package jp.co.pegatron.domain.model.abstractmodel;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCustomer entity provides the base persistence definition of the
 * Customer entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCustomer implements java.io.Serializable {

	// Fields

	private Integer customerid;
	private String customername;
	private String customerphone;
	private String customeremail;
	private String customerextend;
	private Set repairorders = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCustomer() {
	}

	/** minimal constructor */
	public AbstractCustomer(String customername, String customerphone) {
		this.customername = customername;
		this.customerphone = customerphone;
	}

	/** full constructor */
	public AbstractCustomer(String customername, String customerphone,
			String customeremail, String customerextend, Set repairorders) {
		this.customername = customername;
		this.customerphone = customerphone;
		this.customeremail = customeremail;
		this.customerextend = customerextend;
		this.repairorders = repairorders;
	}

	// Property accessors

	public Integer getCustomerid() {
		return this.customerid;
	}

	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return this.customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCustomerphone() {
		return this.customerphone;
	}

	public void setCustomerphone(String customerphone) {
		this.customerphone = customerphone;
	}

	public String getCustomeremail() {
		return this.customeremail;
	}

	public void setCustomeremail(String customeremail) {
		this.customeremail = customeremail;
	}

	public String getCustomerextend() {
		return this.customerextend;
	}

	public void setCustomerextend(String customerextend) {
		this.customerextend = customerextend;
	}

	public Set getRepairorders() {
		return this.repairorders;
	}

	public void setRepairorders(Set repairorders) {
		this.repairorders = repairorders;
	}

}