package jp.co.pegatron.domain.model;

import jp.co.pegatron.domain.model.abstractmodel.AbstractOrgUser;

/**
 * OrgUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class OrgUser extends AbstractOrgUser implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public OrgUser() {
	}

	/** full constructor */
	public OrgUser(OrgUserId id, Organization organization, User user) {
		super(id, organization, user);
	}

}
