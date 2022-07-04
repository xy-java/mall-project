package com.ibm.mallproject.controller;

import ch.ethz.ssh2.*;
import com.ibm.mallproject.entity.HiveAnalys;
import com.ibm.mallproject.entity.UserHiveCount;
import com.ibm.mallproject.service.HiveAnalysService;
import com.ibm.mallproject.service.UserHiveCountService;
import com.ibm.mallproject.util.CommonUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/sqoop")
@CrossOrigin
public class SqoopController {

	@Autowired
	private UserHiveCountService userHiveCountService;
	@Autowired
	private HiveAnalysService hiveAnalysService;

	@RequestMapping("/userCount")
	@ResponseBody
	public UserHiveCount userCount(){
		return userHiveCountService.userCount();
	}

	@RequestMapping("/getTypeDate")
	@ResponseBody
	public List<Map<String,Integer>> getTypeDate(){
		List<HiveAnalys> tpyeDate = hiveAnalysService.getTpyeDate();
		//得到今天的日期
		List<Map<String,Integer>> dateList = new ArrayList<Map<String,Integer>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		LinkedHashMap<String,Integer> linkedMap = new LinkedHashMap<String,Integer>();
		for (int i = 6; i >= 0; i--) {
			Date date = DateUtils.addDays(new Date(), -i);
			String formatDate = sdf.format(date);
			linkedMap.put(formatDate,0);
		}
		dateList.add(linkedMap);



		for (int j = 0; j < tpyeDate.size(); j++) {
			HiveAnalys hiveAnalys = tpyeDate.get(j);
			dateList.get(0).put(hiveAnalys.getX_info(), Integer.valueOf(hiveAnalys.getY_info()));
		}
		return dateList;
	}

	@RequestMapping("/getTypeNum")
	@ResponseBody
	public List<Map<String, String>> getTypeNum(){
		List<HiveAnalys> tpyeNum = hiveAnalysService.getTpyeNum();

		List<Map<String,String>> numList = new ArrayList<Map<String,String>>();
		String[] type = {"手表","电脑","手机"};
		for (int i = 0; i < type.length; i++) {
			Map<String,String> map = new HashMap<String,String>();
			map.put("name",type[i]);
			map.put("value","0");
			numList.add(map);
		}
		for (int j = 0; j < tpyeNum.size(); j++) {
			HiveAnalys hiveAnalys = tpyeNum.get(j);
			for (int i = 0; i < numList.size(); i++) {
				if(hiveAnalys.getX_info().equals(numList.get(i).get("name"))){
					numList.get(i).put("value",hiveAnalys.getY_info());
				}
			}
		}
		return numList;
	}


	@RequestMapping("/getTypePrice")
	@ResponseBody
	public List<Map<String, Double>> getTypePrice(){
		List<HiveAnalys> tpyePrice = hiveAnalysService.getTpyePrice();

		List<Map<String,Double>> priceList = new ArrayList<Map<String,Double>>();
		String[] type = {"手表","电脑","手机"};
		Map<String,Double> map = new HashMap<String,Double>();
		for (int i = 0; i < type.length; i++) {
			map.put(type[i],0.0);
		}
		priceList.add(map);

		for (int j = 0; j < tpyePrice.size(); j++) {
			HiveAnalys hiveAnalys = tpyePrice.get(j);
			priceList.get(0).put(hiveAnalys.getX_info(), Double.valueOf(hiveAnalys.getY_info()));
		}

		return priceList;
	}


	@RequestMapping("/execLinux")
	@ResponseBody
	public String execLinux(){
		//连接服务器 服务器名称和端口号
		//xx.xx.xx.xx 就是目标服务器的ip 端口是22
		Connection connection = new Connection("192.168.32.202",22);
		//你要上传文件所在地址，linux和window路径不一样看你自己的系统
		String filePath1 ="C:\\Users\\XiaoYang\\Desktop\\project\\mall-project\\sql\\sqoop.sh";
		File f = new File(filePath1);
		Session session = null;
		SCPOutputStream os = null;
		try(FileInputStream fis = new FileInputStream(f)) {
			connection.connect();
			//yuan服务器用户名和密码
			boolean isAuthenticated = connection.authenticateWithPassword("root","root");
			if(!isAuthenticated){
				System.out.println("连接建立失败");
				return "执行错误";
			}

			SCPClient scpClient = new SCPClient(connection);
			//这个是你要上传文件的目标服务器的文件路径
			String remoteTargetDirectory = "/root/";
			SFTPv3Client sftpv3Client = new SFTPv3Client(connection);

//            //判断是否有这个文件夹 如果没有就创建一个
//            Boolean isdir = f.isDirectory(sftpv3Client, remoteTargetDirectory);
//            if(!isdir){
//                sftpv3Client.mkdir(remoteTargetDirectory,0600);
//            }
			os = scpClient.put(f.getName(),f.length(),remoteTargetDirectory,null);
			byte[] b = new byte[4096];
			int i;
			while ((i = fis.read(b)) != -1) {
				os.write(b, 0, i);
			}
			session= connection.openSession();//打开一个会话
			String cmd ="bash /usr/local/hadoop/hadoop-3.2.0/sbin/start-all.sh" + " && "+"chmod u+x " + remoteTargetDirectory + f.getName() + " && " + "bash " + remoteTargetDirectory + f.getName();
			//执行sqoop脚本
			System.out.println("linux命令=="+cmd);
			//获取系统时间
			long start = System.currentTimeMillis();
			System.err.println("开始时间："+start);
			session.execCommand(cmd);//执行命令
			InputStream is = new StreamGobbler(session.getStdout());
			BufferedReader brs = new BufferedReader(new InputStreamReader(is));
			while (true)
			{
				String line = brs.readLine();
				if (line == null)
				{
					break;
				}
				System.out.println(line);
			}

			os.flush();
			fis.close();
			os.close();
			session.close();
			connection.close();
			long end = System.currentTimeMillis();
			String time = CommonUtil.convertMillis(end - start);
			System.err.println("结束时间："+time);
			return "success";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "执行错误";
	}

}
