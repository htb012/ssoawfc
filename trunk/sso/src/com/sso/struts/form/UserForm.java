package com.sso.struts.form;

/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
import javax.servlet.http.HttpServletRequest;

import jp.co.pegatron.domain.model.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

/**
 * MyEclipse Struts Creation date: 11-24-2008
 * 
 * XDoclet definition:
 * 
 * @struts.form name="Login"
 */
public class UserForm extends ValidatorForm {
	private Log log = LogFactory.getLog(UserForm.class);

	private String task;

	private User user;

	private String userid;

	private String username;

	private String realname;

	private String password;
	// 确认密码
	private String repassword;

	private String sex;

	private String phone;
	// 住址
	private String address;

	private String email;

	/**
	 * @return the user
	 */
	public User getUser() {
		user = new User();
		return this.buildUser(user);
	}

	public User buildUser(User user) {
		if (userid != null && !"".equals(userid)) {
			user.setUserid(new Integer(userid));
		}
		user.setUsername(username);
		user.setRealname(realname);
		user.setPassword(password);
		user.setSex(sex);
		user.setPhone(phone);
		user.setAddress(address);
		user.setEmail(email);
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
		userid = user.getUserid().toString();
		username = user.getUsername();
		realname = user.getRealname();
		password = user.getPassword();
		repassword = user.getPassword();
		sex = user.getSex();
		phone = user.getPhone();
		address = user.getAddress();
		email = user.getEmail();

	}

	/**
	 * @return the task
	 */
	public String getTask() {
		return task;
	}

	/**
	 * @param task
	 *            the task to set
	 */
	public void setTask(String task) {
		this.task = task;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the realname
	 */
	public String getRealname() {
		return realname;
	}

	/**
	 * @param realname
	 *            the realname to set
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the repassword
	 */
	public String getRepassword() {
		return repassword;
	}

	/**
	 * @param repassword
	 *            the repassword to set
	 */
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Method validate
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		if ("add".equals(task) || "update".equals(task)) {
			ActionErrors errors = new ActionErrors();
			if (username == null || username.length() < 1) {
				log.debug("username's input have a little wrong.");
				errors.add("username", new ActionMessage(
						"userinfoForm.usernameNull", "您的用户名不能为空!"));
			} else if (username.matches("^[a-zA-Z][a-zA-Z0-9_]{4,15}$")) {
				errors.add("userName", new ActionMessage(
						"userinfoForm.usernameError",
						"用户名要以字母开头，允许5-16字节，允许字母数字下划线"));
			}
			if (realname == null || realname.length() < 1) {
				errors.add("realname", new ActionMessage(
						"userinfoForm.realnameNull", "您的用户真实姓名不能为空!"));
			}
			if (password == null || password.length() < 1) {
				errors.add("password", new ActionMessage(
						"userinfoForm.passwordNull", "您的用户密码不能为空!"));
			}
			if (repassword == null || repassword.length() < 1) {
				errors.add("repassword", new ActionMessage(
						"userinfoForm.repasswordNull", "您的确认密码不能为空!"));
			}
			if (!password.equals(repassword)) {
				errors.add("repassword", new ActionMessage(
						"userinfoForm.passwordDif", "您的用户密码和确认密码不一致!"));
			}
			if (sex == null || sex.length() < 1) {
				errors.add("sex", new ActionMessage("userinfoForm.sexNull",
						"请您选择用户性别!"));
			}
			if (phone == null || phone.length() < 1) {
				errors.add("phone", new ActionMessage("userinfoForm.phoneNull",
						"您的电话号码不能为空!"));
			}
			if (!phone.matches("[0-9]{11}")) {
				errors.add("phone", new ActionMessage(
						"userinfoForm.phoneError", "输您输入11位正确的手机号!"));
			}
			if (address.length() < 1 || address == null) {
				errors.add("locus", new ActionMessage(
						"userinfoForm.addressNull", "请您输入住址!"));
			}
			if (email == null || email.length() < 1) {
				errors.add("email", new ActionMessage("userinfoForm.emailNull",
						"请您输入Email地址!"));
			}
			if (!errors.isEmpty()) {
				return errors;
			}
		}
		return null;
	}

	public void clear() {
		userid = null;
		username = null;
		realname = null;
		password = null;
		repassword = null;
		sex = null;
		phone = null;
		address = null;
		email = null;
	}
}