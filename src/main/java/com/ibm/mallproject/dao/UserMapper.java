package com.ibm.mallproject.dao;

import com.ibm.mallproject.entity.UserInfo;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
	//新增
	Integer insert(UserInfo userInfo);

	//删除所有
	Integer delete();

	//通过id删除(可批量删除)
	Integer deleteByIds(List<String> user_Id);

	//修改
	Integer update(UserInfo userInfo);

	//查询用户信息ById
	UserInfo selectById(String user_id);

	//登陆验证
	UserInfo login(String login_name,String passwd,String user_power);

	//查询所有用户
	List<UserInfo> getAllUsers();

	//通过用户名和邮箱模糊查询
	List<UserInfo> queryByName(String login_name,String email);

	//用户名验证
	Integer queryCount(String login_name,String user_id);

	//修改方法查到一条数据
	UserInfo queryById(String user_id);

	//通过手机号查询
	UserInfo selectByPhone(String phone);


	Integer saveUser(UserInfo userInfo);
}
