package jp.co.pegatron.domain.model;

import jp.co.pegatron.domain.model.abstractmodel.AbstractAuthgrpUser;

/**
 * AuthgrpUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class AuthgrpUser extends AbstractAuthgrpUser implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public AuthgrpUser() {
	}

	/** full constructor */
	public AuthgrpUser(AuthgrpUserId id, Authoritygroup authoritygroup,
			User user) {
		super(id, authoritygroup, user);
	}

}
