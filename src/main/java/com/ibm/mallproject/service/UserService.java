package com.ibm.mallproject.service;

import com.ibm.mallproject.entity.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserService {
	//新增
	Integer insert(Map<String, String> userMap);

	//删除
	Integer delete();

	//通过ID删除
	Integer deleteByIds(List<String> user_id);

	//修改
	Integer update(Map<String, String> userMap);

	//查询单个用户
	UserInfo selectById(String user_id);

	//登陆验证
	UserInfo login(String login_name,String passwd,String user_power);

	List<UserInfo> getAllUsers();

	//通过用户名和邮箱模糊查询
	List<UserInfo> queryByName(String login_name,String email);

	//用户名验证
	Integer queryCount(String login_name,String user_id);

	//修改方法查到一条数据
	UserInfo queryById(String user_id);
}
