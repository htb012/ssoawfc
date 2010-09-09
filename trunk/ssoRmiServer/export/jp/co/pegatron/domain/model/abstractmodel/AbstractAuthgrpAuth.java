package jp.co.pegatron.domain.model.abstractmodel;

import jp.co.pegatron.domain.model.AuthgrpAuthId;
import jp.co.pegatron.domain.model.Authority;
import jp.co.pegatron.domain.model.Authoritygroup;

/**
 * AbstractAuthgrpAuth entity provides the base persistence definition of the
 * AuthgrpAuth entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractAuthgrpAuth implements java.io.Serializable {

	// Fields

	private AuthgrpAuthId id;
	private Authoritygroup authoritygroup;
	private Authority authority;

	// Constructors

	/** default constructor */
	public AbstractAuthgrpAuth() {
	}

	/** full constructor */
	public AbstractAuthgrpAuth(AuthgrpAuthId id, Authoritygroup authoritygroup,
			Authority authority) {
		this.id = id;
		this.authoritygroup = authoritygroup;
		this.authority = authority;
	}

	// Property accessors

	public AuthgrpAuthId getId() {
		return this.id;
	}

	public void setId(AuthgrpAuthId id) {
		this.id = id;
	}

	public Authoritygroup getAuthoritygroup() {
		return this.authoritygroup;
	}

	public void setAuthoritygroup(Authoritygroup authoritygroup) {
		this.authoritygroup = authoritygroup;
	}

	public Authority getAuthority() {
		return this.authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

}