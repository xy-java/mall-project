package com.ibm.mallproject.controller;

import ch.ethz.ssh2.*;
import com.ibm.mallproject.entity.HiveAnalys;
import com.ibm.mallproject.entity.UserHiveCount;
import com.ibm.mallproject.service.HiveAnalysService;
import com.ibm.mallproject.service.UserHiveCountService;
import com.ibm.mallproject.util.CommonUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/sqoop")
@CrossOrigin
public class SqoopController {

	@Autowired
	private UserHiveCountService userHiveCountService;
	@Autowired
	private HiveAnalysService hiveAnalysService;

	@RequestMapping("/userCount")
	@ResponseBody
	public UserHiveCount userCount(){
		return userHiveCountService.userCount();
	}

	@RequestMapping("/getTypeDate")
	@ResponseBody
	public List<LinkedHashMap<String,Integer>> getTypeDate(){
		List<HiveAnalys> tpyeDate = hiveAnalysService.getTpyeDate();
		//得到今天的日期
		List<LinkedHashMap<String,Integer>> dateList = new ArrayList<LinkedHashMap<String,Integer>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		LinkedHashMap<String,Integer> linkedMap = new LinkedHashMap<String,Integer>();
		for (int i = 6; i >= 0; i--) {
			Date date = DateUtils.addDays(new Date(), -i);
			String formatDate = sdf.format(date);
			linkedMap.put(formatDate,0);
		}
		dateList.add(linkedMap);

		for (String key : linkedMap.keySet()) {
			for (HiveAnalys hiveAnalys : tpyeDate) {
				if(key.equals(hiveAnalys.getX_info())){
					linkedMap.put(key, Integer.valueOf(hiveAnalys.getY_info()));
				}
			}

		}
		dateList.add(linkedMap);

//		for (int j = 0; j < tpyeDate.size(); j++) {
//			HiveAnalys hiveAnalys = tpyeDate.get(j);
//			dateList.get(0).put(hiveAnalys.getX_info(), Integer.valueOf(hiveAnalys.getY_info()));
//		}
//		System.err.println(dateList);
		return dateList;
	}

	@RequestMapping("/getTypeNum")
	@ResponseBody
	public List<Map<String, String>> getTypeNum(){
		List<HiveAnalys> tpyeNum = hiveAnalysService.getTpyeNum();

		List<Map<String,String>> numList = new ArrayList<Map<String,String>>();
		String[] type = {"手表","电脑","手机"};
		for (int i = 0; i < type.length; i++) {
			Map<String,String> map = new HashMap<String,String>();
			map.put("name",type[i]);
			map.put("value","0");
			numList.add(map);
		}
		for (int j = 0; j < tpyeNum.size(); j++) {
			HiveAnalys hiveAnalys = tpyeNum.get(j);
			for (int i = 0; i < numList.size(); i++) {
				if(hiveAnalys.getX_info().equals(numList.get(i).get("name"))){
					numList.get(i).put("value",hiveAnalys.getY_info());
				}
			}
		}
		return numList;
	}


	@RequestMapping("/getTypePrice")
	@ResponseBody
	public List<Map<String, Double>> getTypePrice(){
		List<HiveAnalys> tpyePrice = hiveAnalysService.getTpyePrice();

		List<Map<String,Double>> priceList = new ArrayList<Map<String,Double>>();
		String[] type = {"手表","电脑","手机"};
		Map<String,Double> map = new HashMap<String,Double>();
		for (int i = 0; i < type.length; i++) {
			map.put(type[i],0.0);
		}
		priceList.add(map);

		for (int j = 0; j < tpyePrice.size(); j++) {
			HiveAnalys hiveAnalys = tpyePrice.get(j);
			priceList.get(0).put(hiveAnalys.getX_info(), Double.valueOf(hiveAnalys.getY_info()));
		}

		return priceList;
	}




}
