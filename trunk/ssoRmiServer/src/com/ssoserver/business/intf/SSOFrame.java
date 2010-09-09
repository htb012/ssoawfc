/*
 * @(#)SSOFrame.java	1.00 2010-3-24ÏÂÎç02:02:39
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ssoserver.business.intf;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ssoserver.business.sys.SysSvc;

/**
 * @author HTB
 * 
 */
public class SSOFrame extends JFrame {

	private JPanel panel;
	private JButton startBut;
	private Icon startIcon;
	private JButton stopBut;
	private Icon stopIcon;

	/**
	 * 
	 */
	public SSOFrame() {
		this.init();
	}

	public void init() {
		this.setTitle("SSORmiServer");
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		startBut = new JButton("start");
		startBut.addActionListener(new StartButClick());
		stopBut = new JButton("stop");
		stopBut.addActionListener(new StopButClick());
		stopBut.setEnabled(false);
		panel.add(startBut);
		panel.add(stopBut);
		this.setContentPane(panel);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 250, 100);
	}

	class StartButClick implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			startBut.setEnabled(false);
			stopBut.setEnabled(true);
			SysSvc.getInstance().start();
		}
	}

	class StopButClick implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			startBut.setEnabled(true);
			stopBut.setEnabled(false);
			SysSvc.getInstance().stop();
		}
	}
}
