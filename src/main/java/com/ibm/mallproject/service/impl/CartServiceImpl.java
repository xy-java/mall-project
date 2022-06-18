package com.ibm.mallproject.service.impl;

import com.ibm.mallproject.dao.CartMapper;
import com.ibm.mallproject.entity.CartInfo;
import com.ibm.mallproject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	private CartMapper cartMapper;

	@Override
	public List<CartInfo> findCartByUserId(String user_id) {


		return cartMapper.findCartByUserId(user_id);
	}

	@Override
	public Integer insertCart(CartInfo cartInfo) {
		return cartMapper.insertCart(cartInfo);
	}

	@Override
	public Integer updateCart(CartInfo cartInfo) {
		return cartMapper.updateCart(cartInfo);
	}

	@Override
	public Integer deleteCartById(String cart_id) {
		return cartMapper.deleteCartById(cart_id);
	}
}
