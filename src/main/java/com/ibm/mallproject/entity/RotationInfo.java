package com.ibm.mallproject.entity;

import java.util.Date;

/***
 *	轮播图实体
 */

public class RotationInfo {
	private String rotation_id;
	private String rotation_url;
	private Date create_time;
	private String rotation_type;

	public RotationInfo() {
	}

	public RotationInfo(String rotation_id, String rotation_url, Date create_time, String rotation_type) {
		this.rotation_id = rotation_id;
		this.rotation_url = rotation_url;
		this.create_time = create_time;
		this.rotation_type = rotation_type;
	}

	public String getRotation_id() {
		return rotation_id;
	}

	public void setRotation_id(String rotation_id) {
		this.rotation_id = rotation_id;
	}

	public String getRotation_url() {
		return rotation_url;
	}

	public void setRotation_url(String rotation_url) {
		this.rotation_url = rotation_url;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getRotation_type() {
		return rotation_type;
	}

	public void setRotation_type(String rotation_type) {
		this.rotation_type = rotation_type;
	}
}

