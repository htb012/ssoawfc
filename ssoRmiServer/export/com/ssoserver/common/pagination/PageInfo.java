/*
 * @(#)PageInfo.java	1.00 2008-11-10����12:40:00
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ssoserver.common.pagination;

import javax.servlet.http.HttpServletRequest;

/**
 * WEB��ҳ�������,��Ҫ��� page.js�����
 * 
 */
public class PageInfo {

	/**
	 * ��request�����д��ݷ�ҳ��Ϣ�ģ��ܵļ�¼������ҳ����ÿҳ��ʾ��¼������ǰҳ�룩�ı�ʶ��
	 */
	public static final String KEY = "page_pginfo";

	/**
	 * ҳ���л��¼���ʶ��
	 */
	public static final String Event_KEY = "page_event";

	/**
	 * Ĭ�ϵ�ÿҳ��¼��
	 */
	public static final int DEFAULT_PERPAGE_COUNT = 10;

	private String pginfoid;

	/**
	 * �ܼ�¼��
	 */
	private int itemCount;

	/**
	 * ÿҳ��ʾ�ļ�¼��
	 */
	private int perPage;

	/**
	 * ��ҳ��
	 */
	private int pageCount;

	/**
	 * ��ǰҳ��
	 */
	private int nowpage;

	/**
	 * ���캯��������ÿ�εķ�ҳ����(�ǵ�һ�η�ҳ����)��ʱ��
	 * 
	 * @param request
	 * @param pginfoid
	 */
	public PageInfo(HttpServletRequest request, String pginfoid) {
		this.pginfoid = pginfoid;
		if (request != null) {
			request.setAttribute(KEY, this);
			String strpageinfo = request.getParameter(KEY);
			if (strpageinfo != null && !strpageinfo.equals("")) {
				parseStr(strpageinfo);
			}
		}
		validateSession(request);
	}

	/**
	 * ���캯�������ڵ�һ�η�ҳ������ʱ�򣬳�ʼ����<br>
	 * ʹ��Ĭ�ϵ�ÿҳ10�м�¼����
	 * 
	 * @param itemCount
	 *            �ܼ�¼��
	 * @param request
	 * @param pginfoid
	 *            �洢��session�з�ҳ��Ϣ����ѯ��䣬ÿҳ��¼������ǰҳ�룩��������
	 */
	public PageInfo(int itemCount, HttpServletRequest request, String pginfoid) {
		this(itemCount, DEFAULT_PERPAGE_COUNT, request, pginfoid);
	}

	/**
	 * ���캯�������ڵ�һ�η�ҳ������ʱ�򣬳�ʼ�������Զ���ÿҳ�ļ�¼����
	 * 
	 * @param itemCount
	 * @param perPage
	 *            �Զ���ÿҳ�ļ�¼��
	 * @param request
	 * @param pginfoid
	 *            �洢��session�з�ҳ��Ϣ��ÿҳ��¼������ǰҳ�룩��������
	 */
	public PageInfo(int itemCount, int perPage, HttpServletRequest request,
			String pginfoid) {
		init(itemCount, perPage);
		this.pginfoid = pginfoid;
		if (request != null) {
			request.setAttribute(KEY, this);
		}
		validateSession(request);
	}

	/**
	 * ��ʼ����ҳ�����ã��ܵļ�¼������ҳ����ÿҳ��ʾ��¼������ǰҳ��
	 * 
	 * @param itemCount
	 *            �ܵļ�¼��
	 * @param perPage
	 *            ÿҳ��ʾ��¼��
	 */
	public void init(int itemCount, int perPage) {
		this.itemCount = itemCount;
		this.perPage = perPage;
		this.nowpage = 1;
		if (itemCount % perPage == 0) {
			this.pageCount = (itemCount / perPage);
		} else {
			this.pageCount = (itemCount / perPage) + 1;
		}
	}

	/**
	 * ������֤��<br/> ��ҳ��Ϣ��Ҫ�洢��2��λ�ã���������session�кͿͻ��˵�ҳ���С�
	 * ����������ڸ��º���֤�û��ύ�������еķ�ҳ��Ϣ�ͷ�����session�еķ�ҳ��Ϣ�Ƿ�һ�¡� Ŀǰֻʹ�ø��¹��ܣ���֤�����ݲ��ṩ��
	 * 
	 * @param request
	 */
	private void validateSession(HttpServletRequest request) {
		if (!isPageEvent(request)) {
			Object obj = request.getSession().getAttribute(pginfoid);
			if (obj != null) {
				PageInfo pi = (PageInfo) obj;
				setPerPage(pi.getPerPage());
				setNowpage(pi.getNowpage());
				request.getSession().setAttribute(pginfoid, this);
			}
		} else {
			request.getSession().setAttribute(pginfoid, this);
		}
	}

	/**
	 * �Ƿ�Ϊҳ���л��¼�
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isPageEvent(HttpServletRequest request) {
		String param = request.getParameter(Event_KEY);
		if (param != null && param.equalsIgnoreCase("y")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * �����û�����ķ�ҳ�����¼���request�������ݵķ�ҳ��Ϣ���ܼ�¼��,��ҳ��,ÿҳ��ʾ��,��ǰҳ,��
	 * 
	 * @param pginfostr
	 *            ��ʽΪ���ܼ�¼��,��ҳ��,ÿҳ��ʾ��,��ǰҳ,
	 */
	public void parseStr(String pginfostr) {
		String[] pis = pginfostr.split(",");
		if (pis.length == 4) {
			this.itemCount = Integer.parseInt(pis[0]);
			this.pageCount = Integer.parseInt(pis[1]);
			setPerPage(Integer.parseInt(pis[2]));
			setNowpage(Integer.parseInt(pis[3]));
		}
	}

	/**
	 * ����ҳ��Ϣ��ϳ��ַ��������ݸ��û�
	 * 
	 * @return ��ʽΪ���ܼ�¼������ҳ����ÿҳ��ʾ������ǰҳ��
	 */
	public String getPginfostr() {
		StringBuffer sb = new StringBuffer();
		sb.append(itemCount + ",");
		sb.append(pageCount + ",");
		sb.append(perPage + ",");
		sb.append(nowpage + ",");
		return sb.toString();
	}

	public int getItemCount() {
		return itemCount;
	}

	public int getNowpage() {
		return nowpage;
	}

	public void setNowpage(int nowpage) {
		if (nowpage > pageCount) {
			this.nowpage = 1;
		} else {
			this.nowpage = nowpage;
		}
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		init(itemCount, perPage);
	}

	/**
	 * ��ǰ��ҳ�Ŀ�ʼ��¼index, ��0��ʼ����
	 * 
	 * @return -1��ʾ��������
	 */
	public int getPageStartIndex() {
		int index = (nowpage - 1) * perPage;
		if (index < itemCount) {
			return index;
		}
		return -1;
	}

	public int getPageEndIndex() {
		int index = nowpage * perPage - 1;
		if (index >= itemCount) {
			return itemCount - 1;
		} else {
			return index;
		}
	}

	/**
	 * ��ȡ��ǰҳ�ļ�¼��
	 * 
	 * @return
	 */
	public int getPageItemCount() {

		return getPageEndIndex() - getPageStartIndex() + 1;

	}
}
