/**
 * 
 */
package test;

import java.util.Date;

import jp.co.pegatron.domain.model.Contact;
import jp.co.pegatron.service.ContactSvc;
import jp.co.pegatron.service.UserSvc;

/**
 * @author HTB
 * 
 */
public class TestSafeContact {

	/**
	 * 
	 */
	public TestSafeContact() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Contact contact = new Contact();
		contact.setContactid(19);
		contact.setUser(UserSvc.getInstance().findById(1));
		contact.setContactdatetime(new Date());
		ContactSvc.getInstance().save(contact);
	}

}
