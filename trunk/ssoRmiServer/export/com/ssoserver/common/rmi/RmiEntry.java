/*
 * @(#)RmiEntry.java	1.00 2008-11-24����11:28:39
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
 * ��ȡRmiʵ�����
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
	 * ͨ��ָ����URL��ȡ��Rmi����
	 * 
	 * @param url
	 *            �����ַ
	 * @return Rmi����
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
	 * ͨ��ָ���ķ�������ַ���˿ںš������Ļ�ȡ��Rmi����
	 * 
	 * @param server
	 *            ��������ַ
	 * @param port
	 *            �˿ں�
	 * @param context
	 *            ������
	 * @return Rmi����
	 */
	public static Object iv(String server, String port, String context) {
		StringBuffer url = new StringBuffer();
		url.append("http://" + server);
		url.append(":" + port);
		url.append(context);
		return RmiEntry.iv(url.toString());
	}

	/**
	 * ͨ��ָ�������ļ��еı�׼���ú�ָ��������������ȡ��ȡ��Rmi����<br>
	 * ��׼�����ļ������У�<br>
	 * ��������ַ��server�����˿ںţ�port��
	 * 
	 * @param conFile
	 *            �����ļ���
	 * @param strContext
	 *            ������
	 * @return Rmi����
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