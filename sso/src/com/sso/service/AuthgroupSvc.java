/**
 * 
 */
package com.sso.service;

import java.util.List;

import jp.co.pegatron.domain.dao.AuthoritygroupDAO;
import jp.co.pegatron.domain.model.Authoritygroup;

import com.ssoserver.common.pagination.PaginationSvc;

/**
 * @author HTB
 * 
 */
public class AuthgroupSvc extends AuthoritygroupDAO implements PaginationSvc {
	private static AuthgroupSvc authgroupSvc;

	/**
	 * 
	 */
	private AuthgroupSvc() {
		// TODO Auto-generated constructor stub
	}

	public static AuthgroupSvc getInstance() {
		if (authgroupSvc == null) {
			authgroupSvc = new AuthgroupSvc();
		}
		return authgroupSvc;
	}

	public void deleteById(Integer id) {
		Authoritygroup authoritygroup = this.findById(id);
		delete(authoritygroup);
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
