package com.ibm.mallproject.service.impl;

import com.ibm.mallproject.dao.HiveAnalysMapper;
import com.ibm.mallproject.entity.HiveAnalys;
import com.ibm.mallproject.service.HiveAnalysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HiveAnalysServiceImpl implements HiveAnalysService {
	@Autowired
	private HiveAnalysMapper hiveAnalysMapper;

	@Override
	public List<HiveAnalys> getTpyeDate() {
		return hiveAnalysMapper.getTpyeDate();
	}

	@Override
	public List<HiveAnalys> getTpyeNum() {
		return hiveAnalysMapper.getTpyeNum();
	}

	@Override
	public List<HiveAnalys> getTpyePrice() {
		return hiveAnalysMapper.getTpyePrice();
	}
}
