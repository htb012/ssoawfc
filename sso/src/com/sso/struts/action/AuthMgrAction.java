/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.sso.struts.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.pegatron.domain.model.Authority;
import jp.co.pegatron.domain.model.Authoritygroup;
import jp.co.pegatron.domain.model.Organization;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sso.service.AuthSvc;
import com.sso.service.AuthgroupSvc;
import com.sso.service.MetaauthSvc;
import com.sso.service.OrgSvc;
import com.sso.service.ResourceSvc;
import com.sso.service.UserSvc;
import com.sso.struts.form.AuthorityForm;
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
public class AuthMgrAction extends Action {
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
			List authList = null;
			String viewHQLStr;
			if ("all".equals(scope)) {
				viewHQLStr = GetPropMessage.getUTF8Message("allAuth");
			} else {
				viewHQLStr = GetPropMessage.getUTF8Message("allAuth");
			}
			authList = pagination.execute(AuthgroupSvc.getInstance(),
					viewHQLStr, request, response);
			request.setAttribute("scope", scope);
			request.setAttribute("authList", authList);
			return mapping.findForward("view");
		} else if ("prepadd".equals(task)) {
			request.setAttribute("resources", ResourceSvc.getInstance()
					.findAll());
			request.setAttribute("metaauths", MetaauthSvc.getIntance()
					.findAll());
			return mapping.findForward("add");
		} else if ("add".equals(task)) {
			AuthorityForm authorityForm = (AuthorityForm) form;
			Authority auth = authorityForm.getAuthority();
			AuthSvc.getInstance().save(auth);
			return mapping.findForward("safeSuc");

		} else if ("delete".equals(task)) {
			Integer authid = new Integer(request.getParameter("authid"));
			AuthSvc.getInstance().deleteById(authid);
			try {
				request.getRequestDispatcher("/authMgr.do?task=view")
						.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
			// �༭һ��Ȩ���������Ϣ
		} else if ("edit".equals(task)) {
			request.setAttribute("resources", ResourceSvc.getInstance()
					.findAll());
			request.setAttribute("metaauths", MetaauthSvc.getIntance()
					.findAll());
			Integer authid = new Integer(request.getParameter("authid"));
			Authority auth = AuthSvc.getInstance().findById(authid);
			AuthorityForm authForm = (AuthorityForm) form;
			authForm.setAuthority(auth);
			return mapping.findForward("edit");
			// ���±���һ������
		} else if ("update".equals(task)) {
			AuthorityForm authForm = (AuthorityForm) form;
			Integer authid = new Integer(request.getParameter("authid"));
			Authority auth = AuthSvc.getInstance().findById(authid);
			authForm.buildAuthority(auth);
			AuthSvc.getInstance().update(auth);
			return mapping.findForward("safeSuc");
		} else if ("search".equals(task)) {
			// δ����
		}
		return null;
	}
}