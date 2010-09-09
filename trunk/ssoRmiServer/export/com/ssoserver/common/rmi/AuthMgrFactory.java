/*
 * @(#)authMgrFactory.java	1.00 2010-3-5ÉÏÎç12:14:52
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ssoserver.common.rmi;

import com.ssoserver.business.authMgr.AuthMgr;
import com.ssoserver.utils.GetPropMessage;

/**
 * @author HTB
 * 
 */
public class AuthMgrFactory {
	private static AuthMgrFactory authMgrFactory;
	private static AuthMgr authMgr;

	/**
	 * 
	 */
	private AuthMgrFactory() {
		// TODO Auto-generated constructor stub
	}

	public static AuthMgrFactory getInstance() {
		if (authMgrFactory == null) {
			authMgrFactory = new AuthMgrFactory();
		}
		return authMgrFactory;
	}

	public static AuthMgr getAuthMgr() {
		if (authMgr == null) {
			StringBuffer path = new StringBuffer();
			path
					.append("rmi://"
							+ GetPropMessage.getMessage("ssoRmiServer.ip"));
			path.append(":" + GetPropMessage.getMessage("ssoRmiServer.port"));
			path.append(GetPropMessage.getMessage("ssoRmiServer.AuthMgr"));
			authMgr = (AuthMgr) RmiEntry.iv(path.toString());
		}
		return authMgr;
	}
}
