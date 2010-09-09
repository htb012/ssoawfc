/**
 * 
 */
package com.ssoserver.utils;

import java.awt.print.Paper;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ssoserver.utils.GetPropMessage;

/**
 * @author HTB
 * 
 */
public class HqlFactory {
	private static HqlFactory hqlFactory;
	private Logger logger;

	/**
	 * 私有构造函数
	 * 
	 */
	private HqlFactory() {
		logger = Logger.getLogger(HqlFactory.class);
	}

	public static HqlFactory getInstance() {
		if (hqlFactory == null) {
			hqlFactory = new HqlFactory();
		}
		return hqlFactory;
	}

	/**
	 * 创建并返回查询hql语句。从资源文件中通过资源名找到原始的字符串。在原有字符串中加入所提供的参数。
	 * 如果在资源文件中的原始hql有5个问号，则传入的List对象必须带有5个参数。否者抛出参数越界例外。 但允许某一个参数的值为null.
	 * 
	 * @param resKey
	 *            资源名
	 * @param paraList
	 *            参数列表
	 * @return 创建好的sql语句
	 */
	public String buildSearchHql(String resKey, Map<String, String> parpMap) {
		StringBuffer hql = new StringBuffer();
		hql.append(GetPropMessage.getUTF8Message(resKey));
		Set<String> keySet = parpMap.keySet();
		for (String key : keySet) {
			String paraValue = parpMap.get(key);
			if (paraValue == null || "".equals(paraValue)) {
				int start = hql.indexOf("?");
				hql.replace(start, start + 1, "(" + key + " like '%' or " + key
						+ " is null )");
			} else {
				int start = hql.indexOf("?");
				hql.replace(start, start + 1, key+" like '%" + paraValue + "%'");
			}
		}
		logger.debug(hql);
		return hql.toString();
	}

	/**
	 * 创建并返回hql语句。从资源文件中通过资源名找到原始的字符串。在原有字符串中加入所提供的参数。
	 * 如果在资源文件中的原始hql有5个问号，则传入的List对象必须带有5个参数。否者抛出参数越界例外。 但允许某一个参数的值为null.
	 * 
	 * @param resKey
	 *            资源名
	 * @param paraList
	 *            参数列表
	 * @return 创建好的sql语句
	 */
	public String buildGeneralHql(String resKey, List<String> paraList) {
		StringBuffer hql = new StringBuffer();
		hql.append(GetPropMessage.getUTF8Message(resKey));
		for (int i = 0; i < paraList.size(); i++) {
			String parameter = paraList.get(i);
			if (parameter == null || "".equals(parameter)) {
				int start = hql.indexOf("?");
				hql.replace(start, start + 1, "''");
			} else {
				int start = hql.indexOf("?");
				hql.replace(start, start + 1, "'" + parameter + "'");
			}
		}
		logger.debug(hql);
		return hql.toString();
	}

}
