package com.ibm.mallproject.service.impl;

import com.ibm.mallproject.dao.*;
import com.ibm.mallproject.entity.*;
import com.ibm.mallproject.service.OrderInfoService;
import com.ibm.mallproject.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName OrderInfoServiceImpl
 * @Author yjq
 * @Date 2022/6/20 13:42
 * @Version 1.0
 * @Description
 */
@Service
@Transactional
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private CartMapper cartMapper;//用id查商品id和数量

    @Autowired
    private SkuMapper skuMapper;//用商品id查单价
    @Autowired
    private OrderDetailMapper orderDetailMapper;//插入订单明细
    @Autowired
    private AddressMapper addressMapper;//查询用户地址

    @Override
    public String insertOrderInfo(Map<String, Object> map) {
        Double total_amount = 0.0;
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrder_id(CommonUtil.getUUID());
        orderInfo.setUser_id(map.get("user_id").toString());
        orderInfo.setCreate_time(new Date());
        orderInfo.setOrder_status(0);
        orderInfo.setPayment_way("在线支付");
        if(map.get("sku_id").toString() != ""){
            CartInfo cartInfo = new CartInfo();
            cartInfo.setUser_id(map.get("user_id").toString());
            cartInfo.setSku_id(map.get("sku_id").toString());
            cartInfo.setSku_version(map.get("sku_version").toString());
            cartInfo.setSku_color(map.get("sku_color").toString());
            cartInfo.setSku_cp(map.get("sku_cp").toString());
            cartInfo.setSku_series(map.get("sku_series").toString());
            CartInfo cartInfo1 = cartMapper.selectOneCart(cartInfo);
            String cart_id = cartInfo1.getCart_id();
            SkuInfo skuInfo = skuMapper.selectSkuById(map.get("sku_id").toString());
            orderInfo.setTotal_amount(skuInfo.getPrice() * cartInfo1.getCart_num());
            orderInfo.setAddress_id(map.get("address_id").toString());

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setDetail_id(CommonUtil.getUUID());
            orderDetail.setOrder_id(orderInfo.getOrder_id());
            orderDetail.setSku_id(cartInfo.getSku_id());
            orderDetail.setCreate_time(new Date());
            orderDetail.setOrder_price(skuInfo.getPrice());
            orderDetail.setOrder_num(cartInfo1.getCart_num());
            orderDetailMapper.insertDetail(orderDetail);
            cartMapper.deleteCartById(cart_id);

            orderInfoMapper.insertOrderInfo(orderInfo);
            return orderInfo.getOrder_id();

        }else{
            List<String> cart_ids = new ArrayList<>();
            for (String cart_id : map.get("cart_ids").toString().split(",")) {
                cart_ids.add(cart_id);
            }

            System.err.println(cart_ids);
            for (int i = 0; i < cart_ids.size(); i++) {
                CartInfo cartInfo = cartMapper.selectCartById(cart_ids.get(i));
                SkuInfo skuInfo = skuMapper.selectSkuById(cartInfo.getSku_id());
                total_amount += skuInfo.getPrice() * cartInfo.getCart_num();
                OrderDetail orderDetail1 = new OrderDetail();
                orderDetail1.setDetail_id(CommonUtil.getUUID());
                orderDetail1.setOrder_id(orderInfo.getOrder_id());
                orderDetail1.setSku_id(cartInfo.getSku_id());
                orderDetail1.setCreate_time(new Date());
                orderDetail1.setOrder_price(skuInfo.getPrice());
                orderDetail1.setOrder_num(cartInfo.getCart_num());
                orderDetailMapper.insertDetail(orderDetail1);
                cartMapper.deleteCartById(cart_ids.get(i));
            }
            orderInfo.setTotal_amount(total_amount);
            List<AddressInfo> address_id = addressMapper.queryAddress(map.get("user_id").toString());
            orderInfo.setAddress_id(address_id.get(0).getAddress_id());

            orderInfoMapper.insertOrderInfo(orderInfo);
            return orderInfo.getOrder_id();
        }
    }

    @Override
    public List<Map<String,String>> selectDetail(String order_id) {
        List<OrderDetail> orderDetails = orderDetailMapper.selectDetail(order_id);
        List<Map<String,String>>  list = new ArrayList<>();
        for (int i = 0; i < orderDetails.size(); i++) {
            HashMap<String,String> hashMap = new HashMap<>();
            OrderDetail orderDetail = orderDetailMapper.selectById(orderDetails.get(i).getDetail_id());
            SkuInfo skuInfo = skuMapper.selectSkuById(orderDetail.getSku_id());
            hashMap.put("sku_name",skuInfo.getSku_name());
            hashMap.put("img",skuInfo.getImg());
            hashMap.put("order_price", String.valueOf(orderDetail.getOrder_price()));
            hashMap.put("order_num", String.valueOf(orderDetail.getOrder_num()));
            hashMap.put("detail_id", orderDetail.getDetail_id());
            list.add(hashMap);
        }

        return list;
    }

    @Override
    public Integer updateAddress(String address_id,String order_id) {
        return orderInfoMapper.updateAddress(address_id,order_id);
    }

    @Override
    public List<OrderInfo> selectOrderInfoByUserId(String user_id) {
        return orderInfoMapper.selectOrderInfoByUserId(user_id);
    }

    @Override
    public Integer deleteOrderInfoById(String order_id) {
        return orderInfoMapper.deleteOrderInfoById(order_id);
    }
}
