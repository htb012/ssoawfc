package jp.co.pegatron.domain.model;

import jp.co.pegatron.domain.model.abstractmodel.AbstractOrgUserId;

/**
 * OrgUserId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class OrgUserId extends AbstractOrgUserId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public OrgUserId() {
	}

	/** full constructor */
	public OrgUserId(Integer orgid, Integer userid) {
		super(orgid, userid);
	}

}
