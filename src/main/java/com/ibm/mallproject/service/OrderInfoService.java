package com.ibm.mallproject.service;

import com.ibm.mallproject.entity.OrderDetail;
import com.ibm.mallproject.entity.OrderInfo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

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
     * @param
     * @return
     */
    String insertOrderInfo(Map<String, Object> map);


    List<Map<String,String>> selectDetail(String cart_id);


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


    Integer updateOrderInfoStatus(OrderInfo orderInfo);

    String payOrder(String order_id);

    List<Map> getDetailList(String order_id);
}
