package com.ibm.mallproject.service.impl;

import com.ibm.mallproject.dao.AddressMapper;
import com.ibm.mallproject.entity.AddressInfo;
import com.ibm.mallproject.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressMapper addressMapper;


	@Override
	public Integer insertAddress(AddressInfo addressInfo) {
		return addressMapper.insertAddress(addressInfo);
	}

	@Override
	public List<AddressInfo> queryAddress(String user_id) {
		return addressMapper.queryAddress(user_id);
	}

	@Override
	public Integer queryStatus(String user_id) {
		return addressMapper.queryStatus(user_id);
	}

	@Override
	public List<AddressInfo> selectById(String address_id) {
		return addressMapper.selectById(address_id);
	}

	@Override
	public Integer updateStatus(String user_id) {
		return addressMapper.updateStatus(user_id);
	}

	@Override
	public Integer update(AddressInfo addressInfo) {
		return addressMapper.update(addressInfo);
	}

	@Override
	public Integer deleteById(String address_id) {
		return addressMapper.deleteById(address_id);
	}
}
