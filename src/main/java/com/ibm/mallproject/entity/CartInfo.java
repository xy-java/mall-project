package com.ibm.mallproject.entity;

import java.util.Date;

/**
 * 购物车
 * **/
public class CartInfo {
	//购物车id
	private String cart_id;
	//用户id
	private String user_id;
	//商品id
	private String sku_id;
	//购物数量
	private Integer cart_num;
	//创建时间
	private Date create_time;

	public CartInfo() {
	}

	public CartInfo(String cart_id, String user_id, String sku_id, Integer cart_num, Date create_time) {
		this.cart_id = cart_id;
		this.user_id = user_id;
		this.sku_id = sku_id;
		this.cart_num = cart_num;
		this.create_time = create_time;
	}

	public String getCart_id() {
		return cart_id;
	}

	public void setCart_id(String cart_id) {
		this.cart_id = cart_id;
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

	public Integer getCart_num() {
		return cart_num;
	}

	public void setCart_num(Integer cart_num) {
		this.cart_num = cart_num;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
