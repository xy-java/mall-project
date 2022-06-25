package com.ibm.mallproject;

import com.ibm.mallproject.dao.SkuMapper;
import com.ibm.mallproject.entity.SkuInfo;
import com.ibm.mallproject.service.SkuService;
import com.ibm.mallproject.util.CommonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MallProjectApplicationTests {

	@Autowired
	SkuMapper skuMapper;

	@Autowired
	SkuService skuService;

	@Test
	public static void main(String[] args) {
		System.out.println(CommonUtil.getUUID());
	}

	@Test
	void contextLoads() {

	}

	@Test
	void test(){
		HashMap<String, String> map = new HashMap<>();
		map.put("price","2");
		map.put("sku_name","name221");
		map.put("sku_desc","desc11");
		map.put("store","12");
		map.put("salcount","10");
		map.put("img","img1111");
		map.put("sku_summary","nn");
		map.put("parameter_id","nn");
		Integer integer = skuService.insertSku(map);
		System.out.println(integer);
	}

	@Test
	public void testUpdate(){
		HashMap<String, String> map = new HashMap<>();
		map.put("price","1.1");
		map.put("sku_name","name2");
		map.put("sku_desc","desc2");
		map.put("store","1222");
		map.put("salcount","1220");
		map.put("sku_id", "dbad521200c347a6");
		Integer integer = skuService.updateSku(map);
		System.out.println(integer);
	}
	@Test
	public void test2(){
//		List<SkuInfo> me = skuService.selectSkuNamePrice("me", 10.0, 20.0);
//		for (SkuInfo skuInfo : me) {
//			System.out.println(skuInfo);
//		}
		SkuInfo skuInfo = skuService.selectSkuById("a84e32370963417f");

			System.out.println(skuInfo);
//		Integer integer = skuService.updateSkuDesc("dbad521200c347a6","aaaaaaaaaaaaaaa");
//		System.out.println(integer);

//		ArrayList<String> strings = new ArrayList<>();
//		strings.add("ae8fd1da88144ebd");
//		//strings.add("c43bcef69d0249eb");
//		Integer integer = skuService.deleteSkuById(strings);
//		System.out.println(integer);
//		Integer integer = skuService.deleteSkuAll();
//		System.out.println(integer);
	}

}
