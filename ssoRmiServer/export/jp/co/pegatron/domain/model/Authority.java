package jp.co.pegatron.domain.model;

import java.util.Set;

import jp.co.pegatron.domain.model.abstractmodel.AbstractAuthority;

/**
 * Authority entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Authority extends AbstractAuthority implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Authority() {
	}

	/** minimal constructor */
	public Authority(Metaauthority metaauthority, Resource resource) {
		super(metaauthority, resource);
	}

	/** full constructor */
	public Authority(Metaauthority metaauthority, Resource resource,
			String authextend, String authdiscription, Set authgrpAuths) {
		super(metaauthority, resource, authextend, authdiscription,
				authgrpAuths);
	}

}
