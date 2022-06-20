package com.ibm.mallproject.service.impl;

import com.ibm.mallproject.dao.CartMapper;
import com.ibm.mallproject.dao.OrderInfoMapper;
import com.ibm.mallproject.dao.SkuMapper;
import com.ibm.mallproject.entity.CartInfo;
import com.ibm.mallproject.entity.OrderInfo;
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

    @Override
    public Integer insertOrderInfo(Map<String, Object> map) {
        Double amount = 0.0;//总金额

        List<String> cart_ids = (List<String>) map.get("cart_id");
        for (String cart_id : cart_ids) {
            //计算订单中每个商品的总价
            //通过订单表中的购物车id查商品单价(商品表根据id查单价)和数量
            CartInfo cartInfo = cartMapper.selectCartById(cart_id);
            Double price = skuMapper.selectSkuById(cartInfo.getSku_id()).getPrice();
            Integer cart_num = cartInfo.getCart_num();
            amount += (price * cart_num);


        }

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrder_id(CommonUtil.getUUID());
        orderInfo.setTotal_amount(amount);
        orderInfo.setUser_id(map.get("user_id").toString());
        orderInfo.setPayment_way(map.get("payment_way").toString());
        orderInfo.setOrder_status(Integer.parseInt(map.get("order_status").toString()));
        orderInfo.setCreate_time(new Date());
        //orderInfo.setCart_id(cart_id);

        return orderInfoMapper.insertOrderInfo(orderInfo);
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
