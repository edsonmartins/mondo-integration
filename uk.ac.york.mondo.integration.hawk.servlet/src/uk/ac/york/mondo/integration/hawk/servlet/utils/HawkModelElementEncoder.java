/*******************************************************************************
 * Copyright (c) 2015 University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Antonio Garcia-Dominguez - initial API and implementation
 *******************************************************************************/
package uk.ac.york.mondo.integration.hawk.servlet.utils;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hawk.graph.GraphWrapper;
import org.hawk.graph.ModelElementNode;

import uk.ac.york.mondo.integration.api.AttributeSlot;
import uk.ac.york.mondo.integration.api.ContainerSlot;
import uk.ac.york.mondo.integration.api.MixedReference;
import uk.ac.york.mondo.integration.api.MixedReference._Fields;
import uk.ac.york.mondo.integration.api.ModelElement;
import uk.ac.york.mondo.integration.api.ReferenceSlot;
import uk.ac.york.mondo.integration.api.Variant;

/**
 * Encodes a graph of Hawk {@link ModelElementNode}s into Thrift
 * {@link ModelElement}s. This is an accumulator: the user should specify the
 * encoding options, call {@link #encode(ModelElementNode)} repeatedly and then
 * finally call {@link #getElements()}.
 *
 * Depending on whether we intend to send the entire model or not, it might make
 * sense to call {@link #setIncludeNodeIDs(boolean)} accordingly before any
 * calls to {@link #encode(ModelElementNode)}.
 */
public class HawkModelElementEncoder {

	private final GraphWrapper graph;

	private final Map<String, ModelElement> nodeIdToElement = new HashMap<>();
	private final Set<ModelElement> rootElements = new IdentityLinkedHashSet<>();

	private String lastMetamodelURI, lastTypename;

	private boolean discardContainerRefs = false;
	private boolean includeAttributes = true;
	private boolean includeReferences = true;
	private boolean sendElementNodeIDs = false;
	private boolean sortByNodeIDs = false;
	private boolean useContainment = true;

	public HawkModelElementEncoder(GraphWrapper gw) {
		this.graph = gw;
	}

	/**
	 * If <code>true</code>, the encoder will include node IDs in the model
	 * elements. Otherwise, it will not include them (the default).
	 *
	 * Note: if we do not include node IDs, it will not be possible to resolve
	 * non-containment references to the encoded elements from elements that
	 * were not encoded. Therefore, setting this to <code>false</code> is only
	 * advisable when we're encoding an entire model, including attributes.
	 */
	public boolean isIncludeNodeIDs() {
		return sendElementNodeIDs;
	}

	/**
	 * Changes the value of {@link #isIncludeNodeIDs()}.
	 */
	public void setIncludeNodeIDs(boolean newValue) {
		this.sendElementNodeIDs = newValue;
	}

	/**
	 * If <code>true</code>, the nodes contained within the encoded nodes will
	 * be retrieved eagerly as well and placed inside the container nodes.
	 * Otherwise, only the requested nodes will be retrieved, and the encoder
	 * will produce a flat list that will not use {@link ContainerSlot}s.
	 */
	public boolean isUseContainment() {
		return useContainment;
	}

	/** Changes the value of {@link #isUseContainment()}. */
	public void setUseContainment(boolean useContainment) {
		this.useContainment = useContainment;
	}


	/**
	 * If <code>true</code>, the tree of {@link ModelElement}s will be sorted
	 * per-level by node ID. This may be necessary if we want to get consistent
	 * ordering over different access methods, e.g. lazy vs greedy loading.
	 */
	public boolean isSortByNodeIDs() {
		return sortByNodeIDs;
	}

	/**
	 * Changes the value of {@link #isSortByNodeIDs()}.
	 */
	public void setSortByNodeIDs(boolean sortByNodeIDs) {
		this.sortByNodeIDs = sortByNodeIDs;
	}

	public boolean isIncludeAttributes() {
		return includeAttributes;
	}

	public void setIncludeAttributes(boolean includeAttributes) {
		this.includeAttributes = includeAttributes;
	}

	public boolean isIncludeReferences() {
		return includeReferences;
	}

	public void setIncludeReferences(boolean includeReferences) {
		this.includeReferences = includeReferences;
	}

	public boolean isDiscardContainerRefs() {
		return discardContainerRefs;
	}

	public void setDiscardContainerRefs(boolean discardContainerRefs) {
		this.discardContainerRefs = discardContainerRefs;
	}

	/**
	 * Returns the list of the encoded {@link ModelElement}s that are not
	 * contained within any other encoded {@link ModelElement}s.
	 */
	public List<ModelElement> getElements() {
		final List<ModelElement> lRoots = new ArrayList<>(rootElements);
		if (isSortByNodeIDs()) {
			sortTreeByNodeId(lRoots);
		}

		final HashMap<String, Integer> id2pos = new HashMap<>();
		computePreorderPositionMap(lRoots, id2pos, 0);
		lastMetamodelURI = lastTypename = null;
		optimizeTree(lRoots, id2pos);

		return lRoots;
	}

	private void sortTreeByNodeId(List<ModelElement> elements) {
		Collections.sort(elements, new Comparator<ModelElement>() {
			public int compare(ModelElement l, ModelElement r) {
				return l.getId().compareTo(r.getId());
			}
		});

		for (ModelElement me : elements) {
			if (me.isSetContainers()) {
				for (ContainerSlot s : me.getContainers()) {
					sortTreeByNodeId(s.elements);
				}
			}
		}
	}

	private int computePreorderPositionMap(Collection<ModelElement> elems, Map<String, Integer> id2pos, int i) {
		for (ModelElement elem : elems) {
			if (elem.isSetId()) {
				id2pos.put(elem.id, i);
			}
			i++;

			if (elem.isSetContainers()) {
				for (ContainerSlot s : elem.containers) {
					i = computePreorderPositionMap(s.elements, id2pos, i);
				}
			}
		}
		return i;
	}

	private void optimizeTree(Collection<ModelElement> elems, Map<String, Integer> id2pos) {
		for (ModelElement me : elems) {
			if (!isIncludeNodeIDs()) {
				me.unsetId();
			}

			if (me.isSetReferences()) {
				for (ReferenceSlot r : me.getReferences()) {
					optimizeReferenceSlot(id2pos, r);
				}
			}
			optimizeRepeatedMetamodelOrType(me);
			// TODO remove AttributeSlots with default values?

			if (me.isSetContainers()) {
				for (ContainerSlot s : me.getContainers()) {
					optimizeTree(s.elements, id2pos);
				}
			}
		}
	}

	private void optimizeRepeatedMetamodelOrType(ModelElement me) {
		// we don't repeat typenames or metamodel URIs if they're the same as the previous element's
		final String currTypename = me.getTypeName();
		final String currMetamodelURI = me.getMetamodelUri();
		if (lastTypename != null && lastTypename.equals(currTypename)) {
			me.unsetTypeName();
		}
		if (lastMetamodelURI != null && lastMetamodelURI.equals(currMetamodelURI)) {
			me.unsetMetamodelUri();
		}
		lastTypename = currTypename;
		lastMetamodelURI = currMetamodelURI;
	}

	private void optimizeReferenceSlot(Map<String, Integer> id2pos,	ReferenceSlot r) {
		// We replace id-based references with position-based references, when we can:
		// the referenced element may not have been encoded.
		
		final Map<Integer, Integer> local2global = computeLocalToGlobalPositionMap(id2pos, r);

		if (local2global.isEmpty()) {
			// Positions are not available: the only thing we can do is switch from ids to id if there's only one
			if (r.ids.size() == 1) {
				r.setId(r.ids.get(0));
				r.unsetIds();
			}
		} else if (local2global.size() == r.ids.size()) {
			// We have positions for all referenced elements: use position or positions
			if (local2global.size() == 1) {
				r.setPosition(local2global.get(0));
				r.unsetIds();
			}
			else {
				r.setPositions(new ArrayList<>(local2global.values()));
				r.unsetIds();
			}
		} else {
			// We only have positions for some referenced elements: use mixed
			final List<MixedReference> mixed = new ArrayList<>();
			int i = 0;
			for (String id : r.ids) {
				final Integer globalPosition = local2global.get(i);
				if (globalPosition == null) {
					mixed.add(new MixedReference(_Fields.ID, id));
				} else {
					mixed.add(new MixedReference(_Fields.POSITION, globalPosition));
				}
				i++;
			}

			r.setMixed(mixed);
			r.unsetIds();
		}
	}

	private Map<Integer, Integer> computeLocalToGlobalPositionMap(Map<String, Integer> id2pos, ReferenceSlot r) {
		int i = 0;

		final Map<Integer, Integer> local2global = new LinkedHashMap<>();
		for (String id : r.ids) {
			final Integer pos = id2pos.get(id);
			if (pos != null) {
				local2global.put(i, pos);
			}
			i++;
		}

		return local2global;
	}

	public void encode(String id) throws Exception {
		final ModelElementNode me = graph.getModelElementNodeById(id);
		encodeInternal(me);
	}

	public void encode(ModelElementNode meNode) throws Exception {
		assert meNode.getNode().getGraph() == this.graph.getGraph()
			: "The node should belong to the same graph as this encoder";
		encodeInternal(meNode);
	}

	private ModelElement encodeInternal(ModelElementNode meNode) throws Exception {
		final ModelElement existing = nodeIdToElement.get(meNode.getId());
		if (existing != null) {
			return existing;
		}

		final ModelElement me = new ModelElement();
		me.setId(meNode.getId());
		me.setTypeName(meNode.getTypeNode().getTypeName());
		me.setMetamodelUri(meNode.getTypeNode().getMetamodelName());

		// we won't set the ID until someone refers to it, but we
		// need to keep track of the element for later
		nodeIdToElement.put(meNode.getId(), me);

		// initially, the model element is not contained in any other
		rootElements.add(me);

		final Map<String, Object> attrs = new HashMap<>();
		final Map<String, Object> refs = new HashMap<>();
		meNode.getSlotValues(attrs, refs);

		if (isIncludeAttributes()) {
			for (Map.Entry<String, Object> attr : attrs.entrySet()) {
				// to save bandwidth, we do not send unset attributes
				if (attr.getValue() == null) continue;
				me.addToAttributes(encodeAttributeSlot(attr.getKey(), attr.getValue()));
			}
		}
		if (isIncludeReferences()) {
			for (Map.Entry<String, Object> ref : refs.entrySet()) {
				// to save bandwidth, we do not send unset or empty references
				if (ref.getValue() == null) continue;

				if (useContainment && meNode.isContainment(ref.getKey())) {
					final ContainerSlot slot = encodeContainerSlot(ref);
					if (slot.elements.isEmpty()) continue;
					me.addToContainers(slot);
				} else if (useContainment && discardContainerRefs && meNode.isContainer(ref.getKey())) {
					// skip this container reference: we're already using containment, so
					// we assume we'll have the parent encoded as well.
				} else {
					final ReferenceSlot slot = encodeReferenceSlot(ref);
					if (slot.ids.isEmpty()) continue;
					me.addToReferences(slot);
				}
			}
		}
		return me;
	}

	private ContainerSlot encodeContainerSlot(Entry<String, Object> slotEntry) throws Exception {
		assert slotEntry.getValue() != null;

		ContainerSlot s = new ContainerSlot();
		s.name = slotEntry.getKey();

		final Object value = slotEntry.getValue();
		if (value instanceof Collection) {
			for (Object o : (Collection<?>)value) {
				final ModelElementNode meNode = graph.getModelElementNodeById((long)o);
				final ModelElement me = encodeInternal(meNode);
				s.addToElements(me);
				rootElements.remove(me);
			}
		} else {
			final ModelElementNode meNode = graph.getModelElementNodeById((long)value);
			final ModelElement me = encodeInternal(meNode);
			s.addToElements(me);
			rootElements.remove(me);
		}

		return s;
	}

	private ReferenceSlot encodeReferenceSlot(Entry<String, Object> slotEntry) throws Exception {
		assert slotEntry.getValue() != null;

		ReferenceSlot s = new ReferenceSlot();
		s.name = slotEntry.getKey();

		final Object value = slotEntry.getValue();
		s.ids = new ArrayList<>();
		if (value instanceof Collection) {
			for (Object o : (Collection<?>)value) {
				addToReferenceIds(o, s);
			}
		} else {
			addToReferenceIds(value, s);
		}

		return s;
	}

	private void addToReferenceIds(Object o, ReferenceSlot s) throws Exception {
		final String referencedId = o.toString();
		final ModelElementNode meNode = graph.getModelElementNodeById(referencedId);
		s.addToIds(meNode.getId());
	}

	public static AttributeSlot encodeAttributeSlot(final String name, Object rawValue) {
		assert rawValue != null;

		// TODO encode arrays as well
		Variant value = new Variant();
		if (rawValue instanceof Object[]) {
			rawValue = Arrays.asList((Object[])rawValue);
		}
		if (rawValue instanceof Collection) {
			final Collection<?> cValue = (Collection<?>) rawValue;
			final int cSize = cValue.size();
			if (cSize == 1) {
				// use the single value attrs if we only have one value (saves
				// 1-5 bytes on TTupleTransport)
				encodeSingleValueAttributeSlot(value, cValue.iterator().next());
			} else if (cSize > 0) {
				value = new Variant();
				encodeNonEmptyListAttributeSlot(value, rawValue, cValue);
			} else {
				// empty list <-> isSet=true and s.values=null
				value = null;
			}
		} else {
			encodeSingleValueAttributeSlot(value, rawValue);
		}

		if (value != null && !value.isSet()) {
			throw new IllegalArgumentException(String.format(
					"Unsupported value type '%s'", rawValue.getClass()
							.getName()));
		}
		return new AttributeSlot(name, value);
	}

	private static void encodeSingleValueAttributeSlot(Variant value, final Object rawValue) {
		if (rawValue instanceof Byte) {
			value.setVByte((byte) rawValue);
		} else if (rawValue instanceof Float) {
			value.setVDouble((double) rawValue);
		} else if (rawValue instanceof Double) {
			value.setVDouble((double) rawValue);
		} else if (rawValue instanceof Integer) {
			value.setVInteger((int) rawValue);
		} else if (rawValue instanceof Long) {
			value.setVLong((long) rawValue);
		} else if (rawValue instanceof Short) {
			value.setVShort((short) rawValue);
		} else if (rawValue instanceof String) {
			value.setVString((String) rawValue);
		} else if (rawValue instanceof Boolean) {
			value.setVBoolean((Boolean) rawValue);
		}
	}

	@SuppressWarnings("unchecked")
	private static void encodeNonEmptyListAttributeSlot(Variant value, final Object rawValue, final Collection<?> cValue) {
		final Iterator<?> it = cValue.iterator();
		final Object o = it.next();
		if (o instanceof Byte) {
			final ByteBuffer bbuf = ByteBuffer.allocate(cValue.size());
			bbuf.put((byte)o);
			while (it.hasNext()) {
				bbuf.put((byte)it.next());
			}
			bbuf.flip();
			value.setVBytes(bbuf);
		} else if (o instanceof Float) {
			final ArrayList<Double> l = new ArrayList<Double>(cValue.size());
			l.add((double)o);
			while (it.hasNext()) {
				l.add((double)it.next());
			}
			value.setVDoubles(l);
		} else if (o instanceof Double) {
			value.setVDoubles(new ArrayList<Double>((Collection<Double>)cValue));
		} else if (o instanceof Integer) {
			value.setVIntegers(new ArrayList<Integer>((Collection<Integer>)cValue));
		} else if (o instanceof Long) {
			value.setVLongs(new ArrayList<Long>((Collection<Long>)cValue));
		} else if (o instanceof Short) {
			value.setVShorts(new ArrayList<Short>((Collection<Short>)cValue));
		} else if (o instanceof String) {
			value.setVStrings(new ArrayList<String>((Collection<String>)cValue));
		} else if (o instanceof Boolean) {
			value.setVBooleans(new ArrayList<Boolean>((Collection<Boolean>)cValue));
		} else if (o != null) {
			throw new IllegalArgumentException(String.format("Unsupported element type '%s'", rawValue.getClass().getName()));
		} else {
			throw new IllegalArgumentException("Null values inside collections are not allowed");
		}
	}

}