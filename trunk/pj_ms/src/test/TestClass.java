package test;

import java.util.Date;

import jp.co.pegatron.hibernate.EnumRepairOrderState;

import com.ssoserver.utils.FormatDate;

public class TestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date now = new Date();
		System.err.println(FormatDate.formatDateToString(now));

		new TestClass().testObject();

		System.err.println(EnumRepairOrderState.修理完了);
	}

	public void testObject() {
		TestObject obj = new TestObject("123", "456");
		this.changeStr(obj);
		System.err.println(obj.str1);
		System.err.println(EnumRepairOrderState.更新済み);
	}

	public void changeStr(TestObject obj) {
		obj.str1 = "htb";
	}

}

class TestObject {

	public String str1;
	public String str2;

	public TestObject(String str1, String str2) {
		// TODO Auto-generated constructor stub
		this.str1 = str1;
		this.str2 = str2;
	}

}
