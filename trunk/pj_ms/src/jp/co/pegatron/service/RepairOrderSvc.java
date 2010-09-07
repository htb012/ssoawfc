package jp.co.pegatron.service;

import java.util.List;

import jp.co.pegatron.domain.dao.RepairorderDAO;
import jp.co.pegatron.domain.model.Repairorder;

import com.ssoserver.common.pagination.PaginationSvc;

/**
 * 维修管理服务，提供数据库查询和分页的服务。 同时在数据库管理的时候，限制部分的查询功能。
 * 
 * @author HTB
 * 
 */
public class RepairOrderSvc extends RepairorderDAO implements PaginationSvc {

	private static RepairOrderSvc instance;

	private RepairOrderSvc() {
	}

	public static RepairOrderSvc getInstance() {
		if (instance == null) {
			instance = new RepairOrderSvc();
		}
		return instance;
	}

	public void deleteUniqueByRmano(String rmano) {
		Repairorder order = this.findUniqueByProperty("rmano", rmano);
		delete(order);
	}

	public void delteById(Integer id) {
		Repairorder repairorder = this.findById(id);
		delete(repairorder);
	}

	public int getItemCount(String queryString) {
		return this.findByHql(queryString).size();
	}

	public List query(String queryString, int startIndex, int itemNum) {
		return super.query(queryString, startIndex, itemNum);
	}
}
