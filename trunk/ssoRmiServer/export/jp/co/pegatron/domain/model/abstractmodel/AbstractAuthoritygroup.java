package jp.co.pegatron.domain.model.abstractmodel;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractAuthoritygroup entity provides the base persistence definition of the
 * Authoritygroup entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractAuthoritygroup implements java.io.Serializable {

	// Fields

	private Integer authgroupid;
	private String authgroupname;
	private Date authgroupcreatetime;
	private String authgroupextend;
	private String authgroupdiscription;
	private Set authgrpUsers = new HashSet(0);
	private Set authgrpAuths = new HashSet(0);
	private Set organizations = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractAuthoritygroup() {
	}

	/** minimal constructor */
	public AbstractAuthoritygroup(String authgroupname, Date authgroupcreatetime) {
		this.authgroupname = authgroupname;
		this.authgroupcreatetime = authgroupcreatetime;
	}

	/** full constructor */
	public AbstractAuthoritygroup(String authgroupname,
			Date authgroupcreatetime, String authgroupextend,
			String authgroupdiscription, Set authgrpUsers, Set authgrpAuths,
			Set organizations) {
		this.authgroupname = authgroupname;
		this.authgroupcreatetime = authgroupcreatetime;
		this.authgroupextend = authgroupextend;
		this.authgroupdiscription = authgroupdiscription;
		this.authgrpUsers = authgrpUsers;
		this.authgrpAuths = authgrpAuths;
		this.organizations = organizations;
	}

	// Property accessors

	public Integer getAuthgroupid() {
		return this.authgroupid;
	}

	public void setAuthgroupid(Integer authgroupid) {
		this.authgroupid = authgroupid;
	}

	public String getAuthgroupname() {
		return this.authgroupname;
	}

	public void setAuthgroupname(String authgroupname) {
		this.authgroupname = authgroupname;
	}

	public Date getAuthgroupcreatetime() {
		return this.authgroupcreatetime;
	}

	public void setAuthgroupcreatetime(Date authgroupcreatetime) {
		this.authgroupcreatetime = authgroupcreatetime;
	}

	public String getAuthgroupextend() {
		return this.authgroupextend;
	}

	public void setAuthgroupextend(String authgroupextend) {
		this.authgroupextend = authgroupextend;
	}

	public String getAuthgroupdiscription() {
		return this.authgroupdiscription;
	}

	public void setAuthgroupdiscription(String authgroupdiscription) {
		this.authgroupdiscription = authgroupdiscription;
	}

	public Set getAuthgrpUsers() {
		return this.authgrpUsers;
	}

	public void setAuthgrpUsers(Set authgrpUsers) {
		this.authgrpUsers = authgrpUsers;
	}

	public Set getAuthgrpAuths() {
		return this.authgrpAuths;
	}

	public void setAuthgrpAuths(Set authgrpAuths) {
		this.authgrpAuths = authgrpAuths;
	}

	public Set getOrganizations() {
		return this.organizations;
	}

	public void setOrganizations(Set organizations) {
		this.organizations = organizations;
	}

}