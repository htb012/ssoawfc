/*
 * @(#)UserSvc.java	1.00 2010-3-2����07:18:36
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.sso.service;

import java.util.ArrayList;
import java.util.List;

import jp.co.pegatron.domain.dao.UserDAO;
import jp.co.pegatron.domain.model.User;

import com.ssoserver.common.pagination.PaginationSvc;
import com.ssoserver.utils.HqlFactory;

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

	public void deleteById(Integer id) {
		User user = this.findById(id);
		this.delete(user);
	}

	public boolean isExist(String username, String password) {
		List<String> paraList = new ArrayList<String>();
		paraList.add(username);
		paraList.add(password);
		String hql = HqlFactory.getInstance().buildGeneralHql("userValidate",
				paraList);
		int size = this.findByHql(hql).size();
		return size > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssoserver.common.pagination.PaginationSvc#getItemCount(java.lang.String)
	 */
	public int getItemCount(String queryString) {
		// TODO Auto-generated method stub
		return this.findByHql(queryString).size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssoserver.common.pagination.PaginationSvc#query(java.lang.String,
	 *      int, int)
	 */
	public List query(String queryString, int startIndex, int itemNum) {
		// TODO Auto-generated method stub
		return super.query(queryString, startIndex, itemNum);
	}

}
