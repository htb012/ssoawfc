package jp.co.pegatron.domain.model;

import jp.co.pegatron.domain.model.abstractmodel.AbstractAuthgrpAuth;

/**
 * AuthgrpAuth entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class AuthgrpAuth extends AbstractAuthgrpAuth implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public AuthgrpAuth() {
	}

	/** full constructor */
	public AuthgrpAuth(AuthgrpAuthId id, Authoritygroup authoritygroup,
			Authority authority) {
		super(id, authoritygroup, authority);
	}

}
