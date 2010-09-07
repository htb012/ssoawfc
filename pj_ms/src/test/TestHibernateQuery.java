/**
 * 
 */
package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.ssoserver.utils.GetPropMessage;
import com.ssoserver.utils.HqlFactory;

import jp.co.pegatron.service.RepairOrderSvc;

/**
 * @author HTB
 * 
 */
public class TestHibernateQuery {

	/**
	 * 
	 */
	public TestHibernateQuery() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("customer.customername", null);
		paraMap.put("model.modelname", null);
		paraMap.put("repOrd.rmano", null);
		paraMap.put("repOrd.sn", "11");
		String hql = HqlFactory.getInstance().buildSearchHql("repOrdSearch",
				paraMap);
		List list = RepairOrderSvc.getInstance().findByHql(hql);
		System.out.println(list.size());

	}

}
