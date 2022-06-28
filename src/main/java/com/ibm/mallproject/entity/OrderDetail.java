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

	@Override
	public String toString() {
		return "OrderDetail{" +
				"detail_id='" + detail_id + '\'' +
				", order_id='" + order_id + '\'' +
				", sku_id='" + sku_id + '\'' +
				", order_price=" + order_price +
				", order_num=" + order_num +
				", create_time=" + create_time +
				", sku_version='" + sku_version + '\'' +
				", sku_color='" + sku_color + '\'' +
				", sku_cp='" + sku_cp + '\'' +
				", sku_series='" + sku_series + '\'' +
				'}';
	}
}
