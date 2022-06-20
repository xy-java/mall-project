package com.ibm.mallproject.entity;

import java.util.Date;

/**
 * 用户表
 * **/
public class UserInfo {
	//用户id
	private String user_id;
	//用户名称
	private String login_name;
	//用户密码
	private String passwd;
	//用户邮箱
	private String email;
	//创建时间
	private Date create_time;
	//用户权限
	private String user_power;
	//手机号
	private String phone;

	public UserInfo() {
	}

	public UserInfo(String user_id, String login_name, String passwd, String email, Date create_time,String user_power) {
		this.user_id = user_id;
		this.login_name = login_name;
		this.passwd = passwd;
		this.email = email;
		this.create_time = create_time;
		this.user_power = user_power;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUser_power() {
		return user_power;
	}

	public void setUser_power(String user_power) {
		this.user_power = user_power;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
