package test;

import java.util.List;
import java.util.Locale;

import org.hibernate.Query;

import jp.co.pegatron.service.RepairOrderSvc;

import com.ssoserver.utils.GetPropMessage;

public class TestGetProMessage {

	public TestGetProMessage() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.err.println(GetPropMessage.getUTF8Message("repairerRepOrd"));
		List list = RepairOrderSvc.getInstance().findByHql(
				GetPropMessage.getUTF8Message("repairerRepOrd"));
		System.out.println(list.size());
	}
}
