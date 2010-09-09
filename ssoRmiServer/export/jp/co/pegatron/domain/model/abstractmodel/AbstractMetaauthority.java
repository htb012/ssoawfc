package jp.co.pegatron.domain.model.abstractmodel;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractMetaauthority entity provides the base persistence definition of the
 * Metaauthority entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractMetaauthority implements java.io.Serializable {

	// Fields

	private Integer metaauthid;
	private String metaauthname;
	private String metaauthextend;
	private String metaauthdiscription;
	private Set authorities = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractMetaauthority() {
	}

	/** minimal constructor */
	public AbstractMetaauthority(String metaauthname) {
		this.metaauthname = metaauthname;
	}

	/** full constructor */
	public AbstractMetaauthority(String metaauthname, String metaauthextend,
			String metaauthdiscription, Set authorities) {
		this.metaauthname = metaauthname;
		this.metaauthextend = metaauthextend;
		this.metaauthdiscription = metaauthdiscription;
		this.authorities = authorities;
	}

	// Property accessors

	public Integer getMetaauthid() {
		return this.metaauthid;
	}

	public void setMetaauthid(Integer metaauthid) {
		this.metaauthid = metaauthid;
	}

	public String getMetaauthname() {
		return this.metaauthname;
	}

	public void setMetaauthname(String metaauthname) {
		this.metaauthname = metaauthname;
	}

	public String getMetaauthextend() {
		return this.metaauthextend;
	}

	public void setMetaauthextend(String metaauthextend) {
		this.metaauthextend = metaauthextend;
	}

	public String getMetaauthdiscription() {
		return this.metaauthdiscription;
	}

	public void setMetaauthdiscription(String metaauthdiscription) {
		this.metaauthdiscription = metaauthdiscription;
	}

	public Set getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(Set authorities) {
		this.authorities = authorities;
	}

}