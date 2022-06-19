package com.ibm.mallproject.service;

import com.ibm.mallproject.entity.CartInfo;

import java.util.List;

public interface CartService {
	/**
	 * 查询用户购物车
	 * */
	List<CartInfo> findCartByUserId(String user_id);

	/**
	 * 添加商品到购物车
	 * */
	Integer insertCart(CartInfo cartInfo);


	CartInfo selectOneCart(CartInfo cartInfo);

	/**
	 * 更新购物车商品数量
	 * */
	Integer updateCart(CartInfo cartInfo);

	/**
	 * 删除购物车商品
	 * */
	Integer deleteCartById(String cart_id);
}
