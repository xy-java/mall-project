package com.ibm.mallproject.dao;

import com.ibm.mallproject.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName OrderInfoMapper
 * @Author yjq
 * @Date 2022/6/20 13:37
 * @Version 1.0
 * @Description 订单
 */
@Mapper
@Repository
public interface OrderInfoMapper {

    /**
     * 用户下单
     * @param orderInfo
     * @return
     */
    Integer insertOrderInfo(OrderInfo orderInfo);

    Integer updateAddress(String address_id,String order_id);

    /**
     * 查询当前用户的所有订单
     * @param user_id
     * @return
     */
    List<OrderInfo> selectOrderInfoByUserId(String user_id);

    //根据订单id查询订单明细

    /**
     * 取消订单
     * @param order_id
     * @return
     */
    Integer deleteOrderInfoById(String order_id);
}
