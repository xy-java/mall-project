package com.ibm.mallproject.controller;

import com.ibm.mallproject.entity.UserInfo;
import com.ibm.mallproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/insert")
	@ResponseBody
	public String insert(@RequestParam Map<String, String> userMap){
		//加判断参数非空(未加)
		Integer flag = userService.insert(userMap);
		if(flag>0){
			return "新增成功";
		}else{
			return "新增失败";
		}

	}

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(){
		Integer flag = userService.delete();
		if(flag>0){
			return "删除成功";
		}else {
			return "删除失败";
		}
	}

	@RequestMapping("/deleteById")
	@ResponseBody
	public String deleteById(@RequestParam List<String> user_id){
			Integer flag = userService.deleteByIds(user_id);
			if(flag>0){
				return "删除成功";
			}else {
				return "删除失败";
			}
//		}

	}

	@RequestMapping("update")
	@ResponseBody
	public String update(@RequestParam Map<String,String> userMap){
		System.err.println(userMap);
		Integer flag = userService.update(userMap);
		if(flag>0){
			return "修改成功";
		}else {
			return "修改失败";
		}
	}

	@RequestMapping("selectById")
	@ResponseBody
	public UserInfo selectById(@RequestParam String user_id){
		UserInfo userInfo = userService.selectById(user_id);
		return userInfo;
	}



	@RequestMapping("/login")
	@ResponseBody
	public UserInfo getUsers(@RequestParam String login_name, String passwd, String user_power){
		UserInfo userInfo= userService.login(login_name,passwd,user_power);
		return userInfo;
	}

	@RequestMapping("/getAllUsers")
	@ResponseBody
	public List<UserInfo> getAllUsers(){
		return userService.getAllUsers();
	}

	@RequestMapping("/queryByName")
	@ResponseBody
	public List<UserInfo> queryByName(@RequestParam String login_name,String email){
		return userService.queryByName(login_name,email);
	}

	@RequestMapping("/queryCount")
	@ResponseBody
	public Integer queryCount(@RequestParam String login_name,String user_id){
		return userService.queryCount(login_name,user_id);
	}
	@RequestMapping("/queryById")
	@ResponseBody
	public UserInfo queryById(@RequestParam String user_id){
		return userService.queryById(user_id);
	}
}
