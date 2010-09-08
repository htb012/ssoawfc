/**
 * 
 */
package com.sso.service;

import java.util.ArrayList;
import java.util.List;

import com.ssoserver.utils.HqlFactory;

import jp.co.pegatron.domain.dao.OrgUserDAO;

/**
 * @author HTB
 * 
 */
public class OrgUserSvc extends OrgUserDAO {
	private static OrgUserSvc orgUserSvc;

	/**
	 * 
	 */
	private OrgUserSvc() {
		// TODO Auto-generated constructor stub
	}

	public static OrgUserSvc getInstance() {
		if (orgUserSvc == null) {
			orgUserSvc = new OrgUserSvc();
		}
		return orgUserSvc;
	}

	public void deleteByAuthgroupid(Integer orgid) {
		List<String> paraList = new ArrayList<String>();
		paraList.add(orgid.toString());
		String delHql = HqlFactory.getInstance().buildGeneralHql(
				"orguserDel", paraList);
		this.deleteByHql(delHql);
	}
}
