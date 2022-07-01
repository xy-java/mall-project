package com.ibm.mallproject;

import com.ibm.mallproject.dao.SkuMapper;
import com.ibm.mallproject.entity.SkuInfo;
import com.ibm.mallproject.service.SkuService;
import com.ibm.mallproject.util.CommonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@SpringBootTest
class MallProjectApplicationTests {

	@Autowired
	SkuMapper skuMapper;

	@Autowired
	SkuService skuService;

	@Test
	public void test() {
	}

}
