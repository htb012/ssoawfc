/**
 * 
 */
package com.sso.service;

import java.util.List;

import jp.co.pegatron.domain.dao.AuthorityDAO;
import jp.co.pegatron.domain.model.Authority;
import jp.co.pegatron.domain.model.Authoritygroup;

import com.ssoserver.common.pagination.PaginationSvc;

/**
 * @author HTB
 * 
 */
public class AuthSvc extends AuthorityDAO implements PaginationSvc {
	private static AuthSvc authSvc;

	/**
	 * 
	 */
	private AuthSvc() {
		// TODO Auto-generated constructor stub
	}

	public static AuthSvc getInstance() {
		if (authSvc == null) {
			authSvc = new AuthSvc();
		}
		return authSvc;
	}

	public void deleteById(Integer id) {
		Authority authority = this.findById(id);
		delete(authority);
	}

	public int getMaxCount() {
		int maxAuid = new Integer(this.findByHql(
				"select max(auth.id.authid) from Authority as auth").get(0)
				.toString()).intValue();
		return maxAuid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssoserver.common.pagination.PaginationSvc#getItemCount(java.lang.String)
	 */
	public int getItemCount(String queryString) {
		return this.findByHql(queryString).size();
	}

	@Override
	public List query(String queryString, int startIndex, int itemNum) {
		// TODO Auto-generated method stub
		return super.query(queryString, startIndex, itemNum);
	}

}
