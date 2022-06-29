package com.ibm.mallproject.controller;

import com.alibaba.fastjson.JSONObject;
import com.ibm.mallproject.entity.OrderDetail;
import com.ibm.mallproject.entity.OrderInfo;
import com.ibm.mallproject.service.OrderInfoService;
import com.ibm.mallproject.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
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
    @Autowired
    private SkuService skuService;

//    /**
//     * 用户下单
//     * @param orderInfo
//     * @return
//     */
//    Integer insertOrderInfo(OrderInfo orderInfo);

    @RequestMapping("/insertOrderInfo")
    @ResponseBody
    public String insertOrderInfo(@RequestBody JSONObject orderInfo) {
        Map map = orderInfo;
        return orderInfoService.insertOrderInfo(map);
    }

    @RequestMapping("/paySuccess")
    @ResponseBody
    public String paySuccess(@RequestBody JSONObject info){
        Map map = info;
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrder_id(map.get("order_id").toString());
        //修改订单状态
        orderInfoService.updateOrderInfoStatus(orderInfo);
        List<Map<String,Object>> sku_info = (List<Map<String, Object>>) map.get("sku_info");
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < sku_info.size(); i++) {
            HashMap<String,Object> hashMap = new HashMap<String,Object>();
            String sku_id = sku_info.get(i).get("sku_id").toString();
            Integer sku_num = Integer.parseInt(sku_info.get(i).get("order_num").toString());
            hashMap.put("sku_id",sku_id);
            hashMap.put("sku_num",sku_num);
            list.add(hashMap);
        }

        //遍历list
        for (int i = 0; i < list.size(); i++) {
            Map<String,Object> map1 = list.get(i);
            String sku_id = map1.get("sku_id").toString();
            Integer sku_num = Integer.parseInt(map1.get("sku_num").toString());
            skuService.updateSkuStore(sku_id,sku_num);
        }

        //修改商品库存
        return null;
    }

    @RequestMapping("/selectSkuInfo")
    @ResponseBody
    public List<Map<String,String>> selectSkuInfo(@RequestParam String cart_id){
        return orderInfoService.selectDetail(cart_id);
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
    @RequestMapping("/selectOrderInfoByUserId")
    @ResponseBody
    public List<OrderInfo> selectOrderInfoByUserId(@RequestParam  String user_id){
        return orderInfoService.selectOrderInfoByUserId(user_id);
    }

//    //根据订单id查询订单明细

//    /**
//     * 取消订单
//     * @param order_id
//     * @return
//     */
    @RequestMapping("deleteOrderInfoById")
    @ResponseBody
    public String deleteOrderInfoById(String order_id){
        return orderInfoService.deleteOrderInfoById(order_id)>0 ? "删除成功":"删除失败";
    }

    @RequestMapping("/payOrder")
    @ResponseBody
    public String payOrder(@RequestParam String order_id){
        return orderInfoService.payOrder(order_id);
    }

	@RequestMapping("/getDetailList")
	@ResponseBody
	public List<Map> getDetailList(@RequestParam String order_id){
		return orderInfoService.getDetailList(order_id);
	}

    @RequestMapping("/updateEnd")
    @ResponseBody
    public String updateEnd(@RequestParam String order_id){
        return orderInfoService.updateEnd(order_id)>0 ? "修改成功":"修改失败";
    }
}
