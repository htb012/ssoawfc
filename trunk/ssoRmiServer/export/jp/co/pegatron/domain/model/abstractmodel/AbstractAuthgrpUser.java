package jp.co.pegatron.domain.model.abstractmodel;

import jp.co.pegatron.domain.model.AuthgrpUserId;
import jp.co.pegatron.domain.model.Authoritygroup;
import jp.co.pegatron.domain.model.User;

/**
 * AbstractAuthgrpUser entity provides the base persistence definition of the
 * AuthgrpUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractAuthgrpUser implements java.io.Serializable {

	// Fields

	private AuthgrpUserId id;
	private Authoritygroup authoritygroup;
	private User user;

	// Constructors

	/** default constructor */
	public AbstractAuthgrpUser() {
	}

	/** full constructor */
	public AbstractAuthgrpUser(AuthgrpUserId id, Authoritygroup authoritygroup,
			User user) {
		this.id = id;
		this.authoritygroup = authoritygroup;
		this.user = user;
	}

	// Property accessors

	public AuthgrpUserId getId() {
		return this.id;
	}

	public void setId(AuthgrpUserId id) {
		this.id = id;
	}

	public Authoritygroup getAuthoritygroup() {
		return this.authoritygroup;
	}

	public void setAuthoritygroup(Authoritygroup authoritygroup) {
		this.authoritygroup = authoritygroup;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}