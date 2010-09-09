/*
 * @(#)UserSvc.java	1.00 2010-3-2ÏÂÎç07:18:36
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ssoserver.service;

import java.util.List;

import jp.co.pegatron.domain.dao.UserDAO;

import com.ssoserver.common.pagination.PaginationSvc;

/**
 * @author HTB
 * 
 */
public class UserSvc extends UserDAO implements PaginationSvc {
	private static UserSvc userSvc;

	/**
	 * 
	 */
	private UserSvc() {
		// TODO Auto-generated constructor stub
	}

	public static UserSvc getInstance() {
		if (userSvc == null) {
			userSvc = new UserSvc();
		}
		return userSvc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssoserver.common.pagination.PaginationSvc#getItemCount(java.lang.String)
	 */
	public int getItemCount(String queryString) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssoserver.common.pagination.PaginationSvc#query(java.lang.String,
	 *      int, int)
	 */
	public List query(String queryString, int startIndex, int itemNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
