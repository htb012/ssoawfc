/*
 * @(#)TestEnumUserState.java	1.00 2008-11-28ÏÂÎç08:50:30
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package test;

import java.util.List;

import jp.co.pegatron.domain.model.User;
import jp.co.pegatron.hibernate.EnumUserState;

import com.ssoserver.service.UserSvc;

/**
 * @author HTB
 * 
 */
public class TestEnumUserState {

	/**
	 * 
	 */
	public TestEnumUserState() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<User> list = UserSvc.getInstance().findAll();
		for (User user : list) {
			System.err.println("before:" + user.getState().name());
			user.setState(EnumUserState.outLine);
			System.err.println("after:" + user.getState().name());
			UserSvc.getInstance().update(user);
		}
		// UserInfo usrInfo = new
		// UserInfo("","","","","","","",EnumUserState.onLine);
		// userInfoDAO.save(usrInfo);
	}
}
