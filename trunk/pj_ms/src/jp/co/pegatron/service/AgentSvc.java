/**
 * 
 */
package jp.co.pegatron.service;

import java.util.List;

import jp.co.pegatron.domain.dao.AgentDAO;
import jp.co.pegatron.domain.model.Agent;
import jp.co.pegatron.domain.model.Model;

import com.ssoserver.common.pagination.PaginationSvc;

/**
 * @author HTB
 * 
 */
public class AgentSvc extends AgentDAO implements PaginationSvc {

	private static AgentSvc agentSvc;

	/**
	 * 
	 */
	private AgentSvc() {
		// TODO Auto-generated constructor stub
	}

	public static AgentSvc getInstance() {
		if (agentSvc == null) {
			agentSvc = new AgentSvc();
		}
		return agentSvc;
	}

	public void deleteById(Integer id) {
		Agent agent = this.findById(id);
		this.delete(agent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssoserver.common.pagination.PaginationSvc#getItemCount()
	 */
	public int getItemCount(String queryString) {
		// TODO Auto-generated method stub
		return this.findByHql(queryString).size();
	}
}
