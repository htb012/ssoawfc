/*
 * @(#)CreatImage.java	V2.00 2008-10-26����12:35:28
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.sso.common.validatecode;

import java.io.*;
import java.awt.image.*;

import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;
import java.awt.Image;

import org.apache.log4j.Logger;

import com.ssoserver.utils.GetPropMessage;

/**
 * <b>�����µ���֤��ͼƬ�������Ӧ���ַ�����֤��</b><br>
 * һ����ȡ��֤��ͼƬԴ�ļ�<br>
 * ���������µ���֤ͼƬ�������Ӧ���ַ�����֤��<br>
 * ������ȡ������֤������Ӧ���ַ�����֤��<br>
 * �磺<br>
 * ��֤��ͼƬԴ�ļ���ͼƬ����Ϊ�����˷���������ͼ��ķǡ�����Ӧ���ַ�������Ϊ�����˷���������ͼ��ķǡ�<br>
 * �����������һ���µ���֤��Ϊ�������Ƿ��õĴˡ��������Ӧ���ַ�����֤��Ϊ�������Ƿ��õĴˡ�<br>
 * 
 * @author H.T.B
 * 
 */
public class CreatImage {
	private BufferedImage image;

	private Random r;

	private StringBuffer key = new StringBuffer();

	private Logger logger;

	private String strImageFile;

	private int signValidateCodeWidth;

	private int signValidateCodeHeight;

	private int validateCodeLength;

	private String validateCodeStr;

	public CreatImage() {
		this.init();
		logger = Logger.getLogger(CreatImage.class);
	}

	private void init() {
		strImageFile = GetPropMessage.getMessage("validateImageFile");
		validateCodeStr = GetPropMessage.getMessage("validateCodeStr");
		String strValidWid = GetPropMessage.getMessage("signValidateCodeWidth");
		signValidateCodeWidth = new Integer(strValidWid).intValue();
		String strValidHeg = GetPropMessage
				.getMessage("signValidateCodeHeight");
		signValidateCodeHeight = new Integer(strValidHeg).intValue();
		String strValidLeng = GetPropMessage.getMessage("validateCodeLength");
		validateCodeLength = new Integer(strValidLeng).intValue();

		image = new BufferedImage(signValidateCodeWidth * validateCodeLength,
				signValidateCodeHeight, BufferedImage.TYPE_INT_RGB);
		r = new Random();
	}

	/**
	 * ��ȡ��֤ͼ���ļ���Image����
	 * 
	 * @return Image����
	 */
	public Image ReadImageFile() {

		File imageFile = new File(strImageFile);
		// logger.info(imageFile.getAbsolutePath());
		BufferedImage imageSrc = null;
		try {
			imageSrc = javax.imageio.ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (imageSrc == null) {
			logger.warn("Can not be read by the designated Image file.");
		}
		return imageSrc;
	}

	/**
	 * ��ȡ�µ���֤��
	 * 
	 * @return
	 */
	public BufferedImage getImage() {
		Graphics g = image.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, signValidateCodeWidth * validateCodeLength,
				signValidateCodeWidth);
		// this.drawRandomString(g,key);
		this.drawRandomImg(g);
		logger.debug("key:" + key);
		return image;
	}

	/**
	 * ��������֤���ͼ���������֤��
	 * 
	 * @param g
	 */
	public void drawRandomImg(Graphics g) {
		for (int i = 0; i < validateCodeLength; i++) {
			int temp = r.nextInt(validateCodeStr.length() - 1);
			key.append(validateCodeStr.charAt(temp));
			g.drawImage(this.ReadImageFile(), i * signValidateCodeWidth, 0,
					(i + 1) * signValidateCodeWidth, signValidateCodeHeight,
					temp * signValidateCodeWidth, 0, (temp + 1)
							* signValidateCodeWidth, signValidateCodeHeight,
					null);
		}
	}

	public String getKey() {
		return this.key.toString();
	}
}
