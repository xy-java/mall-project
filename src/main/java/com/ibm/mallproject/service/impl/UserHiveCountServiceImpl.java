package com.ibm.mallproject.service.impl;

import com.ibm.mallproject.dao.UserHiveCountMapper;
import com.ibm.mallproject.entity.UserHiveCount;
import com.ibm.mallproject.service.UserHiveCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserHiveCountServiceImpl implements UserHiveCountService {

	@Autowired
	private UserHiveCountMapper userHiveCountMapper;

	@Override
	public UserHiveCount userCount() {
		// TODO Auto-generated method stub
		return userHiveCountMapper.userCount();
	}
}
