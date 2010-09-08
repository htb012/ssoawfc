/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.sso.struts.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.pegatron.domain.model.AuthgrpAuth;
import jp.co.pegatron.domain.model.AuthgrpAuthId;
import jp.co.pegatron.domain.model.Authority;
import jp.co.pegatron.domain.model.Authoritygroup;
import jp.co.pegatron.domain.model.OrgUser;
import jp.co.pegatron.domain.model.OrgUserId;
import jp.co.pegatron.domain.model.Organization;
import jp.co.pegatron.domain.model.Resource;
import jp.co.pegatron.domain.model.User;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sso.service.AuthSvc;
import com.sso.service.AuthgroupSvc;
import com.sso.service.AuthgrpAuthSvc;
import com.sso.service.OrgSvc;
import com.sso.service.OrgUserSvc;
import com.sso.service.ResourceSvc;
import com.sso.service.UserSvc;
import com.sso.struts.form.AuthoritygroupForm;
import com.sso.struts.form.OrgUserForm;
import com.sso.struts.form.OrganizationForm;
import com.sso.struts.form.ResourceForm;
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
public class OrgUserMgrAction extends Action {
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
			request.setAttribute("allusers", UserSvc.getInstance().findAll());
			String orgid = request.getParameter("orgid");
			Organization org = OrgSvc.getInstance()
					.findById(new Integer(orgid));
			OrgUserForm orgUserForm = (OrgUserForm) form;
			orgUserForm.setOrg(org);
			// 获取该组织的人员列表
			Set orgUsers = org.getOrgUsers();
			List<User> members = new ArrayList<User>();
			for (Object orgUser : orgUsers) {
				members.add(((OrgUser) orgUser).getUser());
			}
			request.setAttribute("members", members);
			return mapping.findForward("conf");
		} else if ("conf".equals(task)) {
			String orgid = request.getParameter("orgid");
			OrgUserForm orgUserForm = (OrgUserForm) form;
			Organization org = OrgSvc.getInstance()
					.findById(new Integer(orgid));
			OrgUserSvc.getInstance().deleteByAuthgroupid(new Integer(orgid));
			String memberstr = orgUserForm.getMemberstr();
			if (!"".equals(memberstr)) {
				String[] members = memberstr.split(",");
				for (int i = 0; i < members.length; i++) {
					User user = UserSvc.getInstance().findById(
							new Integer(members[i]));
					OrgUserId id = new OrgUserId();
					id.setOrgid(org.getOrgid());
					id.setUserid(user.getUserid());
					OrgUser orgUser = new OrgUser(id, org, user);
					OrgUserSvc.getInstance().merge(orgUser);
				}
			}
			return mapping.findForward("safeSuc");
		} else if ("view".equals(task)) {
			// 未需求
		} else if ("prepadd".equals(task)) {
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