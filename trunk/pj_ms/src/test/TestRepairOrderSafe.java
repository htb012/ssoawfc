/**
 * 
 */
package test;

import java.util.Date;
import java.util.Random;
import jp.co.pegatron.domain.model.Repairorder;
import jp.co.pegatron.hibernate.EnumRepairOrderState;
import jp.co.pegatron.service.CustomerSvc;
import jp.co.pegatron.service.ModelSvc;
import jp.co.pegatron.service.RepairOrderSvc;

/**
 * @author HTB
 * 
 */
public class TestRepairOrderSafe {

	/**
	 * 
	 */
	public TestRepairOrderSafe() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random r = new Random();
		for (int i = 0; i < 3; i++) {
			Repairorder repairorder = new Repairorder();
			repairorder.setRmano("test" + r.nextFloat());
			repairorder.setStartdate(new Date());
			repairorder.setLocation("testLocation");
			repairorder.setProblem("no problem");
			repairorder.setIsagentrepair((byte) 0);
			repairorder.setModel(ModelSvc.getInstance().findById(new Integer(1)));
			repairorder.setCustomer(CustomerSvc.getInstance().findById(
					new Integer(0)));
			repairorder.setRepairorderstate(EnumRepairOrderState.登録済み);
			RepairOrderSvc.getInstance().save(repairorder);
		}
		System.err.println(RepairOrderSvc.getInstance().findAll().size());
	}
}
