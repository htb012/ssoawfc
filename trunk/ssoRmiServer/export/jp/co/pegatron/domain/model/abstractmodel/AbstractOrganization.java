package jp.co.pegatron.domain.model.abstractmodel;

import java.util.HashSet;
import java.util.Set;

import jp.co.pegatron.domain.model.Authoritygroup;

/**
 * AbstractOrganization entity provides the base persistence definition of the
 * Organization entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractOrganization implements java.io.Serializable {

	// Fields

	private Integer orgid;
	private Authoritygroup authoritygroup;
	private String orgname;
	private Integer manager;
	private Integer uplevel;
	private String orgextend;
	private String orgdiscription;
	private Set orgUsers = new HashSet(0);
	private Set repairorders = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractOrganization() {
	}

	/** minimal constructor */
	public AbstractOrganization(String orgname) {
		this.orgname = orgname;
	}

	/** full constructor */
	public AbstractOrganization(Authoritygroup authoritygroup, String orgname,
			Integer manager, Integer uplevel, String orgextend,
			String orgdiscription, Set orgUsers, Set repairorders) {
		this.authoritygroup = authoritygroup;
		this.orgname = orgname;
		this.manager = manager;
		this.uplevel = uplevel;
		this.orgextend = orgextend;
		this.orgdiscription = orgdiscription;
		this.orgUsers = orgUsers;
		this.repairorders = repairorders;
	}

	// Property accessors

	public Integer getOrgid() {
		return this.orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public Authoritygroup getAuthoritygroup() {
		return this.authoritygroup;
	}

	public void setAuthoritygroup(Authoritygroup authoritygroup) {
		this.authoritygroup = authoritygroup;
	}

	public String getOrgname() {
		return this.orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public Integer getManager() {
		return this.manager;
	}

	public void setManager(Integer manager) {
		this.manager = manager;
	}

	public Integer getUplevel() {
		return this.uplevel;
	}

	public void setUplevel(Integer uplevel) {
		this.uplevel = uplevel;
	}

	public String getOrgextend() {
		return this.orgextend;
	}

	public void setOrgextend(String orgextend) {
		this.orgextend = orgextend;
	}

	public String getOrgdiscription() {
		return this.orgdiscription;
	}

	public void setOrgdiscription(String orgdiscription) {
		this.orgdiscription = orgdiscription;
	}

	public Set getOrgUsers() {
		return this.orgUsers;
	}

	public void setOrgUsers(Set orgUsers) {
		this.orgUsers = orgUsers;
	}

	public Set getRepairorders() {
		return this.repairorders;
	}

	public void setRepairorders(Set repairorders) {
		this.repairorders = repairorders;
	}

}