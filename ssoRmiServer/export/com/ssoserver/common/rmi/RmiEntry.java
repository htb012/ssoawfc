/*
 * @(#)RmiEntry.java	1.00 2008-11-24下午11:28:39
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ssoserver.common.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.ssoserver.utils.GetPropMessage;


/**
 * 获取Rmi实体对象
 * 
 * @author HTB
 * 
 */
public class RmiEntry {

	/**
	 * 
	 */
	public RmiEntry() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 通过指定的URL获取到Rmi对象
	 * 
	 * @param url
	 *            网络地址
	 * @return Rmi对象
	 */
	public static Object iv(String url) {
		try {
			return Naming.lookup(url);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过指定的服务器地址、端口号、上下文获取到Rmi对象
	 * 
	 * @param server
	 *            服务器地址
	 * @param port
	 *            端口号
	 * @param context
	 *            上下文
	 * @return Rmi对象
	 */
	public static Object iv(String server, String port, String context) {
		StringBuffer url = new StringBuffer();
		url.append("http://" + server);
		url.append(":" + port);
		url.append(context);
		return RmiEntry.iv(url.toString());
	}

	/**
	 * 通过指定配置文件中的标准配置和指定的上下文来获取获取到Rmi对象<br>
	 * 标准配置文件定义有：<br>
	 * 服务器地址（server）、端口号（port）
	 * 
	 * @param conFile
	 *            配置文件名
	 * @param strContext
	 *            上下文
	 * @return Rmi对象
	 */
	public static Object iv(String conFile, String context) {
		if (conFile == null || "".equals(conFile)) {
			conFile = "conf";
		}
		StringBuffer url = new StringBuffer();

		url.append("http://" + GetPropMessage.getMessage("server", conFile));
		url.append(":" + GetPropMessage.getMessage("port", conFile));
		url.append("/" + GetPropMessage.getMessage("context", conFile));
		return RmiEntry.iv(url.toString());
	}
}