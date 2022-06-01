package com.ibm.mallproject.entity;

import java.util.Date;

/**
 * 收藏表
 * **/
public class FavorInfo {
	//收藏id
	private String favor_id;
	//用户id
	private String user_id;
	//商品id
	private String sku_id;
	//创建时间
	private Date create_time;

	public FavorInfo() {
	}

	public FavorInfo(String favor_id, String user_id, String sku_id, Date create_time) {
		this.favor_id = favor_id;
		this.user_id = user_id;
		this.sku_id = sku_id;
		this.create_time = create_time;
	}

	public String getFavor_id() {
		return favor_id;
	}

	public void setFavor_id(String favor_id) {
		this.favor_id = favor_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getSku_id() {
		return sku_id;
	}

	public void setSku_id(String sku_id) {
		this.sku_id = sku_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
