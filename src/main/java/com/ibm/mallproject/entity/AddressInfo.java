package com.ibm.mallproject.entity;

import java.util.Date;

public class AddressInfo {
	private String address_id;
	private String address_info;
	private String user_id;
	private Integer address_status;
	private Date create_time;
	private String address_level1;
	private String address_level2;
	private String address_level3;
	private String derive_name;

	public String getAddress_id() {
		return address_id;
	}

	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}

	public String getAddress_info() {
		return address_info;
	}

	public void setAddress_info(String address_info) {
		this.address_info = address_info;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Integer getAddress_status() {
		return address_status;
	}

	public void setAddress_status(Integer address_status) {
		this.address_status = address_status;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getAddress_level1() {
		return address_level1;
	}

	public void setAddress_level1(String address_level1) {
		this.address_level1 = address_level1;
	}

	public String getAddress_level2() {
		return address_level2;
	}

	public void setAddress_level2(String address_level2) {
		this.address_level2 = address_level2;
	}

	public String getAddress_level3() {
		return address_level3;
	}

	public void setAddress_level3(String address_level3) {
		this.address_level3 = address_level3;
	}

	public String getDerive_name() {
		return derive_name;
	}

	public void setDerive_name(String derive_name) {
		this.derive_name = derive_name;
	}

	@Override
	public String toString() {
		return "AddressInfo{" +
				"address_id='" + address_id + '\'' +
				", address_info='" + address_info + '\'' +
				", user_id='" + user_id + '\'' +
				", address_status=" + address_status +
				", create_time=" + create_time +
				", address_level1='" + address_level1 + '\'' +
				", address_level2='" + address_level2 + '\'' +
				", address_level3='" + address_level3 + '\'' +
				", derive_name='" + derive_name + '\'' +
				'}';
	}
}
