/*
 * @(#)UserMgrInf.java	1.00 2008-11-24下午10:38:38
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ssoserver.business.userMgr;

import java.rmi.Remote;
import java.rmi.RemoteException;

import jp.co.pegatron.domain.model.User;
import jp.co.pegatron.hibernate.EnumUserState;

/**
 * 用户管理的通信接口<br>
 * 实现对用户的登录状态、当前状态和用户对象的查询，及简单的用户登录功能
 * 
 * @author HTB
 * 
 */
public interface UserMgr extends Remote {
	public static String SECKEY = "secKey";

	/**
	 * 通过用户ID查看用户是否在线
	 * 
	 * @param userid
	 *            用户ID
	 * @return 是否在线信息
	 * 
	 */
	public boolean isLogin(Integer userid) throws RemoteException;

	/**
	 * 获取指定用户ID的当前用户状态
	 * 
	 * @param userid
	 *            用户ID
	 * @return 用户的当前状态
	 */
	public EnumUserState getUserState(Integer userid) throws RemoteException;

	/**
	 * 设定指定用户ID的当前用户状态
	 * 
	 * @param userid
	 *            用户ID
	 * @return 用户之前的状态
	 */
	public EnumUserState setUserState(Integer userid, EnumUserState userState)
			throws RemoteException;

	/**
	 * 获取指定用户ID的用户信息
	 * 
	 * @param userid
	 *            用户ID
	 * @return 用户信息
	 */
	public User getUserInfo(Integer userid) throws RemoteException;

	/**
	 * 登录用户
	 * 
	 * @param user
	 *            用户信息
	 * @return 是否登录成功
	 */
	public boolean login(User user) throws RemoteException;

	/**
	 * 登录用户并记录用户的安全码
	 * 
	 * @param securityKey
	 *            安全码
	 * @param username
	 *            用户名
	 * @return
	 * @throws RemoteException
	 */
	public boolean login(String securityKey, String username, String password)
			throws RemoteException;

	/**
	 * 通过安全码将用户退出
	 * 
	 * @param securityKey
	 *            安全码
	 * @return
	 * @throws RemoteException
	 */
	public boolean logout(String securityKey) throws RemoteException;

	/**
	 * 获取当前用户状态
	 * 
	 * @param sessionID
	 * @return
	 * @throws RemoteException
	 */
	public User getCurrentUser(String sessionID) throws RemoteException;

	/**
	 * 获取当前在线人数
	 * 
	 * @return 在线人数
	 * @throws RemoteException
	 */
	public int getOnlineCount() throws RemoteException;

}
