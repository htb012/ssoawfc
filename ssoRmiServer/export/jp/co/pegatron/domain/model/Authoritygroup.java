package jp.co.pegatron.domain.model;

import java.util.Date;
import java.util.Set;

import jp.co.pegatron.domain.model.abstractmodel.AbstractAuthoritygroup;

/**
 * Authoritygroup entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Authoritygroup extends AbstractAuthoritygroup implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Authoritygroup() {
	}

	/** minimal constructor */
	public Authoritygroup(String authgroupname, Date authgroupcreatetime) {
		super(authgroupname, authgroupcreatetime);
	}

	/** full constructor */
	public Authoritygroup(String authgroupname, Date authgroupcreatetime,
			String authgroupextend, String authgroupdiscription,
			Set authgrpUsers, Set authgrpAuths, Set organizations) {
		super(authgroupname, authgroupcreatetime, authgroupextend,
				authgroupdiscription, authgrpUsers, authgrpAuths, organizations);
	}

}
