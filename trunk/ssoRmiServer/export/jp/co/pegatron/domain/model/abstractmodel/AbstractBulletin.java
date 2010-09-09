package jp.co.pegatron.domain.model.abstractmodel;

import java.util.Date;

import jp.co.pegatron.domain.model.User;
import jp.co.pegatron.hibernate.EnumBulletinType;

/**
 * AbstractBulletin entity provides the base persistence definition of the
 * Bulletin entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractBulletin implements java.io.Serializable {

	// Fields

	private Integer bulletinid;
	private User user;
	private Date publishdate;
	private String bulletintitle;
	private String content;
	private EnumBulletinType bulletintype;
	private String bulletinextend;

	// Constructors

	/** default constructor */
	public AbstractBulletin() {
	}

	/** minimal constructor */
	public AbstractBulletin(User user, Date publishdate, EnumBulletinType bulletintype) {
		this.user = user;
		this.publishdate = publishdate;
		this.bulletintype = bulletintype;
	}

	/** full constructor */
	public AbstractBulletin(User user, Date publishdate, String bulletintitle,
			String content, EnumBulletinType bulletintype, String bulletinextend) {
		this.user = user;
		this.publishdate = publishdate;
		this.bulletintitle = bulletintitle;
		this.content = content;
		this.bulletintype = bulletintype;
		this.bulletinextend = bulletinextend;
	}

	// Property accessors

	public Integer getBulletinid() {
		return this.bulletinid;
	}

	public void setBulletinid(Integer bulletinid) {
		this.bulletinid = bulletinid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getPublishdate() {
		return this.publishdate;
	}

	public void setPublishdate(Date publishdate) {
		this.publishdate = publishdate;
	}

	public String getBulletintitle() {
		return this.bulletintitle;
	}

	public void setBulletintitle(String bulletintitle) {
		this.bulletintitle = bulletintitle;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public EnumBulletinType getBulletintype() {
		return this.bulletintype;
	}

	public void setBulletintype(EnumBulletinType bulletintype) {
		this.bulletintype = bulletintype;
	}

	public String getBulletinextend() {
		return this.bulletinextend;
	}

	public void setBulletinextend(String bulletinextend) {
		this.bulletinextend = bulletinextend;
	}

}