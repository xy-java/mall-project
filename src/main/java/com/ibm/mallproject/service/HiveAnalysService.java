package com.ibm.mallproject.service;

import com.ibm.mallproject.entity.HiveAnalys;

import java.util.List;

public interface HiveAnalysService {
	List<HiveAnalys> getTpyeDate();
	List<HiveAnalys> getTpyeNum();
	List<HiveAnalys> getTpyePrice();
}
