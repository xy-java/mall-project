package com.ibm.mallproject.service;

import com.ibm.mallproject.entity.AddressInfo;

import java.util.List;


public interface AddressService {
	Integer insertAddress(AddressInfo addressInfo);

	List<AddressInfo> queryAddress(String user_id);

	Integer queryStatus(String user_id);

	List<AddressInfo> selectById(String address_id);

	Integer updateStatus(String user_id);

	Integer update(AddressInfo addressInfo);

	Integer deleteById(String address_id);
}
