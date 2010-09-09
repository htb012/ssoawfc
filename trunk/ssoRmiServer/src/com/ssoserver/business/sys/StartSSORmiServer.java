/*
 * @(#)StartSSORmiServer.java	1.00 2008-11-28下午09:18:12
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ssoserver.business.sys;

import org.apache.log4j.Logger;
import com.ssoserver.business.intf.SSOFrame;

/**
 * Rmi服务启动
 * 
 * @author HTB
 * 
 */
public class StartSSORmiServer {
	private static Logger logger = Logger.getLogger(StartSSORmiServer.class);

	/**
	 * 
	 */
	public StartSSORmiServer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StartSSORmiServer.showIntface();
	}

	public static void showIntface() {
		SSOFrame sso = new SSOFrame();
		sso.setVisible(true);
	}
}
