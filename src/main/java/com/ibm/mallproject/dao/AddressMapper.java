package com.ibm.mallproject.dao;

import com.ibm.mallproject.entity.AddressInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AddressMapper {
	Integer insertAddress(AddressInfo addressInfo);
}
