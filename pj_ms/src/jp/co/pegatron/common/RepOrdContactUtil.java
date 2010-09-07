/**
 * 
 */
package jp.co.pegatron.common;

import java.util.Date;
import java.util.Set;

import com.ssoserver.utils.FormatDate;

import jp.co.pegatron.domain.model.Contact;

/**
 * @author HTB
 * 
 */
public class RepOrdContactUtil {

	/**
	 * 
	 */
	private RepOrdContactUtil() {
		// TODO Auto-generated constructor stub
	}

	public static String getLastContactdatetime(Set contactSet) {
		if (!contactSet.isEmpty()) {
			Object[] contacts = contactSet.toArray();
			Date contactdatetime = ((Contact) contacts[contacts.length - 1])
					.getContactdatetime();
			String contactdatetimeStr = FormatDate
					.formatDateTimeToString(contactdatetime);
			return contactdatetimeStr;
		}
		return "--------";
	}

	public static String getLastContacter(Set contactSet) {
		if (!contactSet.isEmpty()) {
			Object[] contacts = contactSet.toArray();
			String contactdatetime = ((Contact) contacts[contacts.length - 1])
					.getUser().getRealname();
			return contactdatetime;
		}
		return "--------";
	}

}
