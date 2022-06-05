package com.ibm.mallproject.service.impl;

import com.ibm.mallproject.dao.RotationMapper;
import com.ibm.mallproject.entity.RotationInfo;
import com.ibm.mallproject.service.RotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RotationServiceImpl implements RotationService {
	@Autowired
	private RotationMapper rotationMapper;

	@Override
	public List<RotationInfo> selectAllList() {
		return rotationMapper.selectAllList();
	}
}
