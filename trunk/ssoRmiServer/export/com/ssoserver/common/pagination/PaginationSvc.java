/*
 * @(#)awfSvc.java	1.00 2008-11-12下午04:38:32
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
	 * 查询一个分页内的记录，根据查询语句、记录的startIndex和查询数，筛选相应的记录
	 * 
	 * @param queryString
	 *            查询语句
	 * @param startIndex
	 *            记录的开始号码
	 * @param itemNum
	 *            查询数
	 * @return 记录集
	 */
	public List query(String queryString, int startIndex, int itemNum);

	/**
	 * 获取记录数
	 * 
	 * @return 全部的记录数
	 */
	public int getItemCount(String queryString);
}
