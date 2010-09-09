package com.ssoserver.common.accessValidate;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.Logger;
import com.ssoserver.business.authMgr.AuthMgr;
import com.ssoserver.business.userMgr.UserMgr;
import com.ssoserver.common.rmi.AuthMgrFactory;

import java.io.*;
import java.rmi.RemoteException;

/**
 * ��¼��֤����<br>
 * ��Դ�ı��� δ��¼���û��ڷ����ܱ������ļ���ʱ��
 * 
 * @author H.T.B
 * 
 */

public class AccessValidateFilter extends HttpServlet implements Filter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AuthMgr authMgr;

	private FilterConfig filterConfig;

	private Logger logger;

	// Handle the passed-in FilterConfig
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		authMgr = AuthMgrFactory.getAuthMgr();
		logger = Logger.getLogger(this.getClass());
	}

	/**
	 * <b>��֤�Ƿ����sso���ѵ�¼��ȫ��,û�еĻ���תҳ�浽��ȫ�빤������ȡһ����ȫ��</b><br>
	 * ��ȫ�������ڵ����¼ϵͳ�ϵ�Ψһʶ�� ��ÿ��<br>
	 * ��¼�����¼ϵͳ����Ϊ�����һ��Ψһ�İ�ȫ�룬<br>
	 * �ڻỰ������ʱ��Ψһ��ȫ��Ҳ�����ڡ�
	 * 
	 * @param request
	 * @return �Ƿ��а�ȫ��
	 */
	public boolean validateSecrityKey(HttpServletRequest request,
			HttpServletResponse response) {
		if (!"/sso".equals(request.getContextPath())) {
			String secKey = request.getParameter(UserMgr.SECKEY);
			// �Ƿ��������������а�ȫ����Ϣ
			if (secKey == null || "".equals(secKey)) {
				secKey = "" + request.getSession().getAttribute(UserMgr.SECKEY);
			} else {
				request.getSession().setAttribute(UserMgr.SECKEY, secKey);
			}
			if (secKey == null || "".equals(secKey) || "null".equals(secKey)) {
				try {
					String accessStr = this.createReturnAccessStr(request,
							authMgr.getURL(AuthMgr.SECFAC));
					response.sendRedirect(accessStr);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return false;
			}
		}
		return true;
	}

	/**
	 * �����ط��ķ��ʵ�URL�ַ���
	 * 
	 * @param request
	 * @param accessSite
	 * @return
	 * @throws RemoteException
	 */
	private String createReturnAccessStr(HttpServletRequest request,
			String accessSite) {
		StringBuffer accessStr = new StringBuffer();
		accessStr.append(accessSite);
		accessStr.append("?accessnetsite=");
		accessStr.append(request.getRequestURL());
		logger.debug("accessStr:" + accessStr.toString());
		return accessStr.toString();
	}

	/**
	 * <b>����ת��SSO�û���֤����</b><br>
	 * 1���ж��û��Ƿ��а�ȫ��<br>
	 * 2������ǲ����а�ȫ�룬��ȥ��ȡ��ȫ��<br>
	 * 3����֤�û��İ�ȫ���Ƿ��Ѿ�ע��<br>
	 * 4������û��İ�ȫ�뻹û��ע�ᣬ����Ҫ��¼��֤��ע�ᰲȫ��<br>
	 * 5���ж��û��Դ���Դ��Ȩ�ޣ�û��->��ת��˵�����棬�н���Դչʾ���û���
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		if (!this.validateSecrityKey(httpServletRequest, httpServletResponse)) {
			return;
		}
		logger.debug("access resource name:"
				+ httpServletRequest.getRequestURI());
		String seckey = ""
				+ httpServletRequest.getSession().getAttribute(UserMgr.SECKEY);
		if (seckey == null || "".equals(seckey) || "null".equals(seckey)) {
			seckey = httpServletRequest.getSession().getId();
		}
		if (authMgr.isNeedLogin(seckey, httpServletRequest.getRequestURI())) {
			String accessStr = this.createReturnAccessStr(httpServletRequest,
					authMgr.getURL(AuthMgr.LOGON));
			((HttpServletResponse) response).sendRedirect(accessStr);
		} else {
			if (authMgr.isHasAuth(seckey, httpServletRequest.getRequestURI())) {
				filterChain.doFilter(request, response);
			} else {
				String accessStr = this.createReturnAccessStr(
						httpServletRequest, authMgr.getURL(AuthMgr.NOAUTH));
				((HttpServletResponse) response).sendRedirect(accessStr);
			}
		}
	}

	// Clean up resources
	public void destroy() {
	}
}
