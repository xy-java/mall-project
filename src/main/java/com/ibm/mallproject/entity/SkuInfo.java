package com.ibm.mallproject.entity;

import java.util.Date;

/**
 * 商品表
 * **/
public class SkuInfo {
	//商品id
	private String sku_id;
	//商品价格
	private Double price;
	//商品名称
	private String sku_name;
	//商品描述
	private String sku_desc;
	//商品库存
	private Integer store;
	//销售数量
	private Integer salcount;
	//图片地址
	private String img;
	//创建时间
	private Date create_time;
	//商品图片概述(用逗号分隔)
	private String sku_summary;
	//参数页详情id
	private String parameter_id;

	private String sku_type;


	public SkuInfo() {
	}

	public SkuInfo(String sku_id, Double price, String sku_name, String sku_desc,
				   Integer store, Integer salcount, String img, Date create_time, String sku_summary, String parameter_id,String sku_type) {
		this.sku_id = sku_id;
		this.price = price;
		this.sku_name = sku_name;
		this.sku_desc = sku_desc;
		this.store = store;
		this.salcount = salcount;
		this.img = img;
		this.create_time = create_time;
		this.sku_summary = sku_summary;
		this.parameter_id = parameter_id;
		this.sku_type = sku_type;
	}

	public String getSku_id() {
		return sku_id;
	}

	public void setSku_id(String sku_id) {
		this.sku_id = sku_id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getSku_name() {
		return sku_name;
	}

	public void setSku_name(String sku_name) {
		this.sku_name = sku_name;
	}

	public String getSku_desc() {
		return sku_desc;
	}

	public void setSku_desc(String sku_desc) {
		this.sku_desc = sku_desc;
	}

	public Integer getStore() {
		return store;
	}

	public void setStore(Integer store) {
		this.store = store;
	}

	public Integer getSalcount() {
		return salcount;
	}

	public void setSalcount(Integer salcount) {
		this.salcount = salcount;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getSku_summary() {
		return sku_summary;
	}

	public void setSku_summary(String sku_summary) {
		this.sku_summary = sku_summary;
	}

	public String getParameter_id() {
		return parameter_id;
	}

	public void setParameter_id(String parameter_id) {
		this.parameter_id = parameter_id;
	}

	public String getSku_type() {
		return sku_type;
	}

	public void setSku_type(String sku_type) {
		this.sku_type = sku_type;
	}

	@Override
	public String toString() {
		return "SkuInfo{" +
				"sku_id='" + sku_id + '\'' +
				", price=" + price +
				", sku_name='" + sku_name + '\'' +
				", sku_desc='" + sku_desc + '\'' +
				", store=" + store +
				", salcount=" + salcount +
				", img='" + img + '\'' +
				", create_time=" + create_time +
				", sku_summary='" + sku_summary + '\'' +
				", parameter_id='" + parameter_id + '\'' +
				'}';
	}
}

