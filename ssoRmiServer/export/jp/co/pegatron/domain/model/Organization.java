package jp.co.pegatron.domain.model;

import java.util.Set;

import jp.co.pegatron.domain.model.abstractmodel.AbstractOrganization;

/**
 * Organization entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Organization extends AbstractOrganization implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Organization() {
	}

	/** minimal constructor */
	public Organization(String orgname) {
		super(orgname);
	}

	/** full constructor */
	public Organization(Authoritygroup authoritygroup, String orgname,
			Integer manager, Integer uplevel, String orgextend,
			String orgdiscription, Set orgUsers, Set repairorders) {
		super(authoritygroup, orgname, manager, uplevel, orgextend,
				orgdiscription, orgUsers, repairorders);
	}

}
