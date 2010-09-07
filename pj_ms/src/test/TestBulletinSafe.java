/**
 * 
 */
package test;

import java.util.Date;

import jp.co.pegatron.domain.model.Bulletin;
import jp.co.pegatron.hibernate.EnumBulletinType;
import jp.co.pegatron.service.BulletinSvc;
import jp.co.pegatron.service.UserSvc;

/**
 * @author HTB
 * 
 */
public class TestBulletinSafe {

	/**
	 * 
	 */
	public TestBulletinSafe() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 50; i++) {
			Bulletin bulletin = new Bulletin();
			bulletin.setUser(UserSvc.getInstance().findById(1));
			bulletin.setContent("test");
			bulletin.setPublishdate(new Date());
			bulletin.setBulletintype(EnumBulletinType.all);
			BulletinSvc.getInstance().save(bulletin);
		}
	}
}
