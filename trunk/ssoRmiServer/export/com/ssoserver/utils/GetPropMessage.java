/*
 * @(#)GetPropMessage.java	1.00 2008-10-1下午02:39:39
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ssoserver.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 配置资源文件读取器<br>
 * 默认资源文件为：ssoServerConf.properties <br>
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
	 * 重新设置资源读取器<br>
	 * 默认资源文件为ssoServerConf.property<br>
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
	 * 通过指定配置项名称、默认资源文件(ssoServerConf.properties)读取配置项的值
	 * 
	 * @param key
	 *            配置项名称
	 * @return 配置项的值
	 */
	public static String getMessage(String key) {
		return resBunld.getString(key);
	}

	/**
	 * 通过指定配置项名称、默认资源文件(ssoServerConf.properties)来读取UTF8编码配置项的值
	 * 
	 * @param key
	 *            配置项名称
	 * @return 配置项的值
	 */
	public static String getUTF8Message(String key) {
		String msg = resBunld.getString(key);
		return FormatStr.format2UTF8(msg);
	}

	/**
	 * 通过指定配置项名称、资源文件来读取配置项的值
	 * 
	 * @param key
	 *            配置项名称
	 * @param url
	 *            资源文件路径
	 * @return 配置项的值
	 */
	public static String getMessage(String key, String url) {
		ResourceBundle resBunld;
		resBunld = ResourceBundle.getBundle(url);
		return resBunld.getString(key);
	}

	/**
	 * 通过指定配置项名称、资源文件和区域来读取配置项的值
	 * 
	 * @param key
	 *            配置项名称
	 * @param url
	 *            资源文件路径
	 * @param local
	 *            区域
	 * @return 配置项的值
	 */
	public static String getMessage(String key, String url, Locale local) {
		ResourceBundle resBunld;
		resBunld = ResourceBundle.getBundle(url, local);
		return resBunld.getString(key);
	}
}
