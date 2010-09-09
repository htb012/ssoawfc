package jp.co.pegatron.domain.model;

import java.util.Date;

import jp.co.pegatron.domain.model.abstractmodel.AbstractBulletin;
import jp.co.pegatron.hibernate.EnumBulletinType;

/**
 * Bulletin entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Bulletin extends AbstractBulletin implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Bulletin() {
	}

	/** minimal constructor */
	public Bulletin(User user, Date publishdate, EnumBulletinType bulletintype) {
		super(user, publishdate, bulletintype);
	}

	/** full constructor */
	public Bulletin(User user, Date publishdate, String bulletintitle,
			String content, EnumBulletinType bulletintype, String bulletinextend) {
		super(user, publishdate, bulletintitle, content, bulletintype,
				bulletinextend);
	}

}
