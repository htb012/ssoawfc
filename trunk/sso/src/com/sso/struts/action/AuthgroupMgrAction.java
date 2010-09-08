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
import jp.co.pegatron.domain.model.Authority;
import jp.co.pegatron.domain.model.Authoritygroup;
import jp.co.pegatron.domain.model.Organization;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sso.service.AuthSvc;
import com.sso.service.AuthgroupSvc;
import com.sso.service.AuthgrpAuthSvc;
import com.sso.service.OrgSvc;
import com.sso.service.UserSvc;
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
public class AuthgroupMgrAction extends Action {
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
			List authgroupList = null;
			String viewHQLStr;
			if ("all".equals(scope)) {
				viewHQLStr = GetPropMessage.getUTF8Message("allAuthgroup");
			} else {
				viewHQLStr = GetPropMessage.getUTF8Message("allAuthgroup");
			}
			authgroupList = pagination.execute(AuthgroupSvc.getInstance(),
					viewHQLStr, request, response);
			request.setAttribute("scope", scope);
			request.setAttribute("authgroupList", authgroupList);
			return mapping.findForward("view");
		}else if ("add".equals(task)) {
			AuthoritygroupForm authoritygroupForm = (AuthoritygroupForm) form;
			Authoritygroup authgroup = authoritygroupForm.getAuthoritygroup();
			authgroup.setAuthgroupcreatetime(new Date());
			AuthgroupSvc.getInstance().save(authgroup);
			return mapping.findForward("safeSuc");
		} else if ("delete".equals(task)) {
			String authgroupid = request.getParameter("authgroupid");
			AuthgroupSvc.getInstance().deleteById(new Integer(authgroupid));
			try {
				request.getRequestDispatcher("/authgroupMgr.do?task=view")
						.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
			// 编辑一个权限组基本信息
		} else if ("edit".equals(task)) {
			String authgroupid = request.getParameter("authgroupid");
			Authoritygroup authgroup = AuthgroupSvc.getInstance().findById(
					new Integer(authgroupid));
			AuthoritygroupForm authoritygroupForm = (AuthoritygroupForm) form;
			authoritygroupForm.setAuthoritygroup(authgroup);
			return mapping.findForward("edit");
			// 更新保存一个公告
		} else if ("update".equals(task)) {
			AuthoritygroupForm authoritygroupForm = (AuthoritygroupForm) form;
			String authgroupid = request.getParameter("authgroupid");
			Authoritygroup authgroup = AuthgroupSvc.getInstance().findById(
					new Integer(authgroupid));
			authoritygroupForm.buildAuthoritygroup(authgroup);
			AuthgroupSvc.getInstance().update(authgroup);
			return mapping.findForward("safeSuc");
		} else if ("search".equals(task)) {
			// 未需求
		}
		return null;
	}
}