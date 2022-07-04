package com.ibm.mallproject.dao;

import com.ibm.mallproject.entity.HiveAnalys;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HiveAnalysMapper {

	List<HiveAnalys> getTpyeDate();
	List<HiveAnalys> getTpyeNum();
	List<HiveAnalys> getTpyePrice();
}
