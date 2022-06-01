package com.ibm.mallproject.entity;

import java.util.Date;

/**
 * 订单表
 * **/
public class OrderInfo {
	//订单id
	private String order_id;
	//总金额
	private Double total_amount;
	//用户id
	private String user_id;
	//付款方式
	private String payment_way;
	//订单状态
	private Integer order_status;
	//创建时间
	private Date create_time;

	public OrderInfo() {
	}

	public OrderInfo(String order_id, Double total_amount, String user_id, String payment_way, Integer order_status, Date create_time) {
		this.order_id = order_id;
		this.total_amount = total_amount;
		this.user_id = user_id;
		this.payment_way = payment_way;
		this.order_status = order_status;
		this.create_time = create_time;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public Double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(Double total_amount) {
		this.total_amount = total_amount;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPayment_way() {
		return payment_way;
	}

	public void setPayment_way(String payment_way) {
		this.payment_way = payment_way;
	}

	public Integer getOrder_status() {
		return order_status;
	}

	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
