/**
 * 
 */
package jp.co.pegatron.service;

import java.util.List;

import jp.co.pegatron.domain.dao.ContactDAO;

import com.ssoserver.common.pagination.PaginationSvc;

/**
 * @author HTB
 *
 */
public class ContactSvc extends ContactDAO implements PaginationSvc {
	private static ContactSvc contactSvc;
	/**
	 * 
	 */
	private ContactSvc() {
		// TODO Auto-generated constructor stub
	}
	
	public static ContactSvc getInstance(){
		if(contactSvc == null){
			contactSvc = new ContactSvc();
		}
		return contactSvc;
	}

	/* (non-Javadoc)
	 * @see com.ssoserver.common.pagination.PaginationSvc#getItemCount(java.lang.String)
	 */
	public int getItemCount(String arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.ssoserver.common.pagination.PaginationSvc#query(java.lang.String, int, int)
	 */
	public List query(String arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
