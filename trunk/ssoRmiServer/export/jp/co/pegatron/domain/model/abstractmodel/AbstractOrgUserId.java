package jp.co.pegatron.domain.model.abstractmodel;

/**
 * AbstractOrgUserId entity provides the base persistence definition of the
 * OrgUserId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractOrgUserId implements java.io.Serializable {

	// Fields

	private Integer orgid;
	private Integer userid;

	// Constructors

	/** default constructor */
	public AbstractOrgUserId() {
	}

	/** full constructor */
	public AbstractOrgUserId(Integer orgid, Integer userid) {
		this.orgid = orgid;
		this.userid = userid;
	}

	// Property accessors

	public Integer getOrgid() {
		return this.orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractOrgUserId))
			return false;
		AbstractOrgUserId castOther = (AbstractOrgUserId) other;

		return ((this.getOrgid() == castOther.getOrgid()) || (this.getOrgid() != null
				&& castOther.getOrgid() != null && this.getOrgid().equals(
				castOther.getOrgid())))
				&& ((this.getUserid() == castOther.getUserid()) || (this
						.getUserid() != null
						&& castOther.getUserid() != null && this.getUserid()
						.equals(castOther.getUserid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOrgid() == null ? 0 : this.getOrgid().hashCode());
		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		return result;
	}

}