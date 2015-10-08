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
package uk.ac.york.mondo.integration.hawk.servlet.servlets;

import org.apache.thrift.protocol.TCompactProtocol;

import uk.ac.york.mondo.integration.api.utils.APIUtils.ThriftProtocol;

/**
 * Servlet that exposes {@link HawkThriftIface} through the {@link TCompactProtocol}.
 */
public class HawkThriftCompactServlet extends HawkThriftServlet {
	private static final long serialVersionUID = 1L;

	public HawkThriftCompactServlet() throws Exception {
		super(new HawkThriftProcessorFactory(ThriftProtocol.COMPACT));
	}
}
