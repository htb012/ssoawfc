/**
 * 
 */
package com.sso.service;

import java.util.List;

import jp.co.pegatron.domain.dao.OrganizationDAO;
import jp.co.pegatron.domain.model.Organization;

import com.ssoserver.common.pagination.PaginationSvc;

/**
 * @author HTB
 * 
 */
public class OrgSvc extends OrganizationDAO implements PaginationSvc {
	private static OrgSvc orgSvc;

	/**
	 * 
	 */
	private OrgSvc() {
		// TODO Auto-generated constructor stub
	}

	public static OrgSvc getInstance() {
		if (orgSvc == null) {
			orgSvc = new OrgSvc();
		}
		return orgSvc;
	}

	public void deleteById(Integer id) {
		Organization org = this.findById(id);
		delete(org);
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
		return super.query(queryString, startIndex, itemNum);
	}

}
