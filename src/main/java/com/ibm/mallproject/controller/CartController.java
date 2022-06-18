package com.ibm.mallproject.controller;

import com.ibm.mallproject.entity.CartInfo;
import com.ibm.mallproject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;

	@RequestMapping("/findCartByUserId")
	@ResponseBody
	public List<CartInfo> findCartByUserId(@RequestParam String user_id) {
		return cartService.findCartByUserId(user_id);
	}

	@RequestMapping("/insertCart")
	@ResponseBody
	public String insertCart(@RequestParam Map<String, String> map) {
		CartInfo cartInfo = new CartInfo();


		return cartService.insertCart(cartInfo)>0 ? "新增成功" : "新增失败";
	}

	@RequestMapping("/updateCart")
	@ResponseBody
	public String updateCart(@RequestParam Map<String, String> map) {
		CartInfo cartInfo = new CartInfo();

		return cartService.updateCart(cartInfo)>0 ? "修改成功" : "修改失败";
	}

	@RequestMapping("/deleteCartById")
	@ResponseBody
	public String deleteCartById(@RequestParam String cart_id) {
		return cartService.deleteCartById(cart_id)>0 ? "删除成功" : "删除失败";
	}
}
