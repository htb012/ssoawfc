package jp.co.pegatron.domain.model;

import java.util.Set;

import jp.co.pegatron.domain.model.abstractmodel.AbstractMetaauthority;

/**
 * Metaauthority entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Metaauthority extends AbstractMetaauthority implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Metaauthority() {
	}

	/** minimal constructor */
	public Metaauthority(String metaauthname) {
		super(metaauthname);
	}

	/** full constructor */
	public Metaauthority(String metaauthname, String metaauthextend,
			String metaauthdiscription, Set authorities) {
		super(metaauthname, metaauthextend, metaauthdiscription, authorities);
	}

}
