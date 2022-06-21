package com.ibm.mallproject.dao;

import com.ibm.mallproject.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Mapper
@Repository
public interface OrderDetailMapper {
	Integer insertDetail(OrderDetail orderDetail);
}
