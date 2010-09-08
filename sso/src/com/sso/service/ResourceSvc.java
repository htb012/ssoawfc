/*
 * @(#)ResourceSvc.java	1.00 2010-3-2ÏÂÎç08:03:10
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.sso.service;

import java.util.List;

import jp.co.pegatron.domain.dao.ResourceDAO;
import jp.co.pegatron.domain.model.Resource;

import com.ssoserver.common.pagination.PaginationSvc;

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
		int size = this.findByResname(resname).size();
		return size > 0;
	}

	public void deleteById(Integer id) {
		Resource resource = this.findById(id);
		this.delete(resource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssoserver.common.pagination.PaginationSvc#getItemCount(java.lang.String)
	 */
	public int getItemCount(String queryString) {
		return this.findByHql(queryString).size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssoserver.common.pagination.PaginationSvc#query(java.lang.String,
	 *      int, int)
	 */
	public List query(String queryString, int startIndex, int itemNum) {
		return super.query(queryString, startIndex, itemNum);
	}
}
