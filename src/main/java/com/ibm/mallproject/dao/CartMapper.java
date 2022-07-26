package com.ibm.mallproject.dao;

import com.ibm.mallproject.entity.CartInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CartMapper {
	/**
	 * 查询用户购物车
	 * */
	List<CartInfo> findCartByUserId(String user_id);

	List<CartInfo> selectCartByIds(List<String> cart_id);

	CartInfo selectOneCart(CartInfo cartInfo);
	/**
	 * 添加商品到购物车
	 * */
	Integer insertCart(CartInfo cartInfo);

	/**
	 * 更新购物车商品数量
	 * */
	Integer updateCart(CartInfo cartInfo);

	/**
	 * 删除购物车商品
	 * */
	Integer deleteCartById(String cart_id);

	/**
	 * 根据购物车id查询
	 * @param cart_id
	 * @return
	 */
	CartInfo selectCartById(String cart_id);
}
