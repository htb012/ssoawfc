package jp.co.pegatron.domain.model;

import jp.co.pegatron.domain.model.abstractmodel.AbstractAuthgrpAuthId;

/**
 * AuthgrpAuthId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class AuthgrpAuthId extends AbstractAuthgrpAuthId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public AuthgrpAuthId() {
	}

	/** full constructor */
	public AuthgrpAuthId(Integer resid, Integer metaauthid,
			Integer authgroupid, Integer authid) {
		super(resid, metaauthid, authgroupid, authid);
	}

}
