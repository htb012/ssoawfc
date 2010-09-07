/**
 * 
 */
package jp.co.pegatron.service;

import java.util.List;

import jp.co.pegatron.domain.dao.BulletinDAO;
import jp.co.pegatron.domain.model.Bulletin;

import com.ssoserver.common.pagination.PaginationSvc;

/**
 * @author HTB
 * 
 */
public class BulletinSvc extends BulletinDAO implements PaginationSvc {
	private static BulletinSvc bulletinSvc;

	/**
	 * 
	 */
	private BulletinSvc() {
		// TODO Auto-generated constructor stub
	}

	public static BulletinSvc getInstance() {
		if (bulletinSvc == null) {
			bulletinSvc = new BulletinSvc();
		}
		return bulletinSvc;
	}

	public void deleteByid(Integer id) {
		Bulletin bulletin = this.findById(id);
		this.delete(bulletin);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssoserver.common.pagination.PaginationSvc#getItemCount(java.lang.String)
	 */
	public int getItemCount(String queryString) {
		return this.findByHql(queryString).size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssoserver.common.pagination.PaginationSvc#query(java.lang.String,
	 *      int, int)
	 */
	public List query(String queryString, int startIndex, int itemNum) {
		return super.query(queryString, startIndex, itemNum);
	}

}
