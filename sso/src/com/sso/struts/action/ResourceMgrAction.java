/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.sso.struts.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.pegatron.domain.model.Resource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sso.service.ResourceSvc;
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
public class ResourceMgrAction extends Action {
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
			List resList = null;
			String viewHQLStr;
			if ("all".equals(scope)) {
				viewHQLStr = GetPropMessage.getUTF8Message("allRes");
			} else {
				viewHQLStr = GetPropMessage.getUTF8Message("allRes");
			}
			resList = pagination.execute(ResourceSvc.getInstance(), viewHQLStr,
					request, response);
			request.setAttribute("scope", scope);
			request.setAttribute("resList", resList);
			return mapping.findForward("view");
		} else if ("add".equals(task)) {
			ResourceForm resourceForm = (ResourceForm) form;
			Resource resource = resourceForm.getResource();
			ResourceSvc.getInstance().save(resource);
			return mapping.findForward("safeSuc");

		} else if ("delete".equals(task)) {
			String resid = request.getParameter("resid");
			ResourceSvc.getInstance().deleteById(new Integer(resid));
			try {
				request.getRequestDispatcher("/resMgr.do?task=view").forward(
						request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
			// 编辑一个公告
		} else if ("edit".equals(task)) {
			String resid = request.getParameter("resid");
			Resource resource = ResourceSvc.getInstance().findById(
					new Integer(resid));
			ResourceForm resourceForm = (ResourceForm) form;
			resourceForm.setResource(resource);
			return mapping.findForward("edit");
			// 更新保存一个公告
		} else if ("update".equals(task)) {
			ResourceForm resourceForm = (ResourceForm) form;
			Resource resource = ResourceSvc.getInstance().findById(
					new Integer(resourceForm.getResid()));
			resourceForm.buildResource(resource);
			ResourceSvc.getInstance().update(resource);
			return mapping.findForward("safeSuc");
		} else if ("search".equals(task)) {
			// 未需求
		}
		return null;
	}
}