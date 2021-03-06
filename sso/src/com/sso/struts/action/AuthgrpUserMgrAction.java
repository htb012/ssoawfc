/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.sso.struts.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.pegatron.domain.model.AuthgrpAuth;
import jp.co.pegatron.domain.model.AuthgrpAuthId;
import jp.co.pegatron.domain.model.AuthgrpUser;
import jp.co.pegatron.domain.model.AuthgrpUserId;
import jp.co.pegatron.domain.model.Authority;
import jp.co.pegatron.domain.model.Authoritygroup;
import jp.co.pegatron.domain.model.Organization;
import jp.co.pegatron.domain.model.User;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sso.service.AuthSvc;
import com.sso.service.AuthgroupSvc;
import com.sso.service.AuthgrpAuthSvc;
import com.sso.service.AuthgrpUserSvc;
import com.sso.service.OrgSvc;
import com.sso.service.UserSvc;
import com.sso.struts.form.AuthgrpAuthForm;
import com.sso.struts.form.AuthgrpUserForm;
import com.sso.struts.form.AuthoritygroupForm;
import com.sso.struts.form.OrganizationForm;
import com.ssoserver.common.pagination.Pagination;
import com.ssoserver.utils.GetPropMessage;

/**
 * MyEclipse Struts Creation date: 04-26-2009
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/resource" name="resourceForm"
 *                input="/resource/resource.jsp" scope="request" validate="true"
 */
public class AuthgrpUserMgrAction extends Action {
	/*
	 * Generated Methods
	 */

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String task = request.getParameter("task");
		if ("prepconf".equals(task)) {
			request.setAttribute("allauthgrps", AuthgroupSvc.getInstance()
					.findAll());
			String userid = request.getParameter("userid");
			User user = UserSvc.getInstance().findById(new Integer(userid));
			AuthgrpUserForm authgrpUserForm = (AuthgrpUserForm) form;
			authgrpUserForm.setUser(user);
			// 获取该权限组的权限列表
			Set authgrpusers = user.getAuthgrpUsers();
			List<Authoritygroup> hasauthgrps = new ArrayList<Authoritygroup>();
			for (Object authgrpuser : authgrpusers) {
				hasauthgrps
						.add(((AuthgrpUser) authgrpuser).getAuthoritygroup());
			}
			request.setAttribute("hasauthgrps", hasauthgrps);
			return mapping.findForward("conf");
		} else if ("conf".equals(task)) {
			String userid = request.getParameter("userid");
			AuthgrpUserForm authgrpUserForm = (AuthgrpUserForm) form;
			User user = UserSvc.getInstance().findById(new Integer(userid));
			AuthgrpUserSvc.getInstance().deleteByuserid(new Integer(userid));
			String authgrps = authgrpUserForm.getAuthgrpstr();
			if (!"".equals(authgrps)) {
				String[] hasauthgrps = authgrps.split(",");
				for (int i = 0; i < hasauthgrps.length; i++) {
					Authoritygroup authgrp = AuthgroupSvc.getInstance()
							.findById(new Integer(hasauthgrps[i]));
					AuthgrpUserId id = new AuthgrpUserId();
					id.setAuthgroupid(new Integer(hasauthgrps[i]));
					id.setUserid(new Integer(userid));
					AuthgrpUser authgrpuser = new AuthgrpUser(id, authgrp, user);
					AuthgrpUserSvc.getInstance().save(authgrpuser);
				}
			}
			return mapping.findForward("safeSuc");
		} else if ("view".equals(task)) {
			// 未需求
		} else if ("add".equals(task)) {
			// 未需求
		} else if ("delete".equals(task)) {
			// 未需求
		} else if ("edit".equals(task)) {
			// 未需求
		} else if ("update".equals(task)) {
			// 未需求
		} else if ("search".equals(task)) {
			// 未需求
		}
		return null;
	}
}