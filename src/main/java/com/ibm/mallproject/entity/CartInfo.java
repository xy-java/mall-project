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

	private String address_id;

	private String sku_version;

	private String sku_color;

	private String sku_cp;
	private String sku_series;

	public String getSku_version() {
		return sku_version;
	}

	public void setSku_version(String sku_version) {
		this.sku_version = sku_version;
	}

	public String getSku_color() {
		return sku_color;
	}

	public void setSku_color(String sku_color) {
		this.sku_color = sku_color;
	}

	public String getSku_cp() {
		return sku_cp;
	}

	public void setSku_cp(String sku_cp) {
		this.sku_cp = sku_cp;
	}

	public String getSku_series() {
		return sku_series;
	}

	public void setSku_series(String sku_series) {
		this.sku_series = sku_series;
	}

	public String getAddress_id() {
		return address_id;
	}

	public void setAddress_id(String address_id) {
		this.address_id = address_id;
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

	@Override
	public String toString() {
		return "CartInfo{" +
				"cart_id='" + cart_id + '\'' +
				", user_id='" + user_id + '\'' +
				", sku_id='" + sku_id + '\'' +
				", cart_num=" + cart_num +
				", create_time=" + create_time +
				", address_id='" + address_id + '\'' +
				", sku_version='" + sku_version + '\'' +
				", sku_color='" + sku_color + '\'' +
				", sku_cp='" + sku_cp + '\'' +
				", sku_series='" + sku_series + '\'' +
				'}';
	}
}
