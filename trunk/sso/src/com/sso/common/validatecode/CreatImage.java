/*
 * @(#)CreatImage.java	V2.00 2008-10-26下午12:35:28
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
 * <b>创建新的验证码图片及与其对应的字符串验证码</b><br>
 * 一、读取验证码图片源文件<br>
 * 二、生成新的验证图片及与其对应的字符串验证码<br>
 * 三、获取数字验证码所对应的字符串验证码<br>
 * 如：<br>
 * 验证码图片源文件上图片文字为：“此方法总是用图像的非”，对应的字符串编码为：“此方法总是用图像的非”<br>
 * 现在随机创建一个新的验证码为：“法是非用的此”，与其对应的字符串验证码为：“法是非用的此”<br>
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
	 * 获取验证图像文件的Image对象
	 * 
	 * @return Image对象
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
	 * 获取新的验证码
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
	 * 生成新验证码的图像和文字验证码
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
