/*
 * @(#)UserMgrImp.java	1.00 2008-11-25ÉÏÎç09:39:22
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ssoserver.business.userMgr;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import jp.co.pegatron.domain.model.User;
import jp.co.pegatron.hibernate.EnumUserState;

import org.apache.log4j.Logger;

import com.ssoserver.service.UserSvc;

/**
 * @author HTB
 * 
 */
public class UserMgrImp extends UnicastRemoteObject implements UserMgr {
	private static int onlineCount = 0;
	private Logger logger;

	/**
	 * 
	 */
	public UserMgrImp() throws RemoteException {
		super();
		logger = Logger.getLogger(UserMgrImp.class);
	}

	public boolean login(String securityKey, String username, String password)
			throws RemoteException {
		List users = UserSvc.getInstance().findByUsername(username);
		if (users.size() > 0) {
			User user = (User) users.get(0);
			if (password.equals(user.getPassword())) {
				user.setSecuritykey(securityKey);
				user.setState(EnumUserState.onLine);
				UserSvc.getInstance().update(user);
				onlineCount++;
				return true;
			}
		}
		return false;
	}

	public boolean logout(String securityKey) throws RemoteException {
		User user = (User) UserSvc.getInstance().findBySecuritykey(securityKey)
				.get(0);
		if (user != null) {
			user.setSecuritykey(null);
			user.setState(EnumUserState.outLine);
			UserSvc.getInstance().update(user);
			onlineCount--;
			return true;
		} else {
			return false;
		}
	}

	public User getCurrentUser(String sessionID) throws RemoteException {
		// TODO Auto-generated method stub
		return (User) UserSvc.getInstance().findBySecuritykey(sessionID).get(0);
	}

	public User getUserInfo(Integer userid) throws RemoteException {
		return UserSvc.getInstance().findById(userid);
	}

	public EnumUserState getUserState(Integer userid) throws RemoteException {
		return UserSvc.getInstance().findById(userid).getState();
	}

	public boolean isLogin(Integer userid) throws RemoteException {
		User user = UserSvc.getInstance().findById(userid);
		if (user != null) {
			return EnumUserState.onLine.equals(user.getState());
		} else {
			logger.warn("didn't exist userinfo" + userid);
			return false;
		}
	}

	public boolean login(User user) throws RemoteException {
		String username = user.getUsername();
		String password = user.getPassword();
		String secKey = user.getSecuritykey();
		return this.login(secKey, username, password);
	}

	public EnumUserState setUserState(Integer userid, EnumUserState userState)
			throws RemoteException {
		User user = UserSvc.getInstance().findById(userid);
		EnumUserState oldState = user.getState();
		user.setState(userState);
		return oldState;
	}

	public int getOnlineCount() throws RemoteException {
		// TODO Auto-generated method stub
		return onlineCount;
	}
}
