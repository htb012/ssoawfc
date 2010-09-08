/*
 * @(#)CreateValide.java	V2.00 2008-10-26����12:35:28
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.sso.common.validatecode;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * ����һ���µ���֤��ͼƬ������֮��Ӧ����ȷ��������֤��Ϊ���ԣ�����rquest�С�
 * 
 * @author H.T.B
 * 
 */
public class CreateValideCode extends HttpServlet {
	private static final String CONTENT_TYPE = "image/jpeg";

	// Initialize global variables
	public void init() throws ServletException {
	}

	// Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		// PrintWriter out = response.getWriter();
		CreatImage creatImg = new CreatImage();
		BufferedImage image = creatImg.getImage();
		request.getSession().setAttribute("forValideteCode", creatImg.getKey());
		ServletOutputStream outs = response.getOutputStream();
		ImageIO.write(image, "jpg", outs);
	}

	// Process the HTTP Post request
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// Clean up resources
	public void destroy() {
	}
}
