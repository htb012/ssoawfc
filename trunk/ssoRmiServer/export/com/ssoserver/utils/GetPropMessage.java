/*
 * @(#)GetPropMessage.java	1.00 2008-10-1����02:39:39
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ssoserver.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * ������Դ�ļ���ȡ��<br>
 * Ĭ����Դ�ļ�Ϊ��ssoServerConf.properties <br>
 * 
 * @author HTB
 * 
 */
public class GetPropMessage {
	private static String url = "systemConf";
	private static ResourceBundle resBunld;

	static {
		resBunld = ResourceBundle.getBundle(url);
	}

	private GetPropMessage() {

	}

	/**
	 * ����������Դ��ȡ��<br>
	 * Ĭ����Դ�ļ�ΪssoServerConf.property<br>
	 */
	public static void reset() {
		url = "systemConf";
		resBunld = ResourceBundle.getBundle(url);
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public static void setUrl(String url) {
		GetPropMessage.url = url;
		resBunld = ResourceBundle.getBundle(url);
	}

	/**
	 * ͨ��ָ�����������ơ�Ĭ����Դ�ļ�(ssoServerConf.properties)��ȡ�������ֵ
	 * 
	 * @param key
	 *            ����������
	 * @return �������ֵ
	 */
	public static String getMessage(String key) {
		return resBunld.getString(key);
	}

	/**
	 * ͨ��ָ�����������ơ�Ĭ����Դ�ļ�(ssoServerConf.properties)����ȡUTF8�����������ֵ
	 * 
	 * @param key
	 *            ����������
	 * @return �������ֵ
	 */
	public static String getUTF8Message(String key) {
		String msg = resBunld.getString(key);
		return FormatStr.format2UTF8(msg);
	}

	/**
	 * ͨ��ָ�����������ơ���Դ�ļ�����ȡ�������ֵ
	 * 
	 * @param key
	 *            ����������
	 * @param url
	 *            ��Դ�ļ�·��
	 * @return �������ֵ
	 */
	public static String getMessage(String key, String url) {
		ResourceBundle resBunld;
		resBunld = ResourceBundle.getBundle(url);
		return resBunld.getString(key);
	}

	/**
	 * ͨ��ָ�����������ơ���Դ�ļ�����������ȡ�������ֵ
	 * 
	 * @param key
	 *            ����������
	 * @param url
	 *            ��Դ�ļ�·��
	 * @param local
	 *            ����
	 * @return �������ֵ
	 */
	public static String getMessage(String key, String url, Locale local) {
		ResourceBundle resBunld;
		resBunld = ResourceBundle.getBundle(url, local);
		return resBunld.getString(key);
	}
}
