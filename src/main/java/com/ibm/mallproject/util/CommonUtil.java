package com.ibm.mallproject.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.StreamGobbler;
import com.alibaba.fastjson.JSONArray;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CommonUtil {
	//16位uuid去掉分割线
	public static String getUUID() {
		return (UUID.randomUUID()).toString().replace("-", "").substring(0,16);
	}

	public static void fileCopy(String src, String des) throws IOException {
		//io流固定格式
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(des));
		int i = -1;//记录获取长度
		byte[] bt = new byte[2014];//缓冲区
		while ((i = bis.read(bt))!=-1) {
			bos.write(bt, 0, i);
		}
		bis.close();
		bos.close();
		//关闭流
	}

	public static String convertMillis (long ms) {
		Integer ss = 1000;
		Integer mi = ss * 60;
		Integer hh = mi * 60;
		Integer dd = hh * 24;

		Long day = ms / dd;
		Long hour = (ms - day * dd) / hh;
		Long minute = (ms - day * dd - hour * hh) / mi;
		Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
		Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

		StringBuffer sb = new StringBuffer();
		if(day > 0) {
			sb.append(day+"天");
		}
		if(hour > 0) {
			sb.append(hour+"小时");
		}
		if(minute > 0) {
			sb.append(minute+"分");
		}
		if(second > 0) {
			sb.append(second+"秒");
		}
		if(milliSecond > 0) {
			sb.append(milliSecond+"毫秒");
		}
		return sb.toString();
	}
}
