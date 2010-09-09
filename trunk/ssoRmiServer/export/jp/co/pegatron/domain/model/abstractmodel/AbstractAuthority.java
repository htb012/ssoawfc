package jp.co.pegatron.domain.model.abstractmodel;

import java.util.HashSet;
import java.util.Set;

import jp.co.pegatron.domain.model.Metaauthority;
import jp.co.pegatron.domain.model.Resource;

/**
 * AbstractAuthority entity provides the base persistence definition of the
 * Authority entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractAuthority implements java.io.Serializable {

	// Fields

	private Integer authid;
	private Metaauthority metaauthority;
	private Resource resource;
	private String authextend;
	private String authdiscription;
	private Set authgrpAuths = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractAuthority() {
	}

	/** minimal constructor */
	public AbstractAuthority(Metaauthority metaauthority, Resource resource) {
		this.metaauthority = metaauthority;
		this.resource = resource;
	}

	/** full constructor */
	public AbstractAuthority(Metaauthority metaauthority, Resource resource,
			String authextend, String authdiscription, Set authgrpAuths) {
		this.metaauthority = metaauthority;
		this.resource = resource;
		this.authextend = authextend;
		this.authdiscription = authdiscription;
		this.authgrpAuths = authgrpAuths;
	}

	// Property accessors

	public Integer getAuthid() {
		return this.authid;
	}

	public void setAuthid(Integer authid) {
		this.authid = authid;
	}

	public Metaauthority getMetaauthority() {
		return this.metaauthority;
	}

	public void setMetaauthority(Metaauthority metaauthority) {
		this.metaauthority = metaauthority;
	}

	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public String getAuthextend() {
		return this.authextend;
	}

	public void setAuthextend(String authextend) {
		this.authextend = authextend;
	}

	public String getAuthdiscription() {
		return this.authdiscription;
	}

	public void setAuthdiscription(String authdiscription) {
		this.authdiscription = authdiscription;
	}

	public Set getAuthgrpAuths() {
		return this.authgrpAuths;
	}

	public void setAuthgrpAuths(Set authgrpAuths) {
		this.authgrpAuths = authgrpAuths;
	}

}