package com.ssoserver.utils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class CharsetEncodeFilter extends HttpServlet implements Filter {
	private FilterConfig filterConfig;

	// Handle the passed-in FilterConfig
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) {
		try {
			request.setCharacterEncoding("UTF-8");
			filterChain.doFilter(request, response);
		} catch (ServletException sx) {
			filterConfig.getServletContext().log(sx.getMessage());
		} catch (IOException iox) {
			filterConfig.getServletContext().log(iox.getMessage());
		}
	}

	// Clean up resources
	public void destroy() {
	}
}
