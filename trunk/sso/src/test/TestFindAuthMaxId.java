/**
 * 
 */
package test;

import com.sso.service.AuthSvc;

/**
 * @author HTB
 *
 */
public class TestFindAuthMaxId {

	/**
	 * 
	 */
	public TestFindAuthMaxId() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AuthSvc.getInstance().getMaxCount();
	}

}
