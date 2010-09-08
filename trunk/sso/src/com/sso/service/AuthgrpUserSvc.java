/**
 * 
 */
package com.sso.service;

import java.util.ArrayList;
import java.util.List;

import com.ssoserver.utils.GetPropMessage;
import com.ssoserver.utils.HqlFactory;

import jp.co.pegatron.domain.dao.AuthgrpAuthDAO;
import jp.co.pegatron.domain.dao.AuthgrpUserDAO;
import jp.co.pegatron.domain.model.AuthgrpAuth;
import jp.co.pegatron.domain.model.AuthgrpAuthId;
import jp.co.pegatron.domain.model.Authority;
import jp.co.pegatron.domain.model.Authoritygroup;

/**
 * @author HTB
 * 
 */
public class AuthgrpUserSvc extends AuthgrpUserDAO {
	private static AuthgrpUserSvc authgrpUserSvc;

	/**
	 * 
	 */
	private AuthgrpUserSvc() {
		// TODO Auto-generated constructor stub
	}

	public static AuthgrpUserSvc getInstance() {
		if (authgrpUserSvc == null) {
			authgrpUserSvc = new AuthgrpUserSvc();
		}
		return authgrpUserSvc;
	}

	public void deleteByuserid(Integer userid) {
		List<String> paraList = new ArrayList<String>();
		paraList.add(userid.toString());
		String delHql = HqlFactory.getInstance().buildGeneralHql(
				"authgrpuserDel", paraList);
		this.deleteByHql(delHql);
	}
}
