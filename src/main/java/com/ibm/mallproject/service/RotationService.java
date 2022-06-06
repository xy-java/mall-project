package com.ibm.mallproject.service;

import com.ibm.mallproject.entity.RotationInfo;

import java.util.List;

public interface RotationService {
	List<RotationInfo> selectAllList();

	Integer updateById(String rotation_id,String rotation_url);
}
