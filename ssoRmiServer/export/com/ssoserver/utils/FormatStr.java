package com.ssoserver.utils;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

/**
 * <b>�ַ�������ת������</b><br>
 * ���������ַ�����ת�����ߣ�<br>
 * Ĭ���ַ�������ת��,��ISO-8859-1�ַ���תΪGB2312 <br>
 * �ַ�������ת��,���ַ���תΪϵͳĬ�ϱ�����ַ���<br>
 * �ַ�������ת�������ַ���תΪָ��������ַ���<br>
 * �ַ�������ת�������ַ���תΪϵͳĬ�ϱ�����ֽ�����<br>
 * �ַ�������ת�������ַ���תΪָ��������ֽ�����<br>
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
	 * Ĭ���ַ�������ת��,��ISO-8859-1�ַ���תΪGB2312
	 * 
	 * @param originalStr
	 *            ��ת�����ַ���
	 * @return ת������ַ���
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
	 * Ĭ���ַ�������ת��,��ISO-8859-1�ַ���תΪGB2312
	 * 
	 * @param originalStr
	 *            ��ת�����ַ���
	 * @return ת������ַ���
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
	 * �ַ�������ת��,���ַ���תΪϵͳĬ�ϱ�����ַ���
	 * 
	 * @param originalStr
	 *            ��ת�����ַ���
	 * @param originalCharsetName
	 *            ��ת���ַ����ı���
	 * @return ת������ַ���
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
	 * �ַ�������ת�������ַ���תΪָ��������ַ���
	 * 
	 * @param originalStr
	 *            ��ת�����ַ���
	 * @param originalCharsetName
	 *            ��ת���ַ����ı���
	 * @param targetCharsetName
	 *            ��Ҫ���ַ�������
	 * @return ת������ַ���
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
	 * �ַ�������ת�������ַ���תΪϵͳĬ�ϱ�����ֽ�����
	 * 
	 * @param originalStr
	 *            ��ת�����ַ���
	 * @param originalCharsetName
	 *            ��ת���ַ����ı���
	 * @return ת����ɵ��ֽ�����
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
	 * �ַ�������ת�������ַ���תΪָ��������ֽ�����
	 * 
	 * @param originalStr
	 *            ��ת�����ַ���
	 * @param originalCharsetName
	 *            ��ת���ַ����ı���
	 * @param targetCharsetName
	 *            ��Ҫ���ֽ��������
	 * @return ת����ɵ��ֽ�����
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