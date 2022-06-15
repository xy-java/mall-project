package com.ibm.mallproject.dao;

import com.ibm.mallproject.entity.AddressInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AddressMapper {
	Integer insertAddress(AddressInfo addressInfo);

	List<AddressInfo> queryAddress(String user_id);

	Integer queryStatus(String user_id);

}
