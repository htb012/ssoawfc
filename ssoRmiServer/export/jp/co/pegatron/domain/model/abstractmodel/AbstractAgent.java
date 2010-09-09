package jp.co.pegatron.domain.model.abstractmodel;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractAgent entity provides the base persistence definition of the Agent
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractAgent implements java.io.Serializable {

	// Fields

	private Integer agentid;
	private String agentname;
	private String agentphone;
	private String agentemail;
	private String agentextend;
	private Set repairorders = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractAgent() {
	}

	/** minimal constructor */
	public AbstractAgent(String agentname, String agentphone) {
		this.agentname = agentname;
		this.agentphone = agentphone;
	}

	/** full constructor */
	public AbstractAgent(String agentname, String agentphone,
			String agentemail, String agentextend, Set repairorders) {
		this.agentname = agentname;
		this.agentphone = agentphone;
		this.agentemail = agentemail;
		this.agentextend = agentextend;
		this.repairorders = repairorders;
	}

	// Property accessors

	public Integer getAgentid() {
		return this.agentid;
	}

	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}

	public String getAgentname() {
		return this.agentname;
	}

	public void setAgentname(String agentname) {
		this.agentname = agentname;
	}

	public String getAgentphone() {
		return this.agentphone;
	}

	public void setAgentphone(String agentphone) {
		this.agentphone = agentphone;
	}

	public String getAgentemail() {
		return this.agentemail;
	}

	public void setAgentemail(String agentemail) {
		this.agentemail = agentemail;
	}

	public String getAgentextend() {
		return this.agentextend;
	}

	public void setAgentextend(String agentextend) {
		this.agentextend = agentextend;
	}

	public Set getRepairorders() {
		return this.repairorders;
	}

	public void setRepairorders(Set repairorders) {
		this.repairorders = repairorders;
	}

}