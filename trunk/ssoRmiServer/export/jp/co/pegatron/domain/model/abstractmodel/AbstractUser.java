package jp.co.pegatron.domain.model.abstractmodel;

import java.util.HashSet;
import java.util.Set;

import jp.co.pegatron.hibernate.EnumUserState;

/**
 * AbstractUser entity provides the base persistence definition of the User
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractUser implements java.io.Serializable {

	// Fields

	private Integer userid;
	private String username;
	private String realname;
	private String password;
	private String sex;
	private String phone;
	private String address;
	private String email;
	private EnumUserState state;
	private String securitykey;
	private String userextend;
	private String userdiscription;
	private Set contacts = new HashSet(0);
	private Set bulletins = new HashSet(0);
	private Set authgrpUsers = new HashSet(0);
	private Set orgUsers = new HashSet(0);
	private Set repairorders = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractUser() {
	}

	/** minimal constructor */
	public AbstractUser(String username, String password, EnumUserState state) {
		this.username = username;
		this.password = password;
		this.state = state;
	}

	/** full constructor */
	public AbstractUser(String username, String realname, String password,
			String sex, String phone, String address, String email,
			EnumUserState state, String securitykey, String userextend,
			String userdiscription, Set contacts, Set bulletins,
			Set authgrpUsers, Set orgUsers, Set repairorders) {
		this.username = username;
		this.realname = realname;
		this.password = password;
		this.sex = sex;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.state = state;
		this.securitykey = securitykey;
		this.userextend = userextend;
		this.userdiscription = userdiscription;
		this.contacts = contacts;
		this.bulletins = bulletins;
		this.authgrpUsers = authgrpUsers;
		this.orgUsers = orgUsers;
		this.repairorders = repairorders;
	}

	// Property accessors

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EnumUserState getState() {
		return this.state;
	}

	public void setState(EnumUserState state) {
		this.state = state;
	}

	public String getSecuritykey() {
		return this.securitykey;
	}

	public void setSecuritykey(String securitykey) {
		this.securitykey = securitykey;
	}

	public String getUserextend() {
		return this.userextend;
	}

	public void setUserextend(String userextend) {
		this.userextend = userextend;
	}

	public String getUserdiscription() {
		return this.userdiscription;
	}

	public void setUserdiscription(String userdiscription) {
		this.userdiscription = userdiscription;
	}

	public Set getContacts() {
		return this.contacts;
	}

	public void setContacts(Set contacts) {
		this.contacts = contacts;
	}

	public Set getBulletins() {
		return this.bulletins;
	}

	public void setBulletins(Set bulletins) {
		this.bulletins = bulletins;
	}

	public Set getAuthgrpUsers() {
		return this.authgrpUsers;
	}

	public void setAuthgrpUsers(Set authgrpUsers) {
		this.authgrpUsers = authgrpUsers;
	}

	public Set getOrgUsers() {
		return this.orgUsers;
	}

	public void setOrgUsers(Set orgUsers) {
		this.orgUsers = orgUsers;
	}

	public Set getRepairorders() {
		return this.repairorders;
	}

	public void setRepairorders(Set repairorders) {
		this.repairorders = repairorders;
	}

}