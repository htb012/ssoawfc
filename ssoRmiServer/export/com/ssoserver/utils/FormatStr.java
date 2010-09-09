package com.ssoserver.utils;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

/**
 * <b>字符串编码转换工具</b><br>
 * 含有如下字符编码转换工具：<br>
 * 默认字符串编码转换,将ISO-8859-1字符串转为GB2312 <br>
 * 字符串编码转换,将字符串转为系统默认编码的字符串<br>
 * 字符串编码转换，将字符串转为指定编码的字符串<br>
 * 字符串编码转换，将字符串转为系统默认编码的字节数组<br>
 * 字符串编码转换，将字符串转为指定编码的字节数组<br>
 * 
 * @author HTB
 * 
 */
public class FormatStr {
	private static Logger logger;
	static {
		logger = Logger.getLogger(FormatStr.class);
	}

	/**
	 * 默认字符串编码转换,将ISO-8859-1字符串转为GB2312
	 * 
	 * @param originalStr
	 *            待转换的字符串
	 * @return 转换完的字符串
	 */
	public static String format(String originalStr) {
		try {
			return new String(originalStr.getBytes("ISO-8859-1"), "GB2312");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 默认字符串编码转换,将ISO-8859-1字符串转为GB2312
	 * 
	 * @param originalStr
	 *            待转换的字符串
	 * @return 转换完的字符串
	 */
	public static String format2UTF8(String originalStr) {
		try {
			return new String(originalStr.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 字符串编码转换,将字符串转为系统默认编码的字符串
	 * 
	 * @param originalStr
	 *            待转换的字符串
	 * @param originalCharsetName
	 *            待转换字符串的编码
	 * @return 转换完的字符串
	 */
	public static String format(String originalStr, String originalCharsetName) {
		if (originalStr == null) {
			return null;
		}
		try {
			return new String(originalStr.getBytes(originalCharsetName));
		} catch (UnsupportedEncodingException ex) {
			logger.error(ex.toString());
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 字符串编码转换，将字符串转为指定编码的字符串
	 * 
	 * @param originalStr
	 *            待转换的字符串
	 * @param originalCharsetName
	 *            待转换字符串的编码
	 * @param targetCharsetName
	 *            需要的字符串编码
	 * @return 转换完的字符串
	 */
	public static String format(String originalStr, String originalCharsetName,
			String targetCharsetName) {
		if (originalStr == null) {
			return null;
		}
		try {
			return new String(originalStr.getBytes(originalCharsetName),
					targetCharsetName);
		} catch (UnsupportedEncodingException ex) {
			logger.error(ex.toString());
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 字符串编码转换，将字符串转为系统默认编码的字节数组
	 * 
	 * @param originalStr
	 *            待转换的字符串
	 * @param originalCharsetName
	 *            待转换字符串的编码
	 * @return 转换完成的字节数组
	 */
	public static byte[] getChaset(String originalStr,
			String originalCharsetName) {
		if (originalStr == null) {
			return null;
		}
		try {
			return originalStr.getBytes(originalCharsetName);
		} catch (UnsupportedEncodingException ex) {
			logger.error(ex.toString());
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 字符串编码转换，将字符串转为指定编码的字节数组
	 * 
	 * @param originalStr
	 *            待转换的字符串
	 * @param originalCharsetName
	 *            待转换字符串的编码
	 * @param targetCharsetName
	 *            需要的字节数组编码
	 * @return 转换完成的字节数组
	 */
	public static byte[] getChaset(String originalStr,
			String originalCharsetName, String targetCharsetName) {
		if (originalStr == null) {
			return null;
		}
		try {
			return new String(originalStr.getBytes(originalCharsetName))
					.getBytes(targetCharsetName);
		} catch (UnsupportedEncodingException ex) {
			logger.error(ex.toString());
			ex.printStackTrace();
			return null;
		}
	}
}