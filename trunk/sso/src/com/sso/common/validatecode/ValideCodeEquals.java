/*
 * @(#)ValideCodeEquals.java	1.00 2008-10-26…œŒÁ10:28:42
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.sso.common.validatecode;

import javax.servlet.http.HttpServletRequest;

/**
 * —È÷§
 * 
 * @author H.T.B
 * 
 */
public class ValideCodeEquals {

	/**
	 * 
	 */
	public ValideCodeEquals() {

	}

	public boolean equals(HttpServletRequest request) {
		String forValideCode = request.getAttribute("forValideteCode")
				.toString();
		String valideCode = request.getAttribute("valideCode").toString();
		return forValideCode.equals(valideCode);
	}
}
