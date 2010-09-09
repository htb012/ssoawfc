/*
 * @(#)ResourceSvc.java	1.00 2010-3-2ÏÂÎç08:03:10
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ssoserver.service;

import java.util.ArrayList;
import java.util.List;

import jp.co.pegatron.domain.dao.ResourceDAO;

import com.ssoserver.common.pagination.PaginationSvc;
import com.ssoserver.utils.HqlFactory;

/**
 * @author HTB
 * 
 */
public class ResourceSvc extends ResourceDAO implements PaginationSvc {
	private static ResourceSvc resourceSvc;

	/**
	 * 
	 */
	private ResourceSvc() {
		// TODO Auto-generated constructor stub
	}

	public static ResourceSvc getInstance() {
		if (resourceSvc == null) {
			resourceSvc = new ResourceSvc();
		}
		return resourceSvc;
	}

	public boolean isResProtect(String resname) {
		List<String> paraList = new ArrayList<String>();
		paraList.add(resname);
		String hql = HqlFactory.getInstance().buildGeneralHql("isResProtect",
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
