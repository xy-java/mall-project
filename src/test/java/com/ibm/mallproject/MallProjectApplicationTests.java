package com.ibm.mallproject;

import com.ibm.mallproject.dao.SkuMapper;
import com.ibm.mallproject.entity.SkuInfo;
import com.ibm.mallproject.service.SkuService;
import com.ibm.mallproject.util.CommonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;

@SpringBootTest
class MallProjectApplicationTests {

	@Autowired
	SkuMapper skuMapper;

	@Autowired
	SkuService skuService;

	@Test
	void contextLoads() {
		SkuInfo skuInfo = new SkuInfo("1",1.1,"name","desc",1,1,"img",new Date());
		Integer integer = skuMapper.insertSku(skuInfo);
		System.out.println(integer);
	}

	@Test
	void test(){
		HashMap<String, String> map = new HashMap<>();
		map.put("price","1.1");
		map.put("sku_name","name1");
		map.put("sku_desc","desc11");
		map.put("store","12");
		map.put("salcount","10");
		map.put("img","img1111");
		Integer integer = skuService.insertSku(map);
		System.out.println(integer);
	}

}
