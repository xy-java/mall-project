package com.ibm.mallproject.service.impl;

import com.ibm.mallproject.dao.UserMapper;
import com.ibm.mallproject.entity.UserInfo;
import com.ibm.mallproject.service.UserService;
import com.ibm.mallproject.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;


	//新增
	@Override
	public Integer insert(Map<String, String> userMap) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUser_id(CommonUtil.getUUID());
		userInfo.setLogin_name(userMap.get("login_name"));
		userInfo.setPasswd(userMap.get("passwd"));
		userInfo.setEmail(userMap.get("email"));
		userInfo.setUser_power(userMap.get("user_power"));
		userInfo.setCreate_time(new Date());
		return userMapper.insert(userInfo);
	}

	//全部删除
	@Override
	public Integer delete() {
		return userMapper.delete();
	}

	//删除单个或多个
	@Override
	public Integer deleteByIds(List<String> user_id) {
		return userMapper.deleteByIds(user_id);
	}

	@Override
	public Integer update(Map<String, String> userMap) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUser_id(userMap.get("user_id"));
		userInfo.setLogin_name(userMap.get("login_name"));
		userInfo.setPasswd(userMap.get("passwd"));
		userInfo.setEmail(userMap.get("email"));
		userInfo.setUser_power(userMap.get("user_power"));
		userInfo.setCreate_time(new Date());
		return userMapper.update(userInfo);
	}

	@Override
	public UserInfo selectById(String user_id) {
		return userMapper.selectById(user_id);
	}

	@Override
	public UserInfo login(String login_name, String passwd, String user_power) {
		return userMapper.login(login_name,passwd,user_power);
	}

	@Override
	public List<UserInfo> getAllUsers() {
		return userMapper.getAllUsers();
	}

	@Override
	public List<UserInfo> queryByName(String login_name, String email) {
		return userMapper.queryByName(login_name,email);
	}

	@Override
	public Integer queryCount(String login_name,String user_id) {
		return userMapper.queryCount(login_name,user_id);
	}

	@Override
	public UserInfo queryById(String user_id) {
		return userMapper.queryById(user_id);
	}

	@Override
	public UserInfo selectByPhone(String phone) {
		return userMapper.selectByPhone(phone);
	}

	@Override
	public Integer saveUser(UserInfo userInfo) {
		return userMapper.saveUser(userInfo);
	}
}
