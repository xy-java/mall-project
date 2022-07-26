package com.ibm.mallproject.dao;


import com.ibm.mallproject.entity.ParamterInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ParameterMapper {
	Integer insertParameter(ParamterInfo paramterInfo);

	List<ParamterInfo> selectBySkuId(String parameter_id);

	Integer updateParameter(ParamterInfo paramterInfo);


	//通过id删除(可批量删除)
	Integer deleteParameterById(List<String> sku_id);
}
