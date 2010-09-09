package jp.co.pegatron.domain.model;

import java.util.Date;

import jp.co.pegatron.domain.model.abstractmodel.AbstractContact;

/**
 * Contact entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Contact extends AbstractContact implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Contact() {
	}

	/** minimal constructor */
	public Contact(Repairorder repairorder, User user, String note) {
		super(repairorder, user, note);
	}

	/** full constructor */
	public Contact(Repairorder repairorder, User user, Date contactdatetime,
			String note, String customresponse, String customrequire,
			String contactvalidate, String contactextend) {
		super(repairorder, user, contactdatetime, note, customresponse,
				customrequire, contactvalidate, contactextend);
	}

}
