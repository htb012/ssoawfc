package jp.co.pegatron.domain.model.abstractmodel;

/**
 * AbstractAuthgrpAuthId entity provides the base persistence definition of the
 * AuthgrpAuthId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractAuthgrpAuthId implements java.io.Serializable {

	// Fields

	private Integer resid;
	private Integer metaauthid;
	private Integer authgroupid;
	private Integer authid;

	// Constructors

	/** default constructor */
	public AbstractAuthgrpAuthId() {
	}

	/** full constructor */
	public AbstractAuthgrpAuthId(Integer resid, Integer metaauthid,
			Integer authgroupid, Integer authid) {
		this.resid = resid;
		this.metaauthid = metaauthid;
		this.authgroupid = authgroupid;
		this.authid = authid;
	}

	// Property accessors

	public Integer getResid() {
		return this.resid;
	}

	public void setResid(Integer resid) {
		this.resid = resid;
	}

	public Integer getMetaauthid() {
		return this.metaauthid;
	}

	public void setMetaauthid(Integer metaauthid) {
		this.metaauthid = metaauthid;
	}

	public Integer getAuthgroupid() {
		return this.authgroupid;
	}

	public void setAuthgroupid(Integer authgroupid) {
		this.authgroupid = authgroupid;
	}

	public Integer getAuthid() {
		return this.authid;
	}

	public void setAuthid(Integer authid) {
		this.authid = authid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractAuthgrpAuthId))
			return false;
		AbstractAuthgrpAuthId castOther = (AbstractAuthgrpAuthId) other;

		return ((this.getResid() == castOther.getResid()) || (this.getResid() != null
				&& castOther.getResid() != null && this.getResid().equals(
				castOther.getResid())))
				&& ((this.getMetaauthid() == castOther.getMetaauthid()) || (this
						.getMetaauthid() != null
						&& castOther.getMetaauthid() != null && this
						.getMetaauthid().equals(castOther.getMetaauthid())))
				&& ((this.getAuthgroupid() == castOther.getAuthgroupid()) || (this
						.getAuthgroupid() != null
						&& castOther.getAuthgroupid() != null && this
						.getAuthgroupid().equals(castOther.getAuthgroupid())))
				&& ((this.getAuthid() == castOther.getAuthid()) || (this
						.getAuthid() != null
						&& castOther.getAuthid() != null && this.getAuthid()
						.equals(castOther.getAuthid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getResid() == null ? 0 : this.getResid().hashCode());
		result = 37
				* result
				+ (getMetaauthid() == null ? 0 : this.getMetaauthid()
						.hashCode());
		result = 37
				* result
				+ (getAuthgroupid() == null ? 0 : this.getAuthgroupid()
						.hashCode());
		result = 37 * result
				+ (getAuthid() == null ? 0 : this.getAuthid().hashCode());
		return result;
	}

}