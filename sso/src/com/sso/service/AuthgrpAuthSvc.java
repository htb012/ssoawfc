/**
 * 
 */
package com.sso.service;

import java.util.ArrayList;
import java.util.List;

import com.ssoserver.utils.GetPropMessage;
import com.ssoserver.utils.HqlFactory;

import jp.co.pegatron.domain.dao.AuthgrpAuthDAO;
import jp.co.pegatron.domain.model.AuthgrpAuth;
import jp.co.pegatron.domain.model.AuthgrpAuthId;
import jp.co.pegatron.domain.model.Authority;
import jp.co.pegatron.domain.model.Authoritygroup;

/**
 * @author HTB
 * 
 */
public class AuthgrpAuthSvc extends AuthgrpAuthDAO {
	private static AuthgrpAuthSvc authgrpAuthSvc;

	/**
	 * 
	 */
	private AuthgrpAuthSvc() {
		// TODO Auto-generated constructor stub
	}

	public static AuthgrpAuthSvc getInstance() {
		if (authgrpAuthSvc == null) {
			authgrpAuthSvc = new AuthgrpAuthSvc();
		}
		return authgrpAuthSvc;
	}

	public void deleteByAuthgroupid(Integer authgroupid) {
		List<String> paraList = new ArrayList<String>();
		paraList.add(authgroupid.toString());
		String delHql = HqlFactory.getInstance().buildGeneralHql(
				"authgrpauthDel", paraList);
		this.deleteByHql(delHql);
	}
}
