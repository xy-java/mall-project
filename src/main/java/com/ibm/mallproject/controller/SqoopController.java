package com.ibm.mallproject.controller;

import com.ibm.mallproject.entity.UserHiveCount;
import com.ibm.mallproject.service.UserHiveCountService;
import org.apache.hadoop.conf.Configuration;
import org.apache.sqoop.Sqoop;
import org.apache.sqoop.hive.HiveConfig;
import org.apache.sqoop.tool.SqoopTool;
import org.apache.sqoop.util.OptionsFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;


@Controller
@RequestMapping("/sqoop")
@CrossOrigin
public class SqoopController {

	@Autowired
	private UserHiveCountService userHiveCountService;

	@RequestMapping("/userCount")
	@ResponseBody
	public UserHiveCount userCount(){
		return userHiveCountService.userCount();
	}


	@RequestMapping("/importDataFromMysql")
	@ResponseBody
	public void importDataFromMysql() throws Exception {
		// 设置用于拼接存放MapReduce临时java文件的目录的名字的用户名称
		System.setProperty("user.name", "mapred");
		// 设置Job执行的用户身份
		System.setProperty("HADOOP_USER_NAME", "root");

		String[] args = new String[] {
				"--connect","jdbc:mysql://192.168.0.103:3306/test",
				"--driver","com.mysql.cj.jdbc.Driver",
				"-username","root",
				"-password","123456",
				"--table","sku_info",
				"-m","1",
				"--target-dir","sku_info",
		};
		System.setProperty("sqoop.throwOnError","true");
		String[] expandedArgs = null;

		try {
			expandedArgs = OptionsFileUtil.expandArguments(args);
		} catch (Exception e){
			System.err.println(e.getMessage());
			System.err.println("Try 'sqoop help' for usage.");
		}
		com.cloudera.sqoop.tool.SqoopTool tool = (com.cloudera.sqoop.tool.SqoopTool) SqoopTool.getTool("import");

		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.32.202:9000");//设置hadoop服务地址
		Configuration pluginConf = SqoopTool.loadPlugins(conf);

		Sqoop sqoop = new Sqoop(tool, pluginConf);
		int res = Sqoop.runSqoop(sqoop, expandedArgs);
		System.out.println(res);
		System.out.println("执行sqoop结束");
	}

	@RequestMapping("/exportDataToMysql")
	@ResponseBody
	public void exportDataToMysql() throws Exception {
		System.out.println(" begin test sqoop");
		String[] argument = new String[]{
				"--connect", "jdbc:mysql://192.168.0.103:3306/test",
				"--username", "root",
				"--password", "123456",
				"--table", "user_info",
				"--hive-import", "--hive-database", "sqooptohive", "--hive-overwrite", "--create-hive-table",
				"--hive-table", "data_table",
				"--delete-target-dir",
				"--target-dir", "/user1",
		};
		com.cloudera.sqoop.tool.SqoopTool sqoopTool = (com.cloudera.sqoop.tool.SqoopTool) SqoopTool.getTool("import");
		Configuration conf = new Configuration();
		conf.set("fs.default.name", "hdfs://192.168.32.202:9000");
		Configuration hive = HiveConfig.getHiveConf(conf);
		Sqoop sqoop = new Sqoop(sqoopTool, SqoopTool.loadPlugins(conf));
		int res = Sqoop.runSqoop(sqoop, argument);
		System.out.println(res);
		System.out.println("执行sqoop结束");
	}

}
