package com.ibm.mallproject.dao;

import com.ibm.mallproject.entity.UserHiveCount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserHiveCountMapper {
	UserHiveCount userCount();
}
