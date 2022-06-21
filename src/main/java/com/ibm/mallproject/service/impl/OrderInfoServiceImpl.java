package com.ibm.mallproject.service.impl;

import com.ibm.mallproject.dao.*;
import com.ibm.mallproject.entity.*;
import com.ibm.mallproject.service.OrderInfoService;
import com.ibm.mallproject.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public Integer insertOrderInfo(Map<String, Object> map) {
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

            return orderInfoMapper.insertOrderInfo(orderInfo);

        }else{
            System.err.println(map.get("cart_ids"));
//            List<String> cart_ids = (List<String>) map.get("cart_ids");
//            for (int i = 0; i < cart_ids.size(); i++) {
//                CartInfo cartInfo = cartMapper.selectCartById(cart_ids.get(i));
//                SkuInfo skuInfo = skuMapper.selectSkuById(cartInfo.getSku_id());
//                total_amount += skuInfo.getPrice() * cartInfo.getCart_num();
//                OrderDetail orderDetail1 = new OrderDetail();
//                orderDetail1.setDetail_id(CommonUtil.getUUID());
//                orderDetail1.setOrder_id(orderInfo.getOrder_id());
//                orderDetail1.setSku_id(cartInfo.getSku_id());
//                orderDetail1.setCreate_time(new Date());
//                orderDetail1.setOrder_price(skuInfo.getPrice());
//                orderDetail1.setOrder_num(cartInfo.getCart_num());
//                orderDetailMapper.insertDetail(orderDetail1);
//                cartMapper.deleteCartById(cart_ids.get(i));
//            }
//            orderInfo.setTotal_amount(total_amount);
//            List<AddressInfo> address_id = addressMapper.queryAddress(map.get("user_id").toString());
//            orderInfo.setAddress_id(address_id.get(0).getAddress_id());
//
//            return orderInfoMapper.insertOrderInfo(orderInfo);
            return 0;
        }
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
