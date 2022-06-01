package com.ibm.mallproject.util;

import java.util.UUID;

public class CommonUtil {
	//16位uuid去掉分割线
	public static String getUUID() {
		return (UUID.randomUUID()).toString().replace("-", "").substring(0,16);
	}

}
