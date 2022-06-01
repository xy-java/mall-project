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


	public SkuInfo() {
	}

	public SkuInfo(String sku_id, Double price, String sku_name, String sku_desc, Integer store, Integer salcount, String img, Date create_time) {
		this.sku_id = sku_id;
		this.price = price;
		this.sku_name = sku_name;
		this.sku_desc = sku_desc;
		this.store = store;
		this.salcount = salcount;
		this.img = img;
		this.create_time = create_time;
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
}
