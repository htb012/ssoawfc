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
public class OrgMgrAction extends Action {
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
		if ("view".equals(task)) {
			Pagination pagination = new Pagination();
			String scope = request.getParameter("scope");
			List orgList = null;
			String viewHQLStr;
			if ("all".equals(scope)) {
				viewHQLStr = GetPropMessage.getUTF8Message("allOrg");
			} else {
				viewHQLStr = GetPropMessage.getUTF8Message("allOrg");
			}
			orgList = pagination.execute(OrgSvc.getInstance(), viewHQLStr,
					request, response);
			request.setAttribute("scope", scope);
			request.setAttribute("orgList", orgList);
			return mapping.findForward("view");
		} else if ("prepadd".equals(task)) {
			request.setAttribute("orgs", OrgSvc.getInstance().findAll());
			request.setAttribute("authgroups", AuthgroupSvc.getInstance()
					.findAll());
			request.setAttribute("users", UserSvc.getInstance().findAll());
			return mapping.findForward("add");
		} else if ("add".equals(task)) {
			OrganizationForm organizationForm = (OrganizationForm) form;
			Organization org = organizationForm.getOrg();
			OrgSvc.getInstance().save(org);
			return mapping.findForward("safeSuc");

		} else if ("delete".equals(task)) {
			String orgid = request.getParameter("orgid");
			OrgSvc.getInstance().deleteById(new Integer(orgid));
			try {
				request.getRequestDispatcher("/orgMgr.do?task=view").forward(
						request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
			// 编辑一个组织
		} else if ("edit".equals(task)) {
			request.setAttribute("orgs", OrgSvc.getInstance().findAll());
			request.setAttribute("authgroups", AuthgroupSvc.getInstance()
					.findAll());
			request.setAttribute("users", UserSvc.getInstance().findAll());
			String orgid = request.getParameter("orgid");
			Organization org = OrgSvc.getInstance()
					.findById(new Integer(orgid));
			OrganizationForm organizationForm = (OrganizationForm) form;
			organizationForm.setOrg(org);
			return mapping.findForward("edit");
			// 更新保存一个公告
		} else if ("update".equals(task)) {
			OrganizationForm organizationForm = (OrganizationForm) form;
			String orgid = request.getParameter("orgid");
			Organization org = OrgSvc.getInstance()
					.findById(new Integer(orgid));
			organizationForm.buildOrg(org);
			OrgSvc.getInstance().update(org);
			return mapping.findForward("safeSuc");
		} else if ("search".equals(task)) {
			// 未需求
		}
		return null;
	}
}