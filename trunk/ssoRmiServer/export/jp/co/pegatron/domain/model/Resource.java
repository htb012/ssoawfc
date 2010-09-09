package jp.co.pegatron.domain.model;

import java.util.Set;

import jp.co.pegatron.domain.model.abstractmodel.AbstractResource;

/**
 * Resource entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Resource extends AbstractResource implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Resource() {
	}

	/** minimal constructor */
	public Resource(String resname) {
		super(resname);
	}

	/** full constructor */
	public Resource(String resname, String resextend, String resdiscription,
			Set authorities) {
		super(resname, resextend, resdiscription, authorities);
	}

}
