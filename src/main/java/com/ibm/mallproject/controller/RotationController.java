package com.ibm.mallproject.controller;

import com.ibm.mallproject.entity.RotationInfo;
import com.ibm.mallproject.service.RotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/rotation")
@CrossOrigin
public class RotationController {
	@Autowired
	private RotationService rotationService;


	@RequestMapping("/selectAllList")
	@ResponseBody
	public List<RotationInfo> selectAllList(){
		return rotationService.selectAllList();
	}
}
