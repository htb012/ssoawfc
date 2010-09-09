package jp.co.pegatron.domain.model.abstractmodel;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractResource entity provides the base persistence definition of the
 * Resource entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractResource implements java.io.Serializable {

	// Fields

	private Integer resid;
	private String resname;
	private String resextend;
	private String resdiscription;
	private Set authorities = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractResource() {
	}

	/** minimal constructor */
	public AbstractResource(String resname) {
		this.resname = resname;
	}

	/** full constructor */
	public AbstractResource(String resname, String resextend,
			String resdiscription, Set authorities) {
		this.resname = resname;
		this.resextend = resextend;
		this.resdiscription = resdiscription;
		this.authorities = authorities;
	}

	// Property accessors

	public Integer getResid() {
		return this.resid;
	}

	public void setResid(Integer resid) {
		this.resid = resid;
	}

	public String getResname() {
		return this.resname;
	}

	public void setResname(String resname) {
		this.resname = resname;
	}

	public String getResextend() {
		return this.resextend;
	}

	public void setResextend(String resextend) {
		this.resextend = resextend;
	}

	public String getResdiscription() {
		return this.resdiscription;
	}

	public void setResdiscription(String resdiscription) {
		this.resdiscription = resdiscription;
	}

	public Set getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(Set authorities) {
		this.authorities = authorities;
	}

}