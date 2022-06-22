package com.ibm.mallproject.controller;

import com.ibm.mallproject.entity.OrderDetail;
import com.ibm.mallproject.entity.OrderInfo;
import com.ibm.mallproject.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderInfoController
 * @Author yjq
 * @Date 2022/6/20 13:43
 * @Version 1.0
 * @Description
 */
@Controller
@RequestMapping("/orderInfo")
@CrossOrigin
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

//    /**
//     * 用户下单
//     * @param orderInfo
//     * @return
//     */
//    Integer insertOrderInfo(OrderInfo orderInfo);

    @RequestMapping("/insertOrderInfo")
    @ResponseBody
    public String insertOrderInfo(@RequestParam Map<String,Object> map){
        return orderInfoService.insertOrderInfo(map);
    }


    @RequestMapping("/selectSkuInfo")
    @ResponseBody
    public List<Map<String,String>> selectSkuInfo(@RequestParam String order_id){
        return orderInfoService.selectDetail(order_id);
    }


    @RequestMapping("/updateAddress")
    @ResponseBody
    public String updateAddress(@RequestParam String address_id, @RequestParam String order_id){
        return orderInfoService.updateAddress(address_id,order_id)>0 ? "修改成功":"修改失败";
    }
//    /**
//     * 查询当前用户的所有订单
//     * @param user_id
//     * @return
//     */
//    List<OrderInfo> selectOrderInfoByUserId(String user_id);

//    //根据订单id查询订单明细

//    /**
//     * 取消订单
//     * @param order_id
//     * @return
//     */
//    Integer deleteOrderInfoById(String order_id);
}
