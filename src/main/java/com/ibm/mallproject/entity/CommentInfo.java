package com.ibm.mallproject.entity;

import java.util.Date;

/**
 * 评价表
 * **/
public class CommentInfo {
	//评价id
	private String comment_id;
	//用户id
	private String user_id;
	//商品id
	private String sku_id;
	//订单编号
	private String order_id;
	//评价等级(1 2 3)
	private Integer appraise;
	//评价内容
	private String comment_txt;
	//创建时间
	private Date create_time;

	public CommentInfo() {
	}

	public CommentInfo(String comment_id, String user_id, String sku_id, String order_id, Integer appraise, String comment_txt, Date create_time) {
		this.comment_id = comment_id;
		this.user_id = user_id;
		this.sku_id = sku_id;
		this.order_id = order_id;
		this.appraise = appraise;
		this.comment_txt = comment_txt;
		this.create_time = create_time;
	}

	public String getComment_id() {
		return comment_id;
	}

	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
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

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public Integer getAppraise() {
		return appraise;
	}

	public void setAppraise(Integer appraise) {
		this.appraise = appraise;
	}

	public String getComment_txt() {
		return comment_txt;
	}

	public void setComment_txt(String comment_txt) {
		this.comment_txt = comment_txt;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
