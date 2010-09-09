package jp.co.pegatron.domain.model.abstractmodel;

import java.util.Date;

import jp.co.pegatron.domain.model.Repairorder;
import jp.co.pegatron.domain.model.User;

/**
 * AbstractContact entity provides the base persistence definition of the
 * Contact entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractContact implements java.io.Serializable {

	// Fields

	private Integer contactid;
	private Repairorder repairorder;
	private User user;
	private Date contactdatetime;
	private String note;
	private String customresponse;
	private String customrequire;
	private String contactvalidate;
	private String contactextend;

	// Constructors

	/** default constructor */
	public AbstractContact() {
	}

	/** minimal constructor */
	public AbstractContact(Repairorder repairorder, User user, String note) {
		this.repairorder = repairorder;
		this.user = user;
		this.note = note;
	}

	/** full constructor */
	public AbstractContact(Repairorder repairorder, User user,
			Date contactdatetime, String note, String customresponse,
			String customrequire, String contactvalidate, String contactextend) {
		this.repairorder = repairorder;
		this.user = user;
		this.contactdatetime = contactdatetime;
		this.note = note;
		this.customresponse = customresponse;
		this.customrequire = customrequire;
		this.contactvalidate = contactvalidate;
		this.contactextend = contactextend;
	}

	// Property accessors

	public Integer getContactid() {
		return this.contactid;
	}

	public void setContactid(Integer contactid) {
		this.contactid = contactid;
	}

	public Repairorder getRepairorder() {
		return this.repairorder;
	}

	public void setRepairorder(Repairorder repairorder) {
		this.repairorder = repairorder;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getContactdatetime() {
		return this.contactdatetime;
	}

	public void setContactdatetime(Date contactdatetime) {
		this.contactdatetime = contactdatetime;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCustomresponse() {
		return this.customresponse;
	}

	public void setCustomresponse(String customresponse) {
		this.customresponse = customresponse;
	}

	public String getCustomrequire() {
		return this.customrequire;
	}

	public void setCustomrequire(String customrequire) {
		this.customrequire = customrequire;
	}

	public String getContactvalidate() {
		return this.contactvalidate;
	}

	public void setContactvalidate(String contactvalidate) {
		this.contactvalidate = contactvalidate;
	}

	public String getContactextend() {
		return this.contactextend;
	}

	public void setContactextend(String contactextend) {
		this.contactextend = contactextend;
	}

}