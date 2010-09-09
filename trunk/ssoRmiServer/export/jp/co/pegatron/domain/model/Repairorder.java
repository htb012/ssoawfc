package jp.co.pegatron.domain.model;

import java.util.Date;
import java.util.Set;

import jp.co.pegatron.domain.model.abstractmodel.AbstractRepairorder;
import jp.co.pegatron.hibernate.EnumRepairOrderState;

/**
 * Repairorder entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Repairorder extends AbstractRepairorder implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Repairorder() {
	}

	/** minimal constructor */
	public Repairorder(String rmano, Date startdate, String location,
			String problem, EnumRepairOrderState repairorderstate) {
		super(rmano, startdate, location, problem, repairorderstate);
	}

	/** full constructor */
	public Repairorder(Agent agent, Organization organization, Model model,
			Customer customer, User user, String rmano, Date startdate,
			Date checkindatetime, String location, String parts, Byte stock,
			String waitparts, String sn, String pn, String warranty,
			String validate, String problem, String photo, Byte isagentrepair,
			EnumRepairOrderState repairorderstate, String repairorderexpend,
			Set contacts) {
		super(agent, organization, model, customer, user, rmano, startdate,
				checkindatetime, location, parts, stock, waitparts, sn, pn,
				warranty, validate, problem, photo, isagentrepair,
				repairorderstate, repairorderexpend, contacts);
	}

}
