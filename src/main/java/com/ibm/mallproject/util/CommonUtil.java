package com.ibm.mallproject.util;

import com.alibaba.fastjson.JSONArray;

import java.io.*;
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


}
