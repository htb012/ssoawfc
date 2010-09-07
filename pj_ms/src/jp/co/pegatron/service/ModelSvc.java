/**
 * 
 */
package jp.co.pegatron.service;

import java.util.List;

import jp.co.pegatron.domain.dao.ModelDAO;
import jp.co.pegatron.domain.model.Model;

import com.ssoserver.common.pagination.PaginationSvc;

/**
 * @author HTB
 * 
 */
public class ModelSvc extends ModelDAO implements PaginationSvc {

	private static ModelSvc modelSvc;

	/**
	 * 
	 */
	private ModelSvc() {
		// TODO Auto-generated constructor stub
	}

	public static ModelSvc getInstance() {
		if (modelSvc == null) {
			modelSvc = new ModelSvc();
		}
		return modelSvc;
	}

	public void deleteById(Integer id) {
		Model model = this.findById(id);
		this.delete(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssoserver.common.pagination.PaginationSvc#getItemCount()
	 */
	public int getItemCount(String queryString) {
		return this.findByHql(queryString).size();
	}

	public List query(String queryString, int startIndex, int itemNum) {
		return super.query(queryString, startIndex, itemNum);
	}

}
