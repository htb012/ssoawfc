/*
 * @(#)awfSvc.java	1.00 2008-11-12����04:38:32
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ssoserver.common.pagination;

import java.util.List;

/**
 * @author H.T.B
 * 
 */
public interface PaginationSvc {
	/**
	 * ��ѯһ����ҳ�ڵļ�¼�����ݲ�ѯ��䡢��¼��startIndex�Ͳ�ѯ����ɸѡ��Ӧ�ļ�¼
	 * 
	 * @param queryString
	 *            ��ѯ���
	 * @param startIndex
	 *            ��¼�Ŀ�ʼ����
	 * @param itemNum
	 *            ��ѯ��
	 * @return ��¼��
	 */
	public List query(String queryString, int startIndex, int itemNum);

	/**
	 * ��ȡ��¼��
	 * 
	 * @return ȫ���ļ�¼��
	 */
	public int getItemCount(String queryString);
}
