package com.ibm.mallproject.entity;

public class HiveAnalys {

	private String x_info;
	private String y_info;
	private String type;

	public String getX_info() {
		return x_info;
	}

	public void setX_info(String x_info) {
		this.x_info = x_info;
	}

	public String getY_info() {
		return y_info;
	}

	public void setY_info(String y_info) {
		this.y_info = y_info;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "HiveAnalys{" +
				"x_info='" + x_info + '\'' +
				", y_info='" + y_info + '\'' +
				", type='" + type + '\'' +
				'}';
	}
}
