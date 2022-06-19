package com.ibm.mallproject.controller;

import com.ibm.mallproject.entity.CartInfo;
import com.ibm.mallproject.entity.SkuInfo;
import com.ibm.mallproject.service.CartService;
import com.ibm.mallproject.service.SkuService;
import com.ibm.mallproject.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private SkuService skuService;

	@RequestMapping("/findCartByUserId")
	@ResponseBody
	public List<Map<String,Object>> findCartByUserId(@RequestParam String user_id) {
		List<CartInfo> cartList=cartService.findCartByUserId(user_id);
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < cartList.size(); i++) {
			HashMap<String,Object> map = new HashMap<String,Object>();
			SkuInfo skuInfo = skuService.selectSkuById(cartList.get(i).getSku_id());
			map.put("skuInfo",skuInfo);
			map.put("cartInfo",cartList.get(i));
			result.add(map);
		}
		return result;
	}

	@RequestMapping("/insertCart")
	@ResponseBody
	public String insertCart(@RequestParam Map<String, String> map) {
		CartInfo cartInfo = new CartInfo();
		cartInfo.setCart_id(CommonUtil.getUUID());
		cartInfo.setUser_id(map.get("user_id"));
		cartInfo.setSku_id(map.get("sku_id"));
		cartInfo.setCart_num(Integer.parseInt(map.get("cart_num")));
		cartInfo.setAddress_id(map.get("address_id"));
		cartInfo.setSku_version(map.get("sku_version"));
		cartInfo.setSku_color(map.get("sku_color"));
		cartInfo.setSku_cp(map.get("sku_cp"));
		cartInfo.setSku_series(map.get("sku_series"));
		cartInfo.setCreate_time(new Date());

		CartInfo cartInfo1 = cartService.selectOneCart(cartInfo);
		if (cartInfo1 != null) {
			cartInfo1.setCart_num(cartInfo1.getCart_num() + cartInfo.getCart_num());
			System.err.println(cartInfo1);
			return cartService.updateCart(cartInfo1)>0?  "新增成功" : "新增失败";
		} else {
			return cartService.insertCart(cartInfo)>0 ? "新增成功" : "新增失败";
		}


	}


	@RequestMapping("/deleteCartById")
	@ResponseBody
	public String deleteCartById(@RequestParam String cart_id) {
		return cartService.deleteCartById(cart_id)>0 ? "删除成功" : "删除失败";
	}

	@RequestMapping("/updateCart")
	@ResponseBody
	public String updateCart(@RequestParam Map<String, String> map) {
		CartInfo cartInfo = new CartInfo();
		cartInfo.setCart_id(map.get("cart_id"));
		cartInfo.setCart_num(Integer.parseInt(map.get("cart_num")));
		return cartService.updateCart(cartInfo)>0 ? "修改成功" : "修改失败";
	}
}
