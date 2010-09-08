/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.sso.struts.form;

import javax.servlet.http.HttpServletRequest;

import jp.co.pegatron.domain.model.Metaauthority;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * MyEclipse Struts Creation date: 04-26-2009
 * 
 * XDoclet definition:
 * 
 * @struts.form name="resourceForm"
 */
public class MetaauthorityForm extends ActionForm {
	private Metaauthority metaauth;
	private String task;
	private String metaauthid;
	private String metaauthname;
	/**
	 * 元权限二进制计算式
	 */
	private String metaauthextend;
	private String metaauthdiscription;

	/**
	 * @return the metaauth
	 */
	public Metaauthority getMetaauth() {
		metaauth = new Metaauthority();
		return this.buildMetaauth(metaauth);
	}

	public Metaauthority buildMetaauth(Metaauthority metaauthority) {
		if (metaauthid != null && !"".equals(metaauthid)) {
			metaauthority.setMetaauthid(new Integer(metaauthid));
		}
		metaauthority.setMetaauthname(metaauthname);
		metaauthority.setMetaauthextend(metaauthextend);
		metaauthority.setMetaauthdiscription(metaauthdiscription);
		return metaauthority;
	}

	/**
	 * @param metaauth
	 *            the metaauth to set
	 */
	public void setMetaauth(Metaauthority metaauthority) {
		this.metaauth = metaauthority;
		metaauthid = metaauthority.getMetaauthid().toString();
		metaauthname = metaauthority.getMetaauthname();
		metaauthextend = metaauthority.getMetaauthextend();
		metaauthdiscription = metaauthority.getMetaauthdiscription();
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
	 * @return the metaauthid
	 */
	public String getMetaauthid() {
		return metaauthid;
	}

	/**
	 * @param metaauthid
	 *            the metaauthid to set
	 */
	public void setMetaauthid(String metaauthid) {
		this.metaauthid = metaauthid;
	}

	/**
	 * @return the metaauthname
	 */
	public String getMetaauthname() {
		return metaauthname;
	}

	/**
	 * @param metaauthname
	 *            the metaauthname to set
	 */
	public void setMetaauthname(String metaauthname) {
		this.metaauthname = metaauthname;
	}

	/**
	 * @return the metaauthdiscription
	 */
	public String getMetaauthdiscription() {
		return metaauthdiscription;
	}

	/**
	 * @return the metaauthextend
	 */
	public String getMetaauthextend() {
		return metaauthextend;
	}

	/**
	 * @param metaauthextend
	 *            the metaauthextend to set
	 */
	public void setMetaauthextend(String metaauthextend) {
		this.metaauthextend = metaauthextend;
	}

	/**
	 * @param metaauthdiscription
	 *            the metaauthdiscription to set
	 */
	public void setMetaauthdiscription(String metaauthdiscription) {
		this.metaauthdiscription = metaauthdiscription;
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