/**
 * 
 */
package jp.co.pegatron.service;

import java.util.List;

import jp.co.pegatron.domain.dao.CustomerDAO;

import com.ssoserver.common.pagination.PaginationSvc;

/**
 * @author HTB
 * 
 */
public class CustomerSvc extends CustomerDAO implements PaginationSvc {

	private static CustomerSvc customerSvc;

	/**
	 * 
	 */
	private CustomerSvc() {
		// TODO Auto-generated constructor stub
	}

	public static CustomerSvc getInstance() {
		if (customerSvc == null) {
			customerSvc = new CustomerSvc();
		}
		return customerSvc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssoserver.common.pagination.PaginationSvc#getItemCount()
	 */
	public int getItemCount(String queryString) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List query(String arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return null;
	}


}
