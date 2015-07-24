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
package uk.ac.york.mondo.integration.hawk.servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.server.TServlet;
import org.hawk.core.graph.IGraphDatabase;
import org.hawk.core.graph.IGraphIterable;
import org.hawk.core.graph.IGraphNode;
import org.hawk.core.graph.IGraphNodeIndex;
import org.hawk.core.graph.IGraphTransaction;
import org.hawk.core.query.InvalidQueryException;
import org.hawk.graph.FileNode;
import org.hawk.graph.GraphWrapper;
import org.hawk.graph.ModelElementNode;
import org.hawk.neo4j_v2.Neo4JDatabase;
import org.hawk.osgiserver.HManager;
import org.hawk.osgiserver.HModel;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.prefs.BackingStoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.ac.york.mondo.integration.api.Credentials;
import uk.ac.york.mondo.integration.api.DerivedAttributeSpec;
import uk.ac.york.mondo.integration.api.File;
import uk.ac.york.mondo.integration.api.Hawk;
import uk.ac.york.mondo.integration.api.HawkInstance;
import uk.ac.york.mondo.integration.api.HawkInstanceNotFound;
import uk.ac.york.mondo.integration.api.HawkInstanceNotRunning;
import uk.ac.york.mondo.integration.api.IndexedAttributeSpec;
import uk.ac.york.mondo.integration.api.InvalidDerivedAttributeSpec;
import uk.ac.york.mondo.integration.api.InvalidIndexedAttributeSpec;
import uk.ac.york.mondo.integration.api.InvalidMetamodel;
import uk.ac.york.mondo.integration.api.InvalidPollingConfiguration;
import uk.ac.york.mondo.integration.api.InvalidQuery;
import uk.ac.york.mondo.integration.api.ModelElement;
import uk.ac.york.mondo.integration.api.ScalarOrReference;
import uk.ac.york.mondo.integration.api.UnknownQueryLanguage;
import uk.ac.york.mondo.integration.api.UnknownRepositoryType;
import uk.ac.york.mondo.integration.api.VCSAuthenticationFailed;

/**
 * Entry point to the Hawk model indexers. This servlet exposes a Thrift-based
 * API using the Thrift TCompactProtocol, which saves on I/O at the expense of
 * some CPU.
 *
 * @author Antonio García-Domínguez
 */
public class HawkThriftServlet extends TServlet {

	private static final class Iface implements Hawk.Iface {
		
		private static final Logger LOGGER = LoggerFactory.getLogger(HawkThriftServlet.class);
		private final HManager manager = HManager.getInstance();
		
		private HModel getRunningHawkByName(String name) throws HawkInstanceNotFound, HawkInstanceNotRunning {
			HModel model = getHawkByName(name);
			if (model.isRunning()) {
				return model;
			} else {
				throw new HawkInstanceNotRunning();
			}
		}

		private HModel getHawkByName(String name) throws HawkInstanceNotFound {
			HModel model;
			try {
				model = manager.getHawkByName(name);
			} catch (NoSuchElementException ex) {
				throw new HawkInstanceNotFound();
			}
			return model;
		}

		@Override
		public void registerMetamodels(String name, List<File> metamodels) throws HawkInstanceNotFound, HawkInstanceNotRunning, InvalidMetamodel, TException {
			final HModel model = getRunningHawkByName(name);

			for (File f : metamodels) {
				try {
					// Remove path separators for now (UNIX-style / and Windows-style \)
					final String safeName = f.name.replaceAll("/", "_").replaceAll("\\\\", "_");
					final java.io.File dataFile = Activator.getInstance().writeToDataFile(safeName, f.contents);
					// TODO No way to report a bad file?
					model.registerMeta(dataFile);
				} catch (FileNotFoundException ex) {
					throw new TException(ex);
				} catch (IOException ex) {
					throw new TException(ex);
				}
			}
		}

		@Override
		public void unregisterMetamodel(String name, String metamodel) throws HawkInstanceNotFound, HawkInstanceNotRunning, TException {
			//final HModel model = getRunningHawkByName(name);

			// TODO Unregister metamodel not implemented by Hawk yet
			throw new TException(new UnsupportedOperationException());
		}

		@Override
		public List<String> listMetamodels(String name) throws HawkInstanceNotFound, HawkInstanceNotRunning {
			final HModel model = getRunningHawkByName(name);
			return model.getRegisteredMetamodels();
		}

		@Override
		public List<String> listQueryLanguages(String name) throws HawkInstanceNotFound, HawkInstanceNotRunning {
			final HModel model = getRunningHawkByName(name);
			return new ArrayList<String>(model.getKnownQueryLanguages());
		}

		@Override
		public List<ScalarOrReference> query(String name, String query, String language, String scope) throws HawkInstanceNotFound, UnknownQueryLanguage, InvalidQuery, TException {
			final HModel model = getRunningHawkByName(name);
			Map<String, String> context = new HashMap<>();
			context.put(org.hawk.core.query.IQueryEngine.PROPERTY_FILECONTEXT, scope);
			try {
				Object ret = model.contextFullQuery(query, language, context);
				// TODO be able to return other things beyond Strings
				final ScalarOrReference v = new ScalarOrReference();
				v.setVString("" + ret);
				return Arrays.asList(v);
			} catch (NoSuchElementException ex) {
				throw new UnknownQueryLanguage();
			} catch (InvalidQueryException ex) {
				throw new InvalidQuery(ex.getMessage());
			} catch (Exception ex) {
				throw new TException(ex);
			}
		}

		@Override
		public List<ModelElement> resolveProxies(String name, List<String> ids) throws HawkInstanceNotFound, HawkInstanceNotRunning, TException {
			final HModel model = getRunningHawkByName(name);

			final IGraphDatabase graph = model.getGraph();
			final GraphWrapper gw = new GraphWrapper(graph);
			final List<ModelElement> resolved = new ArrayList<ModelElement>();
			try (IGraphTransaction tx = graph.beginTransaction()) {
				for (String id : ids) {
					try {
						ModelElementNode me = gw.getModelElementNodeById(id);
						resolved.add(HawkModelElementEncoder.encodeModelElement(me));
					} catch (Exception ex) {
						LOGGER.error(ex.getMessage(), ex);
					}
				}
			} catch (Exception ex) {
				throw new TException(ex);
			}

			return resolved;
		}

		@Override
		public void addRepository(String name, String uri, String type,
				Credentials credentials) throws HawkInstanceNotFound, HawkInstanceNotRunning, UnknownRepositoryType, VCSAuthenticationFailed {

			// TODO Integrate with centralized repositories API
			final HModel model = getRunningHawkByName(name);
			try {
				model.addVCS(uri, type, credentials.username, credentials.password);
			} catch (NoSuchElementException ex) {
				throw new UnknownRepositoryType();
			}
		}

		@Override
		public void removeRepository(String name, String uri) throws HawkInstanceNotFound, HawkInstanceNotRunning, TException {
			final HModel model = getRunningHawkByName(name);
			try {
				model.removeRepository(uri);
			} catch (Exception ex) {
				throw new TException(ex);
			}
		}

		@Override
		public List<String> listRepositories(String name) throws HawkInstanceNotFound, HawkInstanceNotRunning {
			final HModel model = getRunningHawkByName(name);
			return new ArrayList<String>(model.getLocations());
		}

		@Override
		public List<String> listRepositoryTypes() {
			return new ArrayList<String>(manager.getVCSTypes());
		}

		@Override
		public List<String> listFiles(String name, String repository) throws HawkInstanceNotFound, HawkInstanceNotRunning, TException {
			final HModel model = getRunningHawkByName(name);

			final IGraphDatabase graph = model.getGraph();
			try (IGraphTransaction t = graph.beginTransaction()) {
				final IGraphNodeIndex fileIndex = graph.getFileIndex();
				final IGraphIterable<IGraphNode> contents = fileIndex.query("id", "*");

				final List<String> files = new ArrayList<>();
				for (IGraphNode node : contents) {
					files.add("" + node.getProperty("id"));
				}

				return files;
			} catch (Exception ex) {
				LOGGER.error(ex.getMessage(), ex);
				throw new TException(ex);
			}
		}

		@Override
		public void configurePolling(String name, int base, int max) throws HawkInstanceNotFound, HawkInstanceNotRunning, InvalidPollingConfiguration {
			final HModel model = getRunningHawkByName(name);
			model.configurePolling(base, max);
		}

		@Override
		public void addDerivedAttribute(String name, DerivedAttributeSpec spec)
				throws HawkInstanceNotFound, HawkInstanceNotRunning, InvalidDerivedAttributeSpec,
				TException {
			final HModel model = getRunningHawkByName(name);

			try {
				model.addDerivedAttribute(
					spec.metamodelUri, spec.typeName, spec.attributeName, spec.attributeType,
					spec.isMany, spec.isOrdered, spec.isUnique,
					spec.derivationLanguage, spec.derivationLogic);
			} catch (Exception ex) {
				throw new TException(ex);
			}
		}

		@Override
		public void removeDerivedAttribute(String name, DerivedAttributeSpec spec) throws HawkInstanceNotFound, HawkInstanceNotRunning {
			final HModel model = getRunningHawkByName(name);
			model.removeDerivedAttribute(
				spec.metamodelUri, spec.typeName, spec.attributeName);
		}

		@Override
		public List<DerivedAttributeSpec> listDerivedAttributes(String name) throws HawkInstanceNotFound, HawkInstanceNotRunning {
			final HModel model = getRunningHawkByName(name);

			final List<DerivedAttributeSpec> specs = new ArrayList<>();
			for (String sIndexedAttr : model.getDerivedAttributes()) {
				String[] parts = sIndexedAttr.split("##", 3);
				if (parts.length != 3) {
					LOGGER.warn("Expected {} to have 3 parts, but had {} instead: skipping", sIndexedAttr, parts.length);
					continue;
				}

				final DerivedAttributeSpec spec = new DerivedAttributeSpec();
				spec.metamodelUri = parts[0];
				spec.typeName = parts[1];
				spec.attributeName = parts[2];
				specs.add(spec);
			}
			return specs;
		}

		@Override
		public void addIndexedAttribute(String name, IndexedAttributeSpec spec)
				throws HawkInstanceNotFound, HawkInstanceNotRunning, InvalidIndexedAttributeSpec, TException {
			final HModel model = getRunningHawkByName(name);
			try {
				model.addIndexedAttribute(spec.metamodelUri, spec.typeName, spec.attributeName);
			} catch (Exception e) {
				throw new TException(e);
			}
		}

		@Override
		public void removeIndexedAttribute(String name, IndexedAttributeSpec spec) throws HawkInstanceNotFound, HawkInstanceNotRunning {
			final HModel model = getRunningHawkByName(name);
			model.removeIndexedAttribute(spec.metamodelUri, spec.typeName, spec.attributeName);
		}

		@Override
		public List<IndexedAttributeSpec> listIndexedAttributes(String name) throws HawkInstanceNotFound, HawkInstanceNotRunning {
			final HModel model = getRunningHawkByName(name);

			final List<IndexedAttributeSpec> specs = new ArrayList<>();
			for (String sIndexedAttr : model.getIndexedAttributes()) {
				String[] parts = sIndexedAttr.split("##", 3);
				if (parts.length != 3) {
					LOGGER.warn("Expected {} to have 3 parts, but had {} instead: skipping", sIndexedAttr, parts.length);
					continue;
				}

				final IndexedAttributeSpec spec = new IndexedAttributeSpec(parts[0], parts[1], parts[2]);
				specs.add(spec);
			}
			return specs;
		}

		@Override
		public List<ModelElement> getModel(String name, String repositoryUri, List<String> filePath) throws HawkInstanceNotFound, HawkInstanceNotRunning, TException {
			final HModel model = getRunningHawkByName(name);
			final GraphWrapper gw = new GraphWrapper(model.getGraph());

			// TODO filtering by repository
			final List<ModelElement> elems = new ArrayList<>();
			try (IGraphTransaction tx = model.getGraph().beginTransaction()) {
				for (FileNode fileNode : gw.getFileNodes(filePath)) {
					LOGGER.info("Retrieving elements from {}", filePath);

					int i = 0;
					for (ModelElementNode meNode : fileNode.getModelElements()) {
						elems.add(HawkModelElementEncoder.encodeModelElement(meNode));
						i++;
						if (i % 1000 == 0) {
							LOGGER.info("Retrieved {} model elements", i);
						}
					}
				}
				tx.success();
				return elems;
			} catch (Exception ex) {
				LOGGER.error(ex.getMessage(), ex);
				throw new TException(ex);
			}
		}

		@Override
		public void createInstance(String name, String adminPassword) throws TException {
			try {
				HModel.create(name, storageFolder(name), Neo4JDatabase.class.getName(), null, manager, adminPassword.toCharArray());
			} catch (Exception ex) {
				throw new TException(ex);
			}
		}

		@Override
		public List<HawkInstance> listInstances() throws TException {
			final List<HawkInstance> instances = new ArrayList<>();
			for (HModel m : manager.getHawks()) {
				final HawkInstance instance = new HawkInstance();
				instance.name = m.getName();
				instance.running = m.isRunning();
				instances.add(instance);
			}
			return instances;
		}

		@Override
		public void removeInstance(String name) throws HawkInstanceNotFound, TException {
			final HModel model = getHawkByName(name);
			try {
				manager.delete(model, true);
			} catch (BackingStoreException e) {
				throw new TException(e.getMessage(), e);
			}
		}

		@Override
		public void startInstance(String name, String adminPassword) throws HawkInstanceNotFound, TException {
			final HModel model = getHawkByName(name);
			model.start(manager, adminPassword.toCharArray());
		}

		@Override
		public void stopInstance(String name) throws HawkInstanceNotFound, TException {
			final HModel model = getHawkByName(name);
			model.stop();
		}

		private java.io.File storageFolder(String instanceName) {
			java.io.File dataFile = FrameworkUtil.getBundle(HawkThriftServlet.class).getDataFile("hawk-" + instanceName);
			if (!dataFile.exists()) {
				dataFile.mkdir();
				LOGGER.info("Created storage directory for instance '{}' in '{}'", instanceName, dataFile.getPath());
			} else {
				LOGGER.info("Reused storage directory for instance '{}' in '{}'", instanceName, dataFile.getPath());
			}
			return dataFile;
		}
	}

	private static final long serialVersionUID = 1L;

	public HawkThriftServlet() {
		super(new Hawk.Processor<Hawk.Iface>(new Iface()),
				new TTupleProtocol.Factory());
	}

}
