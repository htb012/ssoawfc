/*
 * @(#)pagination.java	1.00 2008-11-12下午04:37:13
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
 * 分页处理<br>
 * 1、第一次页面载入或其他非分页操作事件，初始化分页（读取总记录数、总页数、每页记录数、设置当前页为第一页）<br>
 * 2、每次翻页交互或翻页事件触发，读取分页信息（总记录数,总页数,每页显示数,当前页）<br>
 * 3、根据开始记录数和当前页记录数，查询当前页记录
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
	 * 执行分页操作
	 * 
	 * @param paginationSvc
	 *            数据访问服务对象
	 * @param queryString
	 *            查询语句
	 * @param request
	 * @param response
	 * @return
	 */
	public List execute(PaginationSvc paginationSvc, String queryString,
			HttpServletRequest request, HttpServletResponse response) {
		List list = new ArrayList();
		PageInfo pginfo = null;// 分页信息
		// 每次翻页交互，即分页操作事件（不是第一次的页面创建）
		if (PageInfo.isPageEvent(request)
				&& request.getSession().getAttribute("first") != null) {
			pginfo = new PageInfo(request, "flist");
		} else { // 在第一次页面创建，即创建新的查询结果时候(初始化)
			int dataNumber = paginationSvc.getItemCount(queryString);// 总记录数
			pginfo = new PageInfo(dataNumber, request, "flist");// 分页信息
			pginfo.setNowpage(1);// 设置当前页为第一页
			request.getSession().setAttribute("first", "yes");
		}
		// 根据分页的参数查询本页的相关数据
		list = paginationSvc.query(queryString, pginfo.getPageStartIndex(),
				pginfo.getPageItemCount());
		return list;
	}
}
