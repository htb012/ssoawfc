package jp.co.pegatron.domain.model.abstractmodel;

/**
 * AbstractAuthgrpUserId entity provides the base persistence definition of the
 * AuthgrpUserId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractAuthgrpUserId implements java.io.Serializable {

	// Fields

	private Integer authgroupid;
	private Integer userid;

	// Constructors

	/** default constructor */
	public AbstractAuthgrpUserId() {
	}

	/** full constructor */
	public AbstractAuthgrpUserId(Integer authgroupid, Integer userid) {
		this.authgroupid = authgroupid;
		this.userid = userid;
	}

	// Property accessors

	public Integer getAuthgroupid() {
		return this.authgroupid;
	}

	public void setAuthgroupid(Integer authgroupid) {
		this.authgroupid = authgroupid;
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
		if (!(other instanceof AbstractAuthgrpUserId))
			return false;
		AbstractAuthgrpUserId castOther = (AbstractAuthgrpUserId) other;

		return ((this.getAuthgroupid() == castOther.getAuthgroupid()) || (this
				.getAuthgroupid() != null
				&& castOther.getAuthgroupid() != null && this.getAuthgroupid()
				.equals(castOther.getAuthgroupid())))
				&& ((this.getUserid() == castOther.getUserid()) || (this
						.getUserid() != null
						&& castOther.getUserid() != null && this.getUserid()
						.equals(castOther.getUserid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getAuthgroupid() == null ? 0 : this.getAuthgroupid()
						.hashCode());
		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		return result;
	}

}