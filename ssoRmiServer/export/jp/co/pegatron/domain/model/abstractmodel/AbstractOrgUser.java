package jp.co.pegatron.domain.model.abstractmodel;

import jp.co.pegatron.domain.model.OrgUserId;
import jp.co.pegatron.domain.model.Organization;
import jp.co.pegatron.domain.model.User;

/**
 * AbstractOrgUser entity provides the base persistence definition of the
 * OrgUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractOrgUser implements java.io.Serializable {

	// Fields

	private OrgUserId id;
	private Organization organization;
	private User user;

	// Constructors

	/** default constructor */
	public AbstractOrgUser() {
	}

	/** full constructor */
	public AbstractOrgUser(OrgUserId id, Organization organization, User user) {
		this.id = id;
		this.organization = organization;
		this.user = user;
	}

	// Property accessors

	public OrgUserId getId() {
		return this.id;
	}

	public void setId(OrgUserId id) {
		this.id = id;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}