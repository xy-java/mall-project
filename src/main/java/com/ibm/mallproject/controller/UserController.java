package com.ibm.mallproject.controller;

import com.ibm.mallproject.entity.UserInfo;
import com.ibm.mallproject.service.UserService;
import com.ibm.mallproject.util.CommonUtil;
import com.ibm.mallproject.util.TengXunSMSUtils;
import com.ibm.mallproject.util.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

	@RequestMapping("/sendCode")
	@ResponseBody
	public String sendCode(@RequestParam String phoneNumber, HttpSession session){
		if (phoneNumber!=null && phoneNumber!="") {
			//生成随机4位验证码
			String code = ValidateCodeUtils.generateValidateCode(4).toString();
			System.out.println("code="+code);
			//调用腾讯云提供的短信服务API完成发送短信
			TengXunSMSUtils.sendShortMessage("在家学习1公众号","1439933",phoneNumber,code);
			//保存生成的验证码到Session中
			session.setAttribute(phoneNumber, code);

			return "发送成功";
		}
		return "发送失败";
	}

	@RequestMapping("/enroll")
	@ResponseBody
	public String enroll(@RequestParam Map<String,String> map,HttpSession session){
		String phoneNumber = map.get("phoneNumber");
		String enrollCode = map.get("code");
		String codeInSession = session.getAttribute(phoneNumber).toString();

		if (codeInSession != null && codeInSession.equals(enrollCode)) {
			//判断是不是新用户
			UserInfo userInfo = userService.selectByPhone(phoneNumber);
			if(userInfo == null){
				userInfo.setUser_id(CommonUtil.getUUID());
				userInfo.setLogin_name(map.get("login_name"));
				userInfo.setPasswd(map.get("passwd"));
				userInfo.setCreate_time(new Date());
				userInfo.setUser_power("用户");
				userInfo.setEmail(phoneNumber);

				userService.saveUser(userInfo);
			}
			//成功注册清除session中的验证码
			session.removeAttribute(phoneNumber);

			return "注册成功，请重新登录";
		}

		return "验证码错误";
	}
}
