package com.ibm.mallproject.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.ibm.mallproject.config.AliPayConfig;
import com.ibm.mallproject.entity.OrderInfo;
import com.ibm.mallproject.service.OrderInfoService;
import com.ibm.mallproject.service.SkuService;
import com.sun.deploy.net.URLEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

    @RequestMapping("/pay")
    @ResponseBody
    public String pay(@RequestBody JSONObject map) throws AlipayApiException {
        Map map1 = map;
        if(map1.get("pages").equals("orderPage")){
            AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.server_url,AliPayConfig.app_id,AliPayConfig.merchant_private_key,AliPayConfig.format,AliPayConfig.charset,AliPayConfig.alipayPublicKey,AliPayConfig.sign_type);
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            request.setNotifyUrl("");
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", map.get("order_id"));
            bizContent.put("total_amount", map.get("total_amount"));
            bizContent.put("subject", map.get("order_name"));
            bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
            JSONArray goodsDetail = new JSONArray();
            List<Map<String,Object>> sku_info = (List<Map<String, Object>>) map1.get("goods_detail");
            for (int i = 0; i < sku_info.size(); i++) {
                JSONObject goods1 = new JSONObject();
                goods1.put("goods_id", sku_info.get(i).get("sku_id"));
                goods1.put("goods_name", sku_info.get(i).get("sku_name"));
                goods1.put("quantity", sku_info.get(i).get("order_num"));
                goods1.put("price", sku_info.get(i).get("order_price"));
                goodsDetail.add(goods1);
            }
            bizContent.put("goods_detail", goodsDetail);
            request.setReturnUrl("http://localhost:8081/orderInfo/payFor?goodsDetail="+goodsDetail + "&pages=" + map1.get("pages"));
            request.setBizContent(bizContent.toString());
            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
            if(response.isSuccess()){
                return response.getBody();
            } else {
                return "调用失败";
            }
        }else{
            AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.server_url,AliPayConfig.app_id,AliPayConfig.merchant_private_key,AliPayConfig.format,AliPayConfig.charset,AliPayConfig.alipayPublicKey,AliPayConfig.sign_type);
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            request.setNotifyUrl("");
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", map.get("order_id"));
            bizContent.put("total_amount", map.get("total_amount"));
            bizContent.put("subject", map.get("order_name"));
            bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
            request.setReturnUrl("http://localhost:8081/orderInfo/payFor?goodsDetail=1" + "&pages=" + map1.get("pages"));
            request.setBizContent(bizContent.toString());
            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
            if(response.isSuccess()){
                return response.getBody();
            } else {
                return "调用失败";
            }
        }

    }

    @RequestMapping("/payFor")
    public void payFor( HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("pages").equals("orderPage")){
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrder_id(request.getParameter("out_trade_no"));
            //修改订单状态
            orderInfoService.updateOrderInfoStatus(orderInfo);
            List<Map> sku_info = JSON.parseArray(request.getParameter("goodsDetail"), Map.class);
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            for (int i = 0; i < sku_info.size(); i++) {
                HashMap<String,Object> hashMap = new HashMap<String,Object>();
                String sku_id = sku_info.get(i).get("goods_id").toString();
                Integer sku_num = Integer.parseInt(sku_info.get(i).get("quantity").toString());
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
            response.sendRedirect("http://localhost:8080/");
//            response.sendRedirect("http://3usjhb.natappfree.cc/");
        }else if(request.getParameter("pages").equals("orderConfig")){
            orderInfoService.payOrder(request.getParameter("out_trade_no"));
            response.sendRedirect("http://localhost:8080/#/orderConfig");
//            response.sendRedirect("http://3usjhb.natappfree.cc/#/orderConfig");
        }
    }



}
