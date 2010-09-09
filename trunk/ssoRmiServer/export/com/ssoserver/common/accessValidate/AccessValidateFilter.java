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
 * 登录验证处理<br>
 * 资源的保护 未登录的用户在访问受保护的文件的时候将
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
	 * <b>验证是否存在sso的已登录安全码,没有的话跳转页面到安全码工厂，获取一个安全码</b><br>
	 * 安全码用于在单点登录系统上的唯一识别， 在每次<br>
	 * 登录单点登录系统都将为其分配一个唯一的安全码，<br>
	 * 在会话结束的时候，唯一安全码也将过期。
	 * 
	 * @param request
	 * @return 是否含有安全码
	 */
	public boolean validateSecrityKey(HttpServletRequest request,
			HttpServletResponse response) {
		if (!"/sso".equals(request.getContextPath())) {
			String secKey = request.getParameter(UserMgr.SECKEY);
			// 是否在请求链接上有安全码信息
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
	 * 创建回返的访问的URL字符串
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
	 * <b>控跳转到SSO用户验证界面</b><br>
	 * 1、判断用户是否含有安全码<br>
	 * 2、如果是不含有安全码，将去获取安全码<br>
	 * 3、验证用户的安全码是否已经注册<br>
	 * 4、如果用户的安全码还没有注册，就需要登录验证，注册安全码<br>
	 * 5、判断用户对此资源有权限；没有->跳转到说明界面，有将资源展示给用户。
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
