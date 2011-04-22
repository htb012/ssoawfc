/*
 * @(#)AuthMgrImp.java	1.00 2008-11-25上午09:14:22
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ssoserver.business.authMgr;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ssoserver.service.ResourceSvc;
import com.ssoserver.service.UserSvc;
import com.ssoserver.utils.GetPropMessage;
import com.ssoserver.utils.HqlFactory;

/**
 * 权限认证管理器的实现
 * @author HTB
 * 
 */
public class AuthMgrImp extends UnicastRemoteObject implements AuthMgr {
	private String ssoURL;
	private String logonURL;
	private String secFacURL;
	private String noAuthURL;
	private String noResURL;
	private String errorURL;
	private static Log log;

	/**
	 * 
	 */
	public AuthMgrImp() throws RemoteException {
		super();
		this.init();
	}

	/**
	 * @return the ssoURL
	 */
	public String getSsoURL() {
		if (ssoURL == null || "".equals(ssoURL)) {
			this.setSsoURL();
		}
		return ssoURL;
	}

	public void setSsoURL() {
		StringBuffer path = new StringBuffer();
		path.append("http://" + GetPropMessage.getMessage("ssoWebServer.ip"));
		path.append(":" + GetPropMessage.getMessage("ssoWebServer.port"));
		path.append(GetPropMessage.getMessage("ssoWebServer.context"));
		ssoURL = path.toString();
	}

	/**
	 * @return the logonURL
	 */
	public String getLogonURL() {
		if (logonURL == null || "".equals(logonURL)) {
			this.setLogonURL();
		}
		return logonURL;
	}

	public void setLogonURL() {
		StringBuffer path = new StringBuffer();
		path.append(getSsoURL());
		path.append(GetPropMessage.getMessage("ssoWebServer.logonURL"));
		logonURL = path.toString();
	}

	/**
	 * @return the secFacURL
	 */
	public String getSecFacURL() {
		if (secFacURL == null || "".equals(secFacURL)) {
			this.setSecFacURL();
		}
		return secFacURL;
	}

	public void setSecFacURL() {
		StringBuffer path = new StringBuffer();
		path.append(getSsoURL());
		path.append(GetPropMessage.getMessage("ssoWebServer.secFacURL"));
		secFacURL = path.toString();
	}

	/**
	 * @return the noAuthURL
	 */
	public String getNoAuthURL() {
		if (noAuthURL == null || "".equals(noAuthURL)) {
			this.setNoAuthURL();
		}
		return noAuthURL;
	}

	public void setNoAuthURL() {
		StringBuffer path = new StringBuffer();
		path.append(getSsoURL());
		path.append(GetPropMessage.getMessage("ssoWebServer.noAuthURL"));
		noAuthURL = path.toString();
	}

	/**
	 * @return the noResURL
	 */
	public String getNoResURL() {
		if (noResURL == null || "".equals(noResURL)) {
			this.setNoResURL();
		}
		return noResURL;
	}

	public void setNoResURL() {
		StringBuffer path = new StringBuffer();
		path.append(getSsoURL());
		path.append(GetPropMessage.getMessage("ssoWebServer.noResURL"));
		noResURL = path.toString();
	}

	/**
	 * @return the errorURL
	 */
	public String getErrorURL() {
		if (errorURL == null || "".equals(errorURL)) {
			this.setErrorURL();
		}
		return errorURL;
	}

	public void setErrorURL() {
		StringBuffer path = new StringBuffer();
		path.append(getSsoURL());
		path.append(GetPropMessage.getMessage("ssoWebServer.errorURL"));
		errorURL = path.toString();
	}

	public void init() {
		log = LogFactory.getLog(this.getClass());
		this.setSsoURL();
		this.setLogonURL();
		this.setNoAuthURL();
		this.setNoResURL();
		this.setSecFacURL();
		this.setErrorURL();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssoserver.business.authMgr.AuthMgr#getLoginAddress()
	 */
	public String getURL(int eventCode) {
		String returnURL = "";
		switch (eventCode) {
		case AuthMgr.LOGON:
			returnURL = this.getLogonURL();
			break;
		case AuthMgrImp.NOAUTH:
			returnURL = this.getNoAuthURL();
			break;
		case AuthMgr.NORES:
			returnURL = this.getNoResURL();
			break;
		case AuthMgr.SECFAC:
			returnURL = this.getSecFacURL();
			break;
		case AuthMgr.ERROR:
			returnURL = this.getErrorURL();
			break;
		default:
			returnURL = this.getNoResURL();
			break;
		}
		return returnURL;
	}

	public boolean isNeedLogin(String securityKey, String resName)
			throws RemoteException {
		log.debug("resource name is:" + resName);
		if (ResourceSvc.getInstance().isResProtect(resName)) {
			log.debug(resName + " is be protected");
			int size = UserSvc.getInstance().findBySecuritykey(securityKey)
					.size();
			return size <= 0;
		}
		return false;
	}

	public boolean isHasAuth(String securityKey, String resource)
			throws RemoteException {
		if (ResourceSvc.getInstance().isResProtect(resource)) {
			List<String> paraList = new ArrayList<String>();
			paraList.add(securityKey);
			paraList.add(resource);
			String hql = HqlFactory.getInstance().buildGeneralHql(
					"isUserHasAuth", paraList);
			List userList = UserSvc.getInstance().findByHql(hql);
			log.debug("从安全码" + securityKey + "获取到的对资源" + resource + "有权限的用户数为："
					+ userList.size());
			if (userList.size() <= 0) {
				return false;
			}
		}
		return true;
	}
}
