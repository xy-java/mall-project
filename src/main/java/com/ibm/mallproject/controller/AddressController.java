package com.ibm.mallproject.controller;

import com.ibm.mallproject.entity.AddressInfo;
import com.ibm.mallproject.service.AddressService;
import com.ibm.mallproject.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private AddressService addressService;


	@RequestMapping("/insertAddress")
	@ResponseBody
	public String insertAddress(@RequestParam Map<String,String> map){
		AddressInfo addressInfo = new AddressInfo();
		addressInfo.setAddress_id(CommonUtil.getUUID());
		addressInfo.setAddress_info(map.get("address_info"));
		addressInfo.setCreate_time(new Date());
		addressInfo.setAddress_status(Integer.valueOf(map.get("address_status")));
		addressInfo.setAddress_level1(map.get("address_level1"));
		addressInfo.setAddress_level2(map.get("address_level2"));
		addressInfo.setAddress_level3(map.get("address_level3"));
		addressInfo.setUser_id(map.get("user_id"));
		addressInfo.setDerive_name(map.get("derive_name"));

		return addressService.insertAddress(addressInfo) > 0 ? "增加成功" : "增加失败";
	}
	
	@RequestMapping("/queryAddress")
	@ResponseBody
	public List<AddressInfo> queryAddress(@RequestParam String user_id){
		return addressService.queryAddress(user_id);
	}


	@RequestMapping("/queryStatus")
	@ResponseBody
	public Integer queryStatus(@RequestParam String user_id){
		return addressService.queryStatus(user_id);
	}

}
