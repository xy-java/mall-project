package com.ibm.mallproject.service.impl;

import com.ibm.mallproject.dao.AddressMapper;
import com.ibm.mallproject.entity.AddressInfo;
import com.ibm.mallproject.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressMapper addressMapper;


	@Override
	public Integer insertAddress(AddressInfo addressInfo) {
		return addressMapper.insertAddress(addressInfo);
	}
}
