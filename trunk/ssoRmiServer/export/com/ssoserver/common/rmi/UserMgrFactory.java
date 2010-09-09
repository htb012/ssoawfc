/*
 * @(#)UserMgrFactory.java	1.00 2010-3-5ÏÂÎç02:44:12
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ssoserver.common.rmi;

import com.ssoserver.business.userMgr.UserMgr;
import com.ssoserver.utils.GetPropMessage;

/**
 * @author HTB
 * 
 */
public class UserMgrFactory {
	private static UserMgrFactory userMgrFactory;
	private static UserMgr userMgr;

	/**
	 * 
	 */
	private UserMgrFactory() {
		// TODO Auto-generated constructor stub
	}

	public static UserMgrFactory getInstance() {
		if (userMgrFactory == null) {
			userMgrFactory = new UserMgrFactory();
		}
		return userMgrFactory;
	}

	public static UserMgr getUserMgr() {
		if (userMgr == null) {
			StringBuffer path = new StringBuffer();
			path
					.append("rmi://"
							+ GetPropMessage.getMessage("ssoRmiServer.ip"));
			path.append(":" + GetPropMessage.getMessage("ssoRmiServer.port"));
			path.append(GetPropMessage.getMessage("ssoRmiServer.UserMgr"));
			userMgr = (UserMgr) RmiEntry.iv(path.toString());
		}
		return userMgr;
	}

}
