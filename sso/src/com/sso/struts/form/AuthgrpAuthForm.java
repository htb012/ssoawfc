/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.sso.struts.form;

import javax.servlet.http.HttpServletRequest;

import jp.co.pegatron.domain.model.Authoritygroup;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ssoserver.utils.FormatDate;

/**
 * MyEclipse Struts Creation date: 04-26-2009
 * 
 * XDoclet definition:
 * 
 * @struts.form name="resourceForm"
 */
public class AuthgrpAuthForm extends ActionForm {
	private String task;
	private String authgroupid;
	private String authgroupname;
	private String allauths;
	private String hasauths;
	private String authstr;

	public void setAuthoritygroup(Authoritygroup authgrp) {
		this.authgroupid = authgrp.getAuthgroupid().toString();
		this.authgroupname = authgrp.getAuthgroupname();
	}

	/**
	 * @return the task
	 */
	public String getTask() {
		return task;
	}

	/**
	 * @param task
	 *            the task to set
	 */
	public void setTask(String task) {
		this.task = task;
	}

	/**
	 * @return the authgroupid
	 */
	public String getAuthgroupid() {
		return authgroupid;
	}

	/**
	 * @param authgroupid
	 *            the authgroupid to set
	 */
	public void setAuthgroupid(String authgroupid) {
		this.authgroupid = authgroupid;
	}

	/**
	 * @return the authgroupname
	 */
	public String getAuthgroupname() {
		return authgroupname;
	}

	/**
	 * @param authgroupname
	 *            the authgroupname to set
	 */
	public void setAuthgroupname(String authgroupname) {
		this.authgroupname = authgroupname;
	}

	/**
	 * @return the allauths
	 */
	public String getAllauths() {
		return allauths;
	}

	/**
	 * @param allauths
	 *            the allauths to set
	 */
	public void setAllauths(String allauths) {
		this.allauths = allauths;
	}

	/**
	 * @return the hasauths
	 */
	public String getHasauths() {
		return hasauths;
	}

	/**
	 * @param hasauths
	 *            the hasauths to set
	 */
	public void setHasauths(String hasauths) {
		this.hasauths = hasauths;
	}

	/**
	 * @return the authstr
	 */
	public String getAuthstr() {
		return authstr;
	}

	/**
	 * @param authstr
	 *            the authstr to set
	 */
	public void setAuthstr(String authstr) {
		this.authstr = authstr;
	}

	/**
	 * Method validate
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method reset
	 * 
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}
}