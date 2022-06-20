package com.ibm.mallproject.controller;

import com.ibm.mallproject.entity.UserInfo;
import com.ibm.mallproject.service.UserService;
import com.ibm.mallproject.util.CommonUtil;
import com.ibm.mallproject.util.TengXunSMSUtils;
import com.ibm.mallproject.util.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/insert")
	@ResponseBody
	public String insert(@RequestParam Map<String, String> userMap) {
		if(userMap.get("verifycode").equals(userMap.get("code"))){
			Integer flag = userService.insert(userMap);
			if(flag>0){
				return "新增成功";
			}else{
				return "新增失败";
			}
		}else {
			return "验证码错误";
		}
	}

	@RequestMapping("/checkPhone")
	@ResponseBody
	public String checkPhone(@RequestParam Map<String,String> map){
			UserInfo userInfo = userService.selectByPhone(map.get("email"));
			if(userInfo==null){
				return "未存在";
			}else{
				return "已存在";
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

	@RequestMapping("/sendCode")
	@ResponseBody
	public String sendCode(@RequestParam String phoneNumber){
		//生成验证码
		if (phoneNumber!=null && phoneNumber!="") {
			//生成随机4位验证码
			String code = ValidateCodeUtils.generateValidateCode(4).toString();
			System.out.println("code="+code);
			//调用腾讯云提供的短信服务API完成发送短信
			TengXunSMSUtils.sendShortMessage("在家学习1公众号","1439933",phoneNumber,code);
			//保存生成的验证码到Session中

			return code;
		}
		return "发送失败";
	}


}
