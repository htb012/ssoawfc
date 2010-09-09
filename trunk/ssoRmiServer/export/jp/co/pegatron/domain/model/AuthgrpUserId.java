package jp.co.pegatron.domain.model;

import jp.co.pegatron.domain.model.abstractmodel.AbstractAuthgrpUserId;

/**
 * AuthgrpUserId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class AuthgrpUserId extends AbstractAuthgrpUserId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public AuthgrpUserId() {
	}

	/** full constructor */
	public AuthgrpUserId(Integer authgroupid, Integer userid) {
		super(authgroupid, userid);
	}

}
