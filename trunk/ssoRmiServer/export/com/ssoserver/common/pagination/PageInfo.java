/*
 * @(#)PageInfo.java	1.00 2008-11-10下午12:40:00
 *
 * Copyright 2008 Tuotuo.org, Inc. All rights reserved.
 * Tuotuo.org PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ssoserver.common.pagination;

import javax.servlet.http.HttpServletRequest;

/**
 * WEB分页服务程序,需要配合 page.js来完成
 * 
 */
public class PageInfo {

	/**
	 * 在request请求中传递分页信息的（总的记录数、总页数、每页显示记录数、当前页码）的标识符
	 */
	public static final String KEY = "page_pginfo";

	/**
	 * 页面切换事件标识符
	 */
	public static final String Event_KEY = "page_event";

	/**
	 * 默认的每页记录数
	 */
	public static final int DEFAULT_PERPAGE_COUNT = 10;

	private String pginfoid;

	/**
	 * 总记录数
	 */
	private int itemCount;

	/**
	 * 每页显示的记录数
	 */
	private int perPage;

	/**
	 * 总页数
	 */
	private int pageCount;

	/**
	 * 当前页码
	 */
	private int nowpage;

	/**
	 * 构造函数，用于每次的翻页操作(非第一次分页创建)的时候
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
	 * 构造函数，用于第一次分页创建的时候，初始化。<br>
	 * 使用默认的每页10行记录数。
	 * 
	 * @param itemCount
	 *            总记录数
	 * @param request
	 * @param pginfoid
	 *            存储于session中分页信息（查询语句，每页记录数，当前页码）的属性名
	 */
	public PageInfo(int itemCount, HttpServletRequest request, String pginfoid) {
		this(itemCount, DEFAULT_PERPAGE_COUNT, request, pginfoid);
	}

	/**
	 * 构造函数，用于第一次分页创建的时候，初始化。可自定义每页的记录数。
	 * 
	 * @param itemCount
	 * @param perPage
	 *            自定义每页的记录数
	 * @param request
	 * @param pginfoid
	 *            存储于session中分页信息（每页记录数，当前页码）的属性名
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
	 * 初始化分页，设置：总的记录数、总页数、每页显示记录数、当前页码
	 * 
	 * @param itemCount
	 *            总的记录数
	 * @param perPage
	 *            每页显示记录数
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
	 * 数据验证。<br/> 分页信息主要存储于2个位置，服务器端session中和客户端的页面中。
	 * 这个方法用于更新和验证用户提交的请求中的分页信息和服务器session中的分页信息是否一致。 目前只使用更新功能，验证功能暂不提供。
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
	 * 是否为页面切换事件
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
	 * 解析用户请求的分页操作事件的request中所传递的分页信息（总记录数,总页数,每页显示数,当前页,）
	 * 
	 * @param pginfostr
	 *            格式为：总记录数,总页数,每页显示数,当前页,
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
	 * 将分页信息组合成字符串，传递给用户
	 * 
	 * @return 格式为：总记录数，总页数，每页显示数，当前页，
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
	 * 当前分页的开始记录index, 从0开始计算
	 * 
	 * @return -1表示超出界限
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
	 * 获取当前页的记录数
	 * 
	 * @return
	 */
	public int getPageItemCount() {

		return getPageEndIndex() - getPageStartIndex() + 1;

	}
}
