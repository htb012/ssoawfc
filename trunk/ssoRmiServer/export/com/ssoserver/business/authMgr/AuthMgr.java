/*
 * @(#)AuthMgr.java	1.00 2008-11-24下午11:20:43
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ssoserver.business.authMgr;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 认证权限管理器<br>
 * 
 * @author HTB
 * 
 */
public interface AuthMgr extends Remote {
	/**
	 * 登录
	 */
	public static int LOGON = 0;
	/**
	 * 没有权限
	 */
	public static int NOAUTH = 1;
	/**
	 * 没有该资源
	 */
	public static int NORES = 2;
	/**
	 * 请求错误
	 */
	public static int ERROR = 3;
	/**
	 * 安全码工厂
	 */
	public static int SECFAC = 4;

	/**
	 * 根据事件码获取地址
	 * 
	 * @return
	 */
	public String getURL(int eventCode) throws RemoteException;

	/**
	 * 判断用户所访问的资源是否需要身份验证。判断规则 1、资源是否被保护：是->验证条件2；否->无需登录
	 * 2、用户是否已登录：是->无需登录；否->需要登录。
	 * 
	 * @param securityKey
	 *            用户所带有的安全码
	 * @param accessSite
	 *            要访问的资源
	 * @return
	 * @throws RemoteException
	 */
	public boolean isNeedLogin(String securityKey, String accessSite)
			throws RemoteException;

	/**
	 * 判断已经登录的用户对某一个资源时候有权限（完全权限）
	 * 
	 * @param securityKey
	 *            用户所带有的安全码
	 * @param resource
	 *            要访问的资源
	 * @return
	 * @throws RemoteException
	 */
	public boolean isHasAuth(String securityKey, String resource)
			throws RemoteException;
}
