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
package uk.ac.york.mondo.integration.hawk.emf.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.activemq.artemis.api.core.ActiveMQException;
import org.apache.thrift.TException;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.impl.DynamicEStoreEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.proxy.CallbackHelper;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import uk.ac.york.mondo.integration.api.AttributeSlot;
import uk.ac.york.mondo.integration.api.ContainerSlot;
import uk.ac.york.mondo.integration.api.Hawk.Client;
import uk.ac.york.mondo.integration.api.HawkAttributeRemovalEvent;
import uk.ac.york.mondo.integration.api.HawkAttributeUpdateEvent;
import uk.ac.york.mondo.integration.api.HawkInstanceNotFound;
import uk.ac.york.mondo.integration.api.HawkInstanceNotRunning;
import uk.ac.york.mondo.integration.api.HawkModelElementAdditionEvent;
import uk.ac.york.mondo.integration.api.HawkModelElementRemovalEvent;
import uk.ac.york.mondo.integration.api.HawkReferenceAdditionEvent;
import uk.ac.york.mondo.integration.api.HawkReferenceRemovalEvent;
import uk.ac.york.mondo.integration.api.HawkSynchronizationEndEvent;
import uk.ac.york.mondo.integration.api.HawkSynchronizationStartEvent;
import uk.ac.york.mondo.integration.api.MixedReference;
import uk.ac.york.mondo.integration.api.ModelElement;
import uk.ac.york.mondo.integration.api.QueryResult;
import uk.ac.york.mondo.integration.api.ReferenceSlot;
import uk.ac.york.mondo.integration.api.Subscription;
import uk.ac.york.mondo.integration.api.SubscriptionDurability;
import uk.ac.york.mondo.integration.api.utils.APIUtils;
import uk.ac.york.mondo.integration.artemis.consumer.Consumer;
import uk.ac.york.mondo.integration.hawk.emf.HawkModelDescriptor;
import uk.ac.york.mondo.integration.hawk.emf.HawkModelDescriptor.LoadingMode;
import uk.ac.york.mondo.integration.hawk.emf.HawkResource;
import uk.ac.york.mondo.integration.hawk.emf.IHawkChangeEventHandler;

/**
 * EMF driver that reads a remote model from a Hawk index. This is the main resource
 * for the <code>.hawkmodel</code> file: during its loading, it will create several
 * {@link HawkFileResourceImpl} instances that will contain the model elements that
 * belong to each file in the Hawk index.
 */
public class HawkResourceImpl extends ResourceImpl implements HawkResource {

	/**
	 * Internal state used only while loading a tree of {@link ModelElement}s. It's
	 * kept separate so Java can reclaim the memory as soon as we're done with that
	 * tree.
	 */
	private final class TreeLoadingState {
		// Only for the initial load (allEObjects is cleared afterwards)
		public String lastTypename, lastMetamodelURI;
		public final List<EObject> allEObjects = new ArrayList<>();

		// Only until references are filled in
		public final Map<ModelElement, EObject> meToEObject = new IdentityHashMap<>();
		public String lastRepository;
		public String lastFile;
	}

	private final class InternalHawkChangeEventHandler implements IHawkChangeEventHandler {
		private long lastSyncStart;

		@Override
		@SuppressWarnings("unchecked")
		public void handle(final HawkReferenceRemovalEvent ev) {
			final EObject source = nodeIdToEObjectMap.get(ev.sourceId);
			final EObject target = nodeIdToEObjectMap.get(ev.targetId);
			if (source != null && target != null) {
				final EReference ref = (EReference)source.eClass().getEStructuralFeature(ev.refName);
				if (!ref.isChangeable() || lazyResolver != null && lazyResolver.isPending((InternalEObject)source, ref)) {
					// we don't want to invoke eGet on unchangeable or pending references/attributes
					return;
				}

				if (ref.isMany()) {
					Collection<EObject> objs = (Collection<EObject>)source.eGet(ref);
					objs.remove(target);
				} else {
					source.eUnset(ref);
				}
			}
		}

		@Override
		@SuppressWarnings("unchecked")
		public void handle(final HawkReferenceAdditionEvent ev) {
			final EObject source = nodeIdToEObjectMap.get(ev.sourceId);
			final EObject target = nodeIdToEObjectMap.get(ev.targetId);
			if (source != null && target != null) {
				final EReference ref = (EReference)source.eClass().getEStructuralFeature(ev.refName);
				if (ref == null || !ref.isChangeable() || lazyResolver != null && lazyResolver.isPending((InternalEObject)source, ref)) {
					// we don't want to invoke eGet on unchangeable or pending references/attributes.
					// also, ref may be null if the node ID has been reused for a different type of node
					// since we received that message.
					return;
				}

				if (ref.isMany()) {
					Collection<EObject> objs = (Collection<EObject>)source.eGet(ref);
					objs.add(target);
				} else {
					source.eSet(ref, target);
				}

				if (ref.isContainer()) {
					source.eResource().getContents().remove(source);
				}
			}
		}

		@Override
		@SuppressWarnings("unchecked")
		public void handle(final HawkModelElementRemovalEvent ev) {
			final EObject eob = nodeIdToEObjectMap.remove(ev.id);
			if (eob != null) {
				eob.eResource().getContents().remove(eob);

				final EObject container = eob.eContainer();
				if (container != null) {
					final EStructuralFeature containingFeature = eob.eContainingFeature();
					if (containingFeature.isMany()) {
						((Collection<EObject>)container.eGet(containingFeature)).remove(eob);
					} else {
						container.eUnset(containingFeature);
					}
				}
			}
		}

		@Override
		public void handle(final HawkModelElementAdditionEvent ev) {
			final Registry registry = getResourceSet().getPackageRegistry();
			final EClass eClass = HawkResourceImpl.getEClass(ev.metamodelURI, ev.typeName, registry);
			final EFactory factory = registry.getEFactory(ev.metamodelURI);

			if (!nodeIdToEObjectMap.containsKey(ev.id)) {
				final EObject eob = factory.create(eClass);
				nodeIdToEObjectMap.put(ev.id, eob);
				addToResource(ev.vcsItem.repoURL, ev.vcsItem.path, eob);
			}
		}

		@Override
		public void handle(final HawkAttributeRemovalEvent ev) {
			final EObject eob = nodeIdToEObjectMap.get(ev.getId());
			if (eob != null) {
				final EStructuralFeature eAttr = eob.eClass().getEStructuralFeature(ev.attribute);

				if (eAttr != null) {
					eob.eUnset(eAttr);
				}
			}
		}

		@Override
		public void handle(final HawkAttributeUpdateEvent ev) {
			final EObject eob = nodeIdToEObjectMap.get(ev.getId());
			if (eob != null) {
				final EClass eClass = eob.eClass();
				final AttributeSlot slot = new AttributeSlot(ev.attribute, ev.value);
				try {
					SlotDecodingUtils.setFromSlot(eClass.getEPackage().getEFactoryInstance(), eClass, eob, slot);
				} catch (IOException e) {
					LOGGER.error(e.getMessage(), e);
				}
			} else {
				LOGGER.debug("EObject for ID {} not found when handling attribute update", ev.getId());
			}
		}

		@Override
		public void handle(HawkSynchronizationStartEvent syncStart) {
			this.lastSyncStart = syncStart.getTimestampNanos();
			LOGGER.debug("Sync started: local timestamp is {} ns", lastSyncStart);
		}

		@Override
		public void handle(HawkSynchronizationEndEvent syncEnd) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Sync ended: local timestamp is {} ns (elapsed time: {} ms)",
						syncEnd.getTimestampNanos(),
						(syncEnd.getTimestampNanos() - lastSyncStart)/1_000_000.0);
			}
		}
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(HawkResourceImpl.class);

	private static EClass getEClass(String metamodelUri, String typeName,
			final Registry packageRegistry) {
		final EPackage pkg = packageRegistry.getEPackage(metamodelUri);
		if (pkg == null) {
			throw new NoSuchElementException(String.format(
					"Could not find EPackage with URI '%s' in the registry %s",
					metamodelUri, packageRegistry));
		}

		final EClassifier eClassifier = pkg.getEClassifier(typeName);
		if (!(eClassifier instanceof EClass)) {
			throw new NoSuchElementException(String.format(
					"Received an element of type '%s', which is not an EClass",
					eClassifier));
		}
		final EClass eClass = (EClass) eClassifier;
		return eClass;
	}

	private final Map<String, EObject> nodeIdToEObjectMap = new HashMap<>();
	private final Map<String, Resource> resources = new HashMap<>();
	private final CompositeHawkChangeEventHandler messageHandler = new CompositeHawkChangeEventHandler(new InternalHawkChangeEventHandler());

	private HawkModelDescriptor descriptor;
	private Client client;
	private LazyResolver lazyResolver;
	private Map<Class<?>, net.sf.cglib.proxy.Factory> factories = null;

	private Consumer subscriber;

	public HawkResourceImpl() {}

	public HawkResourceImpl(URI uri, HawkModelDescriptor descriptor) {
		// Even if we're not only to load anything from the URI (as we have a descriptor),
		// we still need it for proxy resolving (hawk+http URLs won't work from CloudATL
		// otherwise: for some reason, without an URI it cannot find EString, for instance).
		super(uri);
		this.descriptor = descriptor;
	}

	public HawkResourceImpl(URI uri) {
		super(uri);
	}

	@Override
	public void load(Map<?, ?> options) throws IOException {
		if (descriptor != null) {
			// We already have a descriptor: no need to create an InputStream from the URI
			doLoad(descriptor);
		} else {
			// Let Ecore create an InputStream from the URI and call doLoad(InputStream, Map)
			super.load(options);
		}
	}

	public HawkModelDescriptor getDescriptor() {
		return descriptor;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean hasChildren(EObject o) {
		for (EReference r : o.eClass().getEAllReferences()) {
			if (r.isContainment()) {
				if (lazyResolver != null && lazyResolver.isPending((InternalEObject)o, r)) {
					// we assume that pending references always have at least one ID
					return true;
				}
				Object v = o.eGet(r);
				if (r.isMany() && !((Collection<EObject>)v).isEmpty() || !r.isMany() && v != null) {
					return true;
				}
			}
		}
		return false;
	}

	public void doLoad(HawkModelDescriptor descriptor) throws IOException {
		// Needed for the virtual resources we create for EMF files, so
		// the Exeed customizations can activate.
		final Factory hawkResourceFactory = new Factory() {
			@Override
			public Resource createResource(URI uri) {
				return new HawkFileResourceImpl(uri, HawkResourceImpl.this);
			}
		};
		final Map<String, Object> protocolToFactoryMap = getResourceSet().getResourceFactoryRegistry().getProtocolToFactoryMap();
		protocolToFactoryMap.put("hawkrepo+file", hawkResourceFactory);
		protocolToFactoryMap.put("hawkrepo+http", hawkResourceFactory);
		protocolToFactoryMap.put("hawkrepo+https", hawkResourceFactory);
		protocolToFactoryMap.put("hawkrepo+git", hawkResourceFactory);
		protocolToFactoryMap.put("hawkrepo+svn", hawkResourceFactory);
		protocolToFactoryMap.put("hawkrepo+svn+ssh", hawkResourceFactory);

		try {
			this.descriptor = descriptor;
			this.client = APIUtils.connectToHawk(descriptor.getHawkURL(), descriptor.getThriftProtocol());

			// TODO allow for multiple repositories
			final LoadingMode mode = descriptor.getLoadingMode();
			List<ModelElement> elems;
			final String queryLanguage = descriptor.getHawkQueryLanguage();
			final String query = descriptor.getHawkQuery();
			if (queryLanguage != null && queryLanguage.length() > 0 && query != null && query.length() > 0) {
				final List<QueryResult> results = client.query(
						descriptor.getHawkInstance(), query, queryLanguage,
						descriptor.getHawkRepository(), Arrays.asList(descriptor.getHawkFilePatterns()),
						mode.isGreedyAttributes(),
						true,
						!mode.isGreedyAttributes() || descriptor.isSubscribed(),
						mode.isGreedyElements()
				);
				elems = new ArrayList<>();
				for (QueryResult result : results) {
					if (result.isSetVModelElement()) {
						elems.add(result.getVModelElement());
					}
				}
			} else if (mode.isGreedyElements()) {
				// We need node IDs if attributes are lazy or if we're subscribing to remote changes
				elems = client.getModel(descriptor.getHawkInstance(),
					Arrays.asList(descriptor.getHawkRepository()),
					Arrays.asList(descriptor.getHawkFilePatterns()), mode.isGreedyAttributes(),
					true, !mode.isGreedyAttributes() || descriptor.isSubscribed());
			} else {
				elems = client.getRootElements(descriptor.getHawkInstance(),
						Arrays.asList(descriptor.getHawkRepository()),
						Arrays.asList(descriptor.getHawkFilePatterns()),
						mode.isGreedyAttributes(), true);
			}

			final TreeLoadingState state = new TreeLoadingState();
			createEObjectTree(elems, state);
			fillInReferences(elems, state);

			if (descriptor.isSubscribed()) {
				final SubscriptionDurability sd = descriptor.getSubscriptionDurability();

				Subscription subscription = client.watchModelChanges(
					descriptor.getHawkInstance(),
					descriptor.getHawkRepository(),
					Arrays.asList(descriptor.getHawkFilePatterns()),
					descriptor.getSubscriptionClientID(), sd);

				subscriber = APIUtils.connectToArtemis(subscription, sd);
				subscriber.openSession();
				subscriber.processChangesAsync(messageHandler);
			}
		} catch (TException e) {
			LOGGER.error(e.getMessage(), e);
			throw new IOException(e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * Changes the message handler to be used with Artemis.
	 */
	public void addChangeEventHandler(IHawkChangeEventHandler handler) {
		this.messageHandler.addHandler(handler);
	}

	public EList<EObject> fetchNodes(List<String> ids)
			throws HawkInstanceNotFound, HawkInstanceNotRunning,
			TException, IOException {
		// Filter the objects that need to be retrieved
		final List<String> toBeFetched = new ArrayList<>();
		for (String id : ids) {
			if (!nodeIdToEObjectMap.containsKey(id)) {
				toBeFetched.add(id);
			}
		}

		// Fetch the eObjects, decode them and resolve references
		if (!toBeFetched.isEmpty()) {
			List<ModelElement> elems = client.resolveProxies(
					descriptor.getHawkInstance(), toBeFetched,
					descriptor.getLoadingMode().isGreedyAttributes(),
					true);
			final TreeLoadingState state = new TreeLoadingState();
			createEObjectTree(elems, state);
			fillInReferences(elems, state);
		}

		// Rebuild the real EList now
		final EList<EObject> finalList = new BasicEList<EObject>(ids.size());
		for (String id : ids) {
			final EObject eObject = nodeIdToEObjectMap.get(id);
			finalList.add(eObject);
		}
		return finalList;
	}

	public void fetchAttributes(InternalEObject object, final List<String> ids) throws IOException, HawkInstanceNotFound, HawkInstanceNotRunning, TException {
		final List<ModelElement> elems = client.resolveProxies(
			descriptor.getHawkInstance(), ids,
			true, false);
		if (elems.isEmpty()) {
			LOGGER.warn("While retrieving attributes, resolveProxies returned an empty list");
		} else {
			final ModelElement me = elems.get(0);
			final EFactory eFactory = getResourceSet().getPackageRegistry().getEFactory(me.getMetamodelUri());
			final EClass eClass = getEClass(
					me.getMetamodelUri(), me.getTypeName(),
					getResourceSet().getPackageRegistry());
			for (AttributeSlot s : me.attributes) {
				SlotDecodingUtils.setFromSlot(eFactory, eClass, object, s);
			}
		}
	}

	private void addToResource(final String repoURL, final String path, final EObject eob) {
		final String fullURL = "hawkrepo+" + repoURL + (repoURL.endsWith("/") ? "!!/" : "/!!/") + path;
		synchronized(resources) {
			Resource resource = resources.get(fullURL);
			if (resource == null) {
				resource = getResourceSet().createResource(URI.createURI(fullURL));
				resources.put(fullURL, resource);
			}
			resource.getContents().add(eob);
		}
	}

	private EObject createEObject(ModelElement me) throws IOException {
		final Registry registry = getResourceSet().getPackageRegistry();
		final EClass eClass = getEClass(me.metamodelUri, me.typeName, registry);

		final EFactory factory = registry.getEFactory(me.metamodelUri);
		final LoadingMode mode = descriptor.getLoadingMode();
		EObject obj = factory.create(eClass);
		if (!mode.isGreedyAttributes() || !mode.isGreedyElements()) {
			obj = createLazyLoadingInstance(eClass, obj.getClass());
		}

		if (me.isSetId()) {
			nodeIdToEObjectMap.put(me.id, obj);
		}

		if (me.isSetAttributes()) {
			for (AttributeSlot s : me.attributes) {
				SlotDecodingUtils.setFromSlot(factory, eClass, obj, s);
			}
		} else if (!mode.isGreedyAttributes()) {
			getLazyResolver().addLazyAttributes(me.id, obj);
		}

		return obj;
	}

	private EObject createLazyLoadingInstance(EClass eClass, Class<?> klass) {
		/*
		 * We need to create a proxy to intercept eGet calls for lazy loading,
		 * but we need to use a subclass of the *real* implementation, or we'll
		 * have all kinds of issues with static metamodels (e.g. not using
		 * DynamicEObjectImpl).
		 */
		if (factories == null) {
			factories = new HashMap<>();
		}

		net.sf.cglib.proxy.Factory factory = factories.get(klass);
		EObject o;
		if (factory == null) {
			Enhancer enh = new Enhancer();
			CallbackHelper helper = new CallbackHelper(klass, new Class[0]) {
				@Override
				protected Object getCallback(Method m) {
					if ("eGet".equals(m.getName())
							&& m.getParameterTypes().length > 0
							&& EStructuralFeature.class.isAssignableFrom(m.getParameterTypes()[0])) {
						return getLazyResolver().getMethodInterceptor();
					} else {
						return NoOp.INSTANCE;
					}
				}
			};
			enh.setSuperclass(klass);

			/*
			 * We need both classloaders: the classloader of the class to be
			 * enhanced, and the classloader of this plugin (which includes
			 * CGLIB). We want the CGLIB classes to always resolve to the same
			 * Class objects, so this plugin's classloader *has* to go first.
			 */
			enh.setClassLoader(new BridgeClassLoader(
				this.getClass().getClassLoader(),
				klass.getClassLoader()));

			/*
			 * The objects created by the Enhancer implicitly implement the
			 * CGLIB Factory interface as well. According to CGLIB, going
			 * through the Factory is faster than recreating or reusing the
			 * Enhancer.
			 */
			enh.setCallbackFilter(helper);
			enh.setCallbacks(helper.getCallbacks());
			o = (EObject)enh.create();
			factories.put(klass, (net.sf.cglib.proxy.Factory)o);
		} else {
			o = (EObject) factory.newInstance(factory.getCallbacks());
		}

		/*
		 * A newly created and instrumented DynamicEObjectImpl won't have the
		 * eClass set. We need to redo that here.
		 */
		if (o instanceof DynamicEObjectImpl) {
			((DynamicEObjectImpl)o).eSetClass(eClass);
		}
		return o;
	}

	private List<EObject> createEObjectTree(final List<ModelElement> elems, final TreeLoadingState state) throws IOException {
		final List<EObject> eObjects = new ArrayList<>();
		for (ModelElement me : elems) {
			if (me.isSetMetamodelUri()) {
				state.lastMetamodelURI = me.getMetamodelUri();
			} else {
				me.setMetamodelUri(state.lastMetamodelURI);
			}
			
			if (me.isSetTypeName()) {
				state.lastTypename = me.getTypeName();
			} else {
				me.setTypeName(state.lastTypename);
			}

			if (me.isSetRepositoryURL()) {
				state.lastRepository = me.getRepositoryURL();
			} else {
				me.setRepositoryURL(state.lastRepository);
			}

			if (me.isSetFile()) {
				state.lastFile = me.getFile();
			} else {
				me.setFile(state.lastFile);
			}

			final EObject obj = createEObject(me);
			state.allEObjects.add(obj);
			state.meToEObject.put(me, obj);
			eObjects.add(obj);

			if (me.isSetContainers()) {
				for (ContainerSlot s : me.containers) {
					final EStructuralFeature sf = obj.eClass().getEStructuralFeature(s.name);
					final List<EObject> children = createEObjectTree(s.elements, state);
					if (sf.isMany()) {
						obj.eSet(sf, ECollections.toEList(children));
					} else if (!children.isEmpty()) {
						obj.eSet(sf, children.get(0));
					}
					for (EObject child : children) {
						if (child.eResource() != null) {
							child.eResource().getContents().remove(child);
						}
					}
				}
			}
		}
		return eObjects;
	}

	private void fillInReferences(final List<ModelElement> elems, TreeLoadingState state) throws IOException {
		final Registry packageRegistry = getResourceSet().getPackageRegistry();

		for (ModelElement me : elems) {
			final EObject sourceObj = state.meToEObject.remove(me);
			fillInReferences(packageRegistry, me, sourceObj, state);
		}
	}

	private void fillInReferences(final Registry packageRegistry, ModelElement me, final EObject sourceObj, final TreeLoadingState state) throws IOException {
		if (me.isSetReferences()) {
			for (ReferenceSlot s : me.references) {
				final EClass eClass = getEClass(me.getMetamodelUri(), me.getTypeName(), packageRegistry);
				final EReference feature = (EReference) eClass.getEStructuralFeature(s.name);

				if (feature.isContainer()) {
					if (sourceObj.eResource() != null) {
						sourceObj.eResource().getContents().remove(sourceObj);
					}
				} else {
					fillInReference(sourceObj, s, feature, state);
				}
			}
		}

		if (me.isSetContainers()) {
			for (ContainerSlot s : me.getContainers()) {
				fillInReferences(s.elements, state);
			}
		}

		if (sourceObj.eContainer() == null) {
			// This is a root element for the moment: add it to the appropriate resource
			addToResource(me.getRepositoryURL(), me.getFile(), sourceObj);
		}
	}

	private void fillInReference(final EObject sourceObj, final ReferenceSlot s, final EReference feature, final TreeLoadingState state) {
		if (!feature.isChangeable() || feature.isDerived() && !(sourceObj instanceof DynamicEStoreEObjectImpl)) {
			// we don't set unchangeable features, and we don't derived references on real objects
			return;
		}

		final boolean greedyElements = descriptor.getLoadingMode().isGreedyElements();

		// This variable will be set to a non-null value if we need to call eSet
		EList<Object> eSetValues = null;

		/*
		 * When using a query in combination with a lazy mode, the query might
		 * have retrieved the value we wanted straight away (e.g. LAZY_CHILDREN
		 * + "return Tree.all;"). Therefore, it is better to simply check if we
		 * already have the value and then add things to the resolver if we
		 * don't have it *and* we're on a lazy mode.
		 */

		if (s.isSetId()) {
			final EObject eObject = nodeIdToEObjectMap.get(s.id);
			if (eObject != null) {
				eSetValues = createEList(eObject);
			} else if (!greedyElements) {
				final EList<Object> value = new BasicEList<Object>();
				value.add(s.id);
				getLazyResolver().addLazyReferences(sourceObj, feature, value);
			}
		}
		else if (s.isSetIds()) {
			eSetValues = createEList();
			for (String targetId : s.ids) {
				final EObject eob = nodeIdToEObjectMap.get(targetId);
				if (eob != null) {
					eSetValues.add(eob);
					if (feature.isContainment() && eob.eResource() != null) {
						eob.eResource().getContents().remove(eob);
					}
				}
			}

			if (!greedyElements && eSetValues.size() != s.ids.size()) {
				eSetValues = null;
				final EList<Object> lazyIds = new BasicEList<>();
				lazyIds.addAll(s.ids);
				getLazyResolver().addLazyReferences(sourceObj, feature, lazyIds);
			}
		}
		else if (s.isSetPosition()) {
			eSetValues = createEList(state.allEObjects.get(s.position));
		}
		else if (s.isSetPositions()) {
			eSetValues = createEList();
			for (Integer position : s.positions) {
				eSetValues.add(state.allEObjects.get(position));
			}
		}
		else if (s.isSetMixed()) {
			final EList<Object> value = createEList();

			boolean allFetched = true;
			for (MixedReference mixed : s.mixed) {
				if (mixed.isSetId()) {
					final EObject eob = nodeIdToEObjectMap.get(mixed.getId());
					if (eob != null) {
						value.add(eob);
						if (feature.isContainment() && eob.eResource() != null) {
							eob.eResource().getContents().remove(eob);
						}
					} else if (!greedyElements) {
						allFetched = false;
						value.add(mixed.getId());
					}
				} else if (mixed.isSetPosition()) {
					value.add(state.allEObjects.get(mixed.getPosition()));
				} else {
					LOGGER.warn("Unknown mixed reference in {}", mixed);
				}
			}
			if (allFetched) {
				eSetValues = value;
			} else {
				getLazyResolver().addLazyReferences(sourceObj, feature, value);
			}
		}
		else {
			LOGGER.warn("No known reference field was set in {}", s);
		}

		if (eSetValues != null) {
			if (feature.isMany()) {
				sourceObj.eSet(feature, eSetValues);
			} else if (!eSetValues.isEmpty()) {
				sourceObj.eSet(feature, eSetValues.get(0));
			}
			if (feature.isContainment()) {
				for (Object o : eSetValues) {
					final EObject contained = (EObject)o;
					if (contained.eResource() != null) {
						contained.eResource().getContents().remove(contained);
					}
				}
			}
		}
	}

	private EList<Object> createEList(final EObject... objects) {
		EList<Object> values = new BasicEList<Object>();
		values.addAll(Arrays.asList(objects));
		return values;
	}

	private LazyResolver getLazyResolver() {
		if (lazyResolver == null) {
			lazyResolver = new LazyResolver(this);
		}
		return lazyResolver;
	}

	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		HawkModelDescriptor descriptor = new HawkModelDescriptor();
		descriptor.load(inputStream);
		doLoad(descriptor);
	}

	@Override
	protected void doUnload() {
		super.doUnload();

		resources.clear();
		nodeIdToEObjectMap.clear();

		if (client != null) {
			client.getInputProtocol().getTransport().close();
			client = null;
		}

		if (subscriber != null) {
			try {
				subscriber.closeSession();
			} catch (ActiveMQException e) {
				LOGGER.error("Could not close the subscriber session", e);
			}
			subscriber = null;
		}

		lazyResolver = null;
	}

	@Override
	protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		throw new UnsupportedOperationException("Remote views are read-only");
	}

}