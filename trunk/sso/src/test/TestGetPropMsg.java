/**
 * 
 */
package test;

import com.ssoserver.utils.GetPropMessage;

/**
 * @author HTB
 * 
 */
public class TestGetPropMsg {

	/**
	 * 
	 */
	public TestGetPropMsg() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String hql = GetPropMessage.getUTF8Message("allUser");
		System.err.println(hql);
	}

}
