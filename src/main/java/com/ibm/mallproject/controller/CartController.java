package com.ibm.mallproject.controller;

import com.ibm.mallproject.entity.CartInfo;
import com.ibm.mallproject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;

	@RequestMapping("/findCartByUserId")
	@ResponseBody
	public List<CartInfo> findCartByUserId(String user_id) {
		return cartService.findCartByUserId(user_id);
	}

	@RequestMapping("/insertCart")
	@ResponseBody
	public Integer insertCart(CartInfo cartInfo) {
		return cartService.insertCart(cartInfo);
	}

	@RequestMapping("/updateCart")
	@ResponseBody
	public Integer updateCart(CartInfo cartInfo) {
		return cartService.updateCart(cartInfo);
	}

	@RequestMapping("/deleteCartById")
	@ResponseBody
	public Integer deleteCartById(String cart_id) {
		return cartService.deleteCartById(cart_id);
	}
}
