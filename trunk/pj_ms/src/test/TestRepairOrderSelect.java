/**
 * 
 */
package test;

import java.util.List;

import jp.co.pegatron.domain.model.Repairorder;
import jp.co.pegatron.service.RepairOrderSvc;

/**
 * @author HTB
 * 
 */
public class TestRepairOrderSelect {

	/**
	 * 
	 */
	public TestRepairOrderSelect() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Repairorder> repordList = RepairOrderSvc.getInstance().findAll();
		for (Repairorder repord : repordList) {
			System.err.println(repord.getWarranty());
		}
	}

}
