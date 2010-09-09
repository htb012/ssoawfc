/*
 * @(#)AuthMgr.java	1.00 2008-11-24����11:20:43
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ssoserver.business.authMgr;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * ��֤Ȩ�޹�����<br>
 * 
 * @author HTB
 * 
 */
public interface AuthMgr extends Remote {
	/**
	 * ��¼
	 */
	public static int LOGON = 0;
	/**
	 * û��Ȩ��
	 */
	public static int NOAUTH = 1;
	/**
	 * û�и���Դ
	 */
	public static int NORES = 2;
	/**
	 * �������
	 */
	public static int ERROR = 3;
	/**
	 * ��ȫ�빤��
	 */
	public static int SECFAC = 4;

	/**
	 * �����¼����ȡ��ַ
	 * 
	 * @return
	 */
	public String getURL(int eventCode) throws RemoteException;

	/**
	 * �ж��û������ʵ���Դ�Ƿ���Ҫ�����֤���жϹ��� 1����Դ�Ƿ񱻱�������->��֤����2����->�����¼
	 * 2���û��Ƿ��ѵ�¼����->�����¼����->��Ҫ��¼��
	 * 
	 * @param securityKey
	 *            �û������еİ�ȫ��
	 * @param accessSite
	 *            Ҫ���ʵ���Դ
	 * @return
	 * @throws RemoteException
	 */
	public boolean isNeedLogin(String securityKey, String accessSite)
			throws RemoteException;

	/**
	 * �ж��Ѿ���¼���û���ĳһ����Դʱ����Ȩ�ޣ���ȫȨ�ޣ�
	 * 
	 * @param securityKey
	 *            �û������еİ�ȫ��
	 * @param resource
	 *            Ҫ���ʵ���Դ
	 * @return
	 * @throws RemoteException
	 */
	public boolean isHasAuth(String securityKey, String resource)
			throws RemoteException;
}
