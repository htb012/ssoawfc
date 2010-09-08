/**
 * 
 */
package com.sso.service;

import jp.co.pegatron.domain.dao.MetaauthorityDAO;
import jp.co.pegatron.domain.model.Metaauthority;

import com.ssoserver.common.pagination.PaginationSvc;

/**
 * @author HTB
 * 
 */
public class MetaauthSvc extends MetaauthorityDAO implements PaginationSvc {
	private static MetaauthSvc metaauthSvc;

	/**
	 * 
	 */
	private MetaauthSvc() {
		// TODO Auto-generated constructor stub
	}

	public static MetaauthSvc getIntance() {
		if (metaauthSvc == null) {
			metaauthSvc = new MetaauthSvc();
		}
		return metaauthSvc;
	}

	public void deleteById(Integer id) {
		Metaauthority metaauth = this.findById(id);
		delete(metaauth);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssoserver.common.pagination.PaginationSvc#getItemCount(java.lang.String)
	 */
	public int getItemCount(String queryString) {
		return this.findByHql(queryString).size();
	}

}
