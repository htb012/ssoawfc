package com.ssoserver.jsp.jtl;

import java.rmi.RemoteException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.ssoserver.business.authMgr.AuthMgr;
import com.ssoserver.common.rmi.AuthMgrFactory;

public class CheckPermisstion extends TagSupport {
	// 要判断的资源文件的名称
	private String res;

	/**
	 * @param resource
	 *            the resource to set
	 */
	public void setRes(String res) {
		this.res = res;
	}

	public int doStartTag() {
		HttpSession session = this.pageContext.getSession();
		String secKey = session.getAttribute("secKey").toString();
		AuthMgr authMgr = AuthMgrFactory.getAuthMgr();
		boolean hasAuth = false;
		try {
			hasAuth = authMgr.isHasAuth(secKey, res);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (hasAuth) {
			return this.EVAL_BODY_INCLUDE;
		} else {
			return this.SKIP_BODY;
		}
	}
}
