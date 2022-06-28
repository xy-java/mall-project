package com.ibm.mallproject.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
        orderInfo.setAddress_id(map.get("address_id").toString());

        OrderDetail orderDetail = new OrderDetail();
        List<Map<String,Object>> sku_info = (List<Map<String, Object>>) map.get("sku_info");
        for (int i = 0; i < sku_info.size(); i++) {
            Map<String,Object> sku_map = sku_info.get(i);
            orderDetail.setDetail_id(CommonUtil.getUUID());
            orderDetail.setOrder_id(orderInfo.getOrder_id());
            orderDetail.setSku_id(sku_map.get("sku_id").toString());
            orderDetail.setSku_series(sku_map.get("sku_series").toString());
            orderDetail.setSku_version(sku_map.get("sku_version").toString());
            orderDetail.setSku_color(sku_map.get("sku_color").toString());
            orderDetail.setSku_cp(sku_map.get("sku_cp").toString());
            orderDetail.setCreate_time(new Date());
            orderDetail.setOrder_price(Double.valueOf(sku_map.get("order_price").toString()));
            orderDetail.setOrder_num(Integer.valueOf(sku_map.get("order_num").toString()));
            orderDetailMapper.insertDetail(orderDetail);
            total_amount += Double.valueOf(sku_map.get("order_price").toString()) * Integer.valueOf(sku_map.get("order_num").toString());
            //删除购物车
            cartMapper.deleteCartById(sku_map.get("cart_id").toString());
        }
        orderInfo.setTotal_amount(total_amount);

        return orderInfoMapper.insertOrderInfo(orderInfo) > 0 ? orderInfo.getOrder_id() : "fail";
    }

    @Override
    public List<Map<String,String>> selectDetail(String cart_id) {
        List<String> cart_ids = new ArrayList<>();
        for (String cart_id1 : cart_id.split(",")) {
            cart_ids.add(cart_id1);
        }
        List<CartInfo> cartInfo = cartMapper.selectCartByIds(cart_ids);
        List<Map<String,String>>  list = new ArrayList<>();
        for (int i = 0; i < cartInfo.size(); i++) {
            HashMap<String,String> hashMap = new HashMap<>();
            SkuInfo skuInfo = skuMapper.selectSkuById(cartInfo.get(i).getSku_id());
            hashMap.put("sku_name",skuInfo.getSku_name());
            hashMap.put("img",skuInfo.getImg());
            hashMap.put("sku_id",skuInfo.getSku_id());
            hashMap.put("cart_id",cartInfo.get(i).getCart_id());

            hashMap.put("sku_type",skuInfo.getSku_type());
            hashMap.put("sku_version",cartInfo.get(i).getSku_version());
            hashMap.put("sku_color",cartInfo.get(i).getSku_color());
            hashMap.put("sku_cp",cartInfo.get(i).getSku_cp());
            hashMap.put("sku_series",cartInfo.get(i).getSku_series());

            hashMap.put("order_price", String.valueOf(skuInfo.getPrice()));
            hashMap.put("order_num", String.valueOf(cartInfo.get(i).getCart_num()));
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
        //删除明细
        orderDetailMapper.deleteByOrderId(order_id);
        return orderInfoMapper.deleteOrderInfoById(order_id);
    }

    @Override
    public Integer updateOrderInfoStatus(OrderInfo orderInfo) {
        return orderInfoMapper.updateOrderInfoStatus(orderInfo);
    }

    @Override
    public String payOrder(String order_id) {

        //修改订单状态
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrder_id(order_id);
        orderInfoMapper.updateOrderInfoStatus(orderInfo);

        //修改商品库存
        List<OrderDetail> orderDetails = orderDetailMapper.selectDetail(order_id);
        for (int i = 0; i < orderDetails.size(); i++) {
            skuMapper.updateSkuStore(orderDetails.get(i).getSku_id(),orderDetails.get(i).getOrder_num());
        }

        return "success";
    }

    @Override
    public List<Map> getDetailList(String order_id) {
        List<OrderDetail> orderDetails = orderDetailMapper.selectByOrderId(order_id);
        List<Map> list = new ArrayList<>();
        for (int i = 0; i < orderDetails.size(); i++) {
            HashMap<String,String> hashMap = new HashMap<>();
            SkuInfo skuInfo = skuMapper.selectSkuById(orderDetails.get(i).getSku_id());
            hashMap.put("sku_name",skuInfo.getSku_name());
            hashMap.put("img",skuInfo.getImg());
            hashMap.put("sku_id",skuInfo.getSku_id());
            hashMap.put("order_price", String.valueOf(orderDetails.get(i).getOrder_price()));
            hashMap.put("order_num", String.valueOf(orderDetails.get(i).getOrder_num()));
            hashMap.put("sku_type",skuInfo.getSku_type());
            hashMap.put("sku_version",orderDetails.get(i).getSku_version());
            hashMap.put("sku_color",orderDetails.get(i).getSku_color());
            hashMap.put("sku_cp",orderDetails.get(i).getSku_cp());
            hashMap.put("sku_series",orderDetails.get(i).getSku_series());
            hashMap.put("order_id",orderDetails.get(i).getOrder_id());
            list.add(hashMap);
        }
        return list;
    }
}
