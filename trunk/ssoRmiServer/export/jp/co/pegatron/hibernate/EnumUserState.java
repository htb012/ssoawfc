/*
 * @(#)UserState.java	1.00 2008-11-24下午10:44:24
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package jp.co.pegatron.hibernate;

/**
 * 用户的状态说明<br>
 * 在线、隐身、离线、未知
 * 
 * @author HTB
 * 
 */
public enum EnumUserState implements java.io.Serializable {
	onLine, hidden, outLine, unKnow;
}
