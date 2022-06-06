package com.ibm.mallproject.dao;

import com.ibm.mallproject.entity.RotationInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RotationMapper {
	//查询所有轮播图
	List<RotationInfo> selectAllList();

	//更新轮播图
	Integer updateById(String rotation_id,String rotation_url);

}
