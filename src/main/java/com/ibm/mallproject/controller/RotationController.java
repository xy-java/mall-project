package com.ibm.mallproject.controller;

import com.ibm.mallproject.entity.RotationInfo;
import com.ibm.mallproject.service.RotationService;
import com.ibm.mallproject.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

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

	@RequestMapping("/upload")
	@ResponseBody
	public String  upload(@RequestParam MultipartFile file,@RequestParam  String rotation_id) throws IOException {

		if(file.isEmpty()){
			return "上传失败";
		}
		String fileName = file.getOriginalFilename();
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		String newfileName = CommonUtil.getUUID().toUpperCase() + suffixName;

		ClassPathResource resource = new ClassPathResource("static/images");
		String path = resource.getFile().getPath();

		File dest = new File(path.split("target")[0] + "src\\main\\resources\\static\\images\\" + newfileName);
		try {
			List<RotationInfo> list = rotationService.selectAllList();
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getRotation_id().equals(rotation_id)){
					new File(path.split("target")[0] + "src\\main\\resources\\static\\images\\" +  list.get(i).getRotation_url()).delete();
					new File(path + "\\" + list.get(i).getRotation_url()).delete();
				}
			}
			file.transferTo(dest);
			//拷贝一份在target里
			CommonUtil.fileCopy(path.split("target")[0] + "src\\main\\resources\\static\\images\\" + newfileName
					,path+"\\" + newfileName);
			//修改数据库
			rotationService.updateById(rotation_id,newfileName);

			System.out.println("上传成功");
			return newfileName;
		} catch (IOException e) {
			System.out.println("上传失败");
			System.out.println(e.getMessage());
		}


		return "上传失败！";
	}


}
