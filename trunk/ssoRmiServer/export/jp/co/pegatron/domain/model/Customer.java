package jp.co.pegatron.domain.model;

import java.util.Set;

import jp.co.pegatron.domain.model.abstractmodel.AbstractCustomer;

/**
 * Customer entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Customer extends AbstractCustomer implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Customer() {
	}

	/** minimal constructor */
	public Customer(String customername, String customerphone) {
		super(customername, customerphone);
	}

	/** full constructor */
	public Customer(String customername, String customerphone,
			String customeremail, String customerextend, Set repairorders) {
		super(customername, customerphone, customeremail, customerextend,
				repairorders);
	}

}
