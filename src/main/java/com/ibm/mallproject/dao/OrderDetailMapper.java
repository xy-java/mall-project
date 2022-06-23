package com.ibm.mallproject.dao;

import com.ibm.mallproject.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Repository
public interface OrderDetailMapper {
	Integer insertDetail(OrderDetail orderDetail);

	List<OrderDetail> selectDetail(String cart_id);

	OrderDetail selectById(String detail_id);
}
