package jp.co.pegatron.domain.model;

import java.util.Set;

import jp.co.pegatron.domain.model.abstractmodel.AbstractUser;
import jp.co.pegatron.hibernate.EnumUserState;

/**
 * User entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class User extends AbstractUser implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String password, EnumUserState state) {
		super(username, password, state);
	}

	/** full constructor */
	public User(String username, String realname, String password, String sex,
			String phone, String address, String email, EnumUserState state,
			String securitykey, String userextend, String userdiscription,
			Set contacts, Set bulletins, Set authgrpUsers, Set orgUsers,
			Set repairorders) {
		super(username, realname, password, sex, phone, address, email, state,
				securitykey, userextend, userdiscription, contacts, bulletins,
				authgrpUsers, orgUsers, repairorders);
	}

}
