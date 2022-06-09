package com.ibm.mallproject.entity;

import java.util.Date;

public class ParamterInfo {
	private String parameter_id;
	private Date create_time;
	private String parameter_versions;
	private String parameter_color;
	private String parameter_cp;
	private String parameter_series;

	public String getParameter_id() {
		return parameter_id;
	}

	public void setParameter_id(String parameter_id) {
		this.parameter_id = parameter_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getParameter_versions() {
		return parameter_versions;
	}

	public void setParameter_versions(String parameter_versions) {
		this.parameter_versions = parameter_versions;
	}

	public String getParameter_color() {
		return parameter_color;
	}

	public void setParameter_color(String parameter_color) {
		this.parameter_color = parameter_color;
	}

	public String getParameter_cp() {
		return parameter_cp;
	}

	public void setParameter_cp(String parameter_cp) {
		this.parameter_cp = parameter_cp;
	}

	public String getParameter_series() {
		return parameter_series;
	}

	public void setParameter_series(String parameter_series) {
		this.parameter_series = parameter_series;
	}
}
