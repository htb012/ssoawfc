/*
 * @(#)FormatDate.java	1.00 下午05:27:54
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ssoserver.utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @author H.T.B
 * 
 */
public class FormatDate {

	private static Logger logger;
	private static SimpleDateFormat simDFDateTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat simDFTime = new SimpleDateFormat("HH:mm:ss");
	private static SimpleDateFormat simDFDate = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static SimpleDateFormat dFDateTime = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");

	/**
	 * 
	 */
	static {
		logger = Logger.getLogger(FormatDate.class);
	}

	public static String formatDateToString(Date date) {
		return simDFDate.format(date);
	}

	public static String formatTimeToString(Time time) {
		return simDFTime.format(time);
	}

	public static String formatDateTimeToString(Date date) {
		return simDFDateTime.format(date);
	}

	public static Date parseStringToDate(String dateStr) {
		try {
			return simDFDate.parse(dateStr);
		} catch (ParseException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static Time parseStringToTime(String dateStr) {
		try {
			return new Time(simDFTime.parse(dateStr).getTime());
		} catch (ParseException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static Date parseStringToDateTime(String dateStr) {
		try {
			return simDFDateTime.parse(dateStr);
		} catch (ParseException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把当天的日期格式化为“yyMMddHH:mm:ss.SSS”格式的字符串
	 * 
	 * @return
	 */
	public static String parseNowDateToString() {
		return dFDateTime.format(new Date());
	}
}
