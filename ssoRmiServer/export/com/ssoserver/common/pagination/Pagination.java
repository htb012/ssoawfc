/*
 * @(#)pagination.java	1.00 2008-11-12����04:37:13
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ssoserver.common.pagination;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * ��ҳ����<br>
 * 1����һ��ҳ������������Ƿ�ҳ�����¼�����ʼ����ҳ����ȡ�ܼ�¼������ҳ����ÿҳ��¼�������õ�ǰҳΪ��һҳ��<br>
 * 2��ÿ�η�ҳ������ҳ�¼���������ȡ��ҳ��Ϣ���ܼ�¼��,��ҳ��,ÿҳ��ʾ��,��ǰҳ��<br>
 * 3�����ݿ�ʼ��¼���͵�ǰҳ��¼������ѯ��ǰҳ��¼
 * 
 * @author H.T.B
 * 
 */
public class Pagination {

	private Logger logger;

	/**
	 * 
	 */
	public Pagination() {
		// TODO Auto-generated constructor stub
		logger = Logger.getLogger(this.getClass());
	}

	/**
	 * ִ�з�ҳ����
	 * 
	 * @param paginationSvc
	 *            ���ݷ��ʷ������
	 * @param queryString
	 *            ��ѯ���
	 * @param request
	 * @param response
	 * @return
	 */
	public List execute(PaginationSvc paginationSvc, String queryString,
			HttpServletRequest request, HttpServletResponse response) {
		List list = new ArrayList();
		PageInfo pginfo = null;// ��ҳ��Ϣ
		// ÿ�η�ҳ����������ҳ�����¼������ǵ�һ�ε�ҳ�洴����
		if (PageInfo.isPageEvent(request)
				&& request.getSession().getAttribute("first") != null) {
			pginfo = new PageInfo(request, "flist");
		} else { // �ڵ�һ��ҳ�洴�����������µĲ�ѯ���ʱ��(��ʼ��)
			int dataNumber = paginationSvc.getItemCount(queryString);// �ܼ�¼��
			pginfo = new PageInfo(dataNumber, request, "flist");// ��ҳ��Ϣ
			pginfo.setNowpage(1);// ���õ�ǰҳΪ��һҳ
			request.getSession().setAttribute("first", "yes");
		}
		// ���ݷ�ҳ�Ĳ�����ѯ��ҳ���������
		list = paginationSvc.query(queryString, pginfo.getPageStartIndex(),
				pginfo.getPageItemCount());
		return list;
	}
}
