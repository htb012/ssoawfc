/*
 * @(#)TestUserAuth.java	1.00 2010-3-5ÏÂÎç07:44:15
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import jp.co.pegatron.domain.model.User;

import com.ssoserver.business.authMgr.AuthMgr;
import com.ssoserver.business.authMgr.AuthMgrImp;
import com.ssoserver.service.UserSvc;
import com.ssoserver.utils.HqlFactory;

/**
 * @author HTB
 * 
 */
public class TestUserAuth {

	/**
	 * 
	 */
	public TestUserAuth() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException {
		AuthMgr authMgr = new AuthMgrImp();
		System.err.println(authMgr.isHasAuth(
				"328A9A88A3868CDE6FD473960DD04D96",
				"/sso/manageBus"));
	}
}
