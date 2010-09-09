package jp.co.pegatron.domain.model;

import java.util.Set;

import jp.co.pegatron.domain.model.abstractmodel.AbstractAgent;

/**
 * Agent entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Agent extends AbstractAgent implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Agent() {
	}

	/** minimal constructor */
	public Agent(String agentname, String agentphone) {
		super(agentname, agentphone);
	}

	/** full constructor */
	public Agent(String agentname, String agentphone, String agentemail,
			String agentextend, Set repairorders) {
		super(agentname, agentphone, agentemail, agentextend, repairorders);
	}

}
