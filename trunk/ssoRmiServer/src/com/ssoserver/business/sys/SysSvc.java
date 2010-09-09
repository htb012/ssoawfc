/*
 * @(#)sysSvc.java	1.00 2010-3-27上午12:15:49
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ssoserver.business.sys;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.apache.log4j.Logger;

import com.ssoserver.business.authMgr.AuthMgrImp;
import com.ssoserver.business.userMgr.UserMgrImp;
import com.ssoserver.utils.GetPropMessage;

/**
 * @author HTB
 * 
 */
public class SysSvc {
	private static Logger logger = Logger.getLogger(SysSvc.class);
	public static SysSvc sysSvc;
	public Registry registry;
	public UserMgrImp userMgrImp;
	public AuthMgrImp authMgrImp;

	/**
	 * 
	 */
	private SysSvc() {
		init();
	}

	public static SysSvc getInstance() {
		if (sysSvc == null) {
			sysSvc = new SysSvc();
		}
		return sysSvc;
	}

	public void init() {
		int port = new Integer(GetPropMessage.getMessage("ssoRmiServer.port"))// 指定注册的端口号
				.intValue();
		try {
			userMgrImp = new UserMgrImp();
			authMgrImp = new AuthMgrImp();
			registry = LocateRegistry.createRegistry(port);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		logger.info("Start SSO Rmi Server...");
		try {
			registry.rebind("UserMgr", userMgrImp);
			registry.rebind("AuthMgr", authMgrImp);
			logger.info("ready SSO Rmi Server.");
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("start SSO Rmi failed." + e.getLocalizedMessage());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			logger.info("start SSO Rmi failed." + e.getLocalizedMessage());
		}
	}

	public void stop() {
		logger.info("stop SSO Rmi Server...");
		try {
			registry.unbind("UserMgr");
			registry.unbind("AuthMgr");
			logger.info("stop SSO Rmi Server success.");
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("stop SSO Rmi failed." + e.getLocalizedMessage());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			logger.info("stop SSO Rmi failed." + e.getLocalizedMessage());
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("stop SSO Rmi failed." + e.getLocalizedMessage());
		}
	}
}
