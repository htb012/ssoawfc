package test;

import java.util.Date;

import jp.co.pegatron.domain.model.Repairorder;
import jp.co.pegatron.service.RepairOrderSvc;

public class TestTime {

	public TestTime() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Repairorder order = RepairOrderSvc.getInstance().findById(30);
		Date startDate = order.getStartdate();
		System.out.println(startDate.toGMTString());
		System.out.println(startDate.toLocaleString());
		System.out.println(startDate.toString());
		Date date = new Date();
		System.out.println(date.toGMTString());
		System.out.println(date.toLocaleString());
		System.out.println(date.toString()); 
		System.out.println(date.toGMTString());
		System.out.println(date.toLocaleString());
		System.out.println(date.toString());
	}

}
