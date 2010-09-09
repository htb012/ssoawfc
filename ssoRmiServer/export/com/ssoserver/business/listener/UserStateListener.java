/**
 * 
 */
package com.ssoserver.business.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ssoserver.business.userMgr.UserMgr;
import com.ssoserver.common.rmi.UserMgrFactory;

/**
 * @author HTB
 * 
 */
public class UserStateListener implements ServletContextListener,
		HttpSessionListener {
	private UserMgr userMgr;

	/**
	 * 
	 */
	public UserStateListener() {
		userMgr = UserMgrFactory.getUserMgr();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		ServletContext sc = sessionEvent.getSession().getServletContext();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		ServletContext sc = sessionEvent.getSession().getServletContext();
	}
}
