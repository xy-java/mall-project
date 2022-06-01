package com.ibm.mallproject.entity;

import java.util.Date;

/**
 * 订单明细表
 * **/
public class OrderDetail {
	//订单明细id
	private String detail_id;
	//订单id
	private String order_id;
	//商品id
	private String sku_id;
	//购买价格
	private Double order_price;
	//购买个数
	private Integer order_num;
	//创建时间
	private Date create_time;

	public OrderDetail() {
	}

	public OrderDetail(String detail_id, String order_id, String sku_id, Double order_price, Integer order_num, Date create_time) {
		this.detail_id = detail_id;
		this.order_id = order_id;
		this.sku_id = sku_id;
		this.order_price = order_price;
		this.order_num = order_num;
		this.create_time = create_time;
	}

	public String getDetail_id() {
		return detail_id;
	}

	public void setDetail_id(String detail_id) {
		this.detail_id = detail_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getSku_id() {
		return sku_id;
	}

	public void setSku_id(String sku_id) {
		this.sku_id = sku_id;
	}

	public Double getOrder_price() {
		return order_price;
	}

	public void setOrder_price(Double order_price) {
		this.order_price = order_price;
	}

	public Integer getOrder_num() {
		return order_num;
	}

	public void setOrder_num(Integer order_num) {
		this.order_num = order_num;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
