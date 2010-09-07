/**
 * 
 */
package jp.co.pegatron.service;

import java.util.List;

import jp.co.pegatron.domain.dao.UserDAO;

import com.ssoserver.common.pagination.PaginationSvc;

/**
 * @author HTB
 * 
 */
public class UserSvc extends UserDAO implements PaginationSvc {
	private static UserSvc userSvc;

	/**
	 * 
	 */
	private UserSvc() {
		// TODO Auto-generated constructor stub
	}

	public static UserSvc getInstance() {
		if (userSvc == null) {
			userSvc = new UserSvc();
		}
		return userSvc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssoserver.common.pagination.PaginationSvc#getItemCount(java.lang.String)
	 */
	public int getItemCount(String arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssoserver.common.pagination.PaginationSvc#query(java.lang.String,
	 *      int, int)
	 */
	public List query(String arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
