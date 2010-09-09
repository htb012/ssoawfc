/*
 * @(#)UserMgrInf.java	1.00 2008-11-24����10:38:38
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
 * �û������ͨ�Žӿ�<br>
 * ʵ�ֶ��û��ĵ�¼״̬����ǰ״̬���û�����Ĳ�ѯ�����򵥵��û���¼����
 * 
 * @author HTB
 * 
 */
public interface UserMgr extends Remote {
	public static String SECKEY = "secKey";

	/**
	 * ͨ���û�ID�鿴�û��Ƿ�����
	 * 
	 * @param userid
	 *            �û�ID
	 * @return �Ƿ�������Ϣ
	 * 
	 */
	public boolean isLogin(Integer userid) throws RemoteException;

	/**
	 * ��ȡָ���û�ID�ĵ�ǰ�û�״̬
	 * 
	 * @param userid
	 *            �û�ID
	 * @return �û��ĵ�ǰ״̬
	 */
	public EnumUserState getUserState(Integer userid) throws RemoteException;

	/**
	 * �趨ָ���û�ID�ĵ�ǰ�û�״̬
	 * 
	 * @param userid
	 *            �û�ID
	 * @return �û�֮ǰ��״̬
	 */
	public EnumUserState setUserState(Integer userid, EnumUserState userState)
			throws RemoteException;

	/**
	 * ��ȡָ���û�ID���û���Ϣ
	 * 
	 * @param userid
	 *            �û�ID
	 * @return �û���Ϣ
	 */
	public User getUserInfo(Integer userid) throws RemoteException;

	/**
	 * ��¼�û�
	 * 
	 * @param user
	 *            �û���Ϣ
	 * @return �Ƿ��¼�ɹ�
	 */
	public boolean login(User user) throws RemoteException;

	/**
	 * ��¼�û�����¼�û��İ�ȫ��
	 * 
	 * @param securityKey
	 *            ��ȫ��
	 * @param username
	 *            �û���
	 * @return
	 * @throws RemoteException
	 */
	public boolean login(String securityKey, String username, String password)
			throws RemoteException;

	/**
	 * ͨ����ȫ�뽫�û��˳�
	 * 
	 * @param securityKey
	 *            ��ȫ��
	 * @return
	 * @throws RemoteException
	 */
	public boolean logout(String securityKey) throws RemoteException;

	/**
	 * ��ȡ��ǰ�û�״̬
	 * 
	 * @param sessionID
	 * @return
	 * @throws RemoteException
	 */
	public User getCurrentUser(String sessionID) throws RemoteException;

	/**
	 * ��ȡ��ǰ��������
	 * 
	 * @return ��������
	 * @throws RemoteException
	 */
	public int getOnlineCount() throws RemoteException;

}
