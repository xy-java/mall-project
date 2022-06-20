package com.ibm.mallproject.service;

import com.ibm.mallproject.entity.OrderInfo;

import java.util.List;

/**
 * @ClassName OrderInfoService
 * @Author yjq
 * @Date 2022/6/20 13:42
 * @Version 1.0
 * @Description
 */
public interface OrderInfoService {
    /**
     * 用户下单
     * @param orderInfo
     * @return
     */
    Integer insertOrderInfo(OrderInfo orderInfo);

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
