package jp.co.pegatron.domain.model.abstractmodel;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jp.co.pegatron.domain.model.Agent;
import jp.co.pegatron.domain.model.Customer;
import jp.co.pegatron.domain.model.Model;
import jp.co.pegatron.domain.model.Organization;
import jp.co.pegatron.domain.model.User;
import jp.co.pegatron.hibernate.EnumRepairOrderState;

/**
 * AbstractRepairorder entity provides the base persistence definition of the
 * Repairorder entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractRepairorder implements java.io.Serializable {

	// Fields

	private Integer repairorderid;
	private Agent agent;
	private Organization organization;
	private Model model;
	private Customer customer;
	private User user;
	private String rmano;
	private Date startdate;
	private Date checkindatetime;
	private String location;
	private String parts;
	private Byte stock;
	private String waitparts;
	private String sn;
	private String pn;
	private String warranty;
	private String validate;
	private String problem;
	private String photo;
	private Byte isagentrepair;
	private EnumRepairOrderState repairorderstate;
	private String repairorderexpend;
	private Set contacts = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractRepairorder() {
	}

	/** minimal constructor */
	public AbstractRepairorder(String rmano, Date startdate, String location,
			String problem, EnumRepairOrderState repairorderstate) {
		this.rmano = rmano;
		this.startdate = startdate;
		this.location = location;
		this.problem = problem;
		this.repairorderstate = repairorderstate;
	}

	/** full constructor */
	public AbstractRepairorder(Agent agent, Organization organization,
			Model model, Customer customer, User user, String rmano,
			Date startdate, Date checkindatetime, String location,
			String parts, Byte stock, String waitparts, String sn, String pn,
			String warranty, String validate, String problem, String photo,
			Byte isagentrepair, EnumRepairOrderState repairorderstate,
			String repairorderexpend, Set contacts) {
		this.agent = agent;
		this.organization = organization;
		this.model = model;
		this.customer = customer;
		this.user = user;
		this.rmano = rmano;
		this.startdate = startdate;
		this.checkindatetime = checkindatetime;
		this.location = location;
		this.parts = parts;
		this.stock = stock;
		this.waitparts = waitparts;
		this.sn = sn;
		this.pn = pn;
		this.warranty = warranty;
		this.validate = validate;
		this.problem = problem;
		this.photo = photo;
		this.isagentrepair = isagentrepair;
		this.repairorderstate = repairorderstate;
		this.repairorderexpend = repairorderexpend;
		this.contacts = contacts;
	}

	// Property accessors

	public Integer getRepairorderid() {
		return this.repairorderid;
	}

	public void setRepairorderid(Integer repairorderid) {
		this.repairorderid = repairorderid;
	}

	public Agent getAgent() {
		return this.agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Model getModel() {
		return this.model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRmano() {
		return this.rmano;
	}

	public void setRmano(String rmano) {
		this.rmano = rmano;
	}

	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getCheckindatetime() {
		return this.checkindatetime;
	}

	public void setCheckindatetime(Date checkindatetime) {
		this.checkindatetime = checkindatetime;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getParts() {
		return this.parts;
	}

	public void setParts(String parts) {
		this.parts = parts;
	}

	public Byte getStock() {
		return this.stock;
	}

	public void setStock(Byte stock) {
		this.stock = stock;
	}

	public String getWaitparts() {
		return this.waitparts;
	}

	public void setWaitparts(String waitparts) {
		this.waitparts = waitparts;
	}

	public String getSn() {
		return this.sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getPn() {
		return this.pn;
	}

	public void setPn(String pn) {
		this.pn = pn;
	}

	/**
	 * @return the warranty
	 */
	public String getWarranty() {
		return warranty;
	}

	/**
	 * @param warranty
	 *            the warranty to set
	 */
	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public String getValidate() {
		return this.validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getProblem() {
		return this.problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Byte getIsagentrepair() {
		return this.isagentrepair;
	}

	public void setIsagentrepair(Byte isagentrepair) {
		this.isagentrepair = isagentrepair;
	}

	public EnumRepairOrderState getRepairorderstate() {
		return this.repairorderstate;
	}

	public void setRepairorderstate(EnumRepairOrderState repairorderstate) {
		this.repairorderstate = repairorderstate;
	}

	public String getRepairorderexpend() {
		return this.repairorderexpend;
	}

	public void setRepairorderexpend(String repairorderexpend) {
		this.repairorderexpend = repairorderexpend;
	}

	public Set getContacts() {
		return this.contacts;
	}

	public void setContacts(Set contacts) {
		this.contacts = contacts;
	}

}