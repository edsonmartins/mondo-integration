package hu.bme.mit.mondo.integration.incquery.hawk;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.scope.IBaseIndex;
import org.eclipse.incquery.runtime.api.scope.IEngineContext;
import org.eclipse.incquery.runtime.exception.IncQueryException;

import uk.ac.york.mondo.integration.api.Hawk.Client;
import uk.ac.york.mondo.integration.hawk.emf.HawkResource;

public class HawkEngineContext implements IEngineContext {

	protected HawkScope hawkScope;
	protected HawkQueryRuntimeContext<?> hawkQueryRuntimeContext;

	protected IncQueryEngine engine;
	protected Logger logger;
	protected Client client;

	public HawkEngineContext(HawkScope hawkScope, IncQueryEngine engine, Logger logger, Client client) {
		this.hawkScope = hawkScope;
		this.client = client;
	}

	@Override
	public void initializeBackends(IQueryBackendInitializer initializer) throws IncQueryException {
		HawkResource hawkResource = null; 
		outer: for (Notifier notifier : hawkScope.getScopeRoots()) {
			if (notifier instanceof HawkResource) {
				hawkResource = (HawkResource) notifier;
				break;
			}
			
			if (notifier instanceof ResourceSet) {
				for (Resource resource : ((ResourceSet) notifier).getResources()) {
					if (resource instanceof HawkResource) {
						hawkResource = (HawkResource) resource;
						break outer;
					}
				}
			}
		}
		if (hawkResource == null) {
			String msg = "Could not find a HawkResource in the HawkScope.";
			throw new IncQueryException(msg, msg);
		}
		
		if (hawkQueryRuntimeContext == null) {
			hawkQueryRuntimeContext = new HawkQueryRuntimeContext<>(hawkResource, logger, hawkScope);
			initializer.initializeWith(hawkQueryRuntimeContext);
		}
	}

	@Override
	public IBaseIndex getBaseIndex() throws IncQueryException {
		return new HawkBaseIndex();
	}

	@Override
	public void dispose() {
		if (hawkQueryRuntimeContext != null) {
			hawkQueryRuntimeContext.dispose();
		}

		this.engine = null;
		this.logger = null;
	}

}
