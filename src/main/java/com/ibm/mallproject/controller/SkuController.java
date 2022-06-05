package com.ibm.mallproject.controller;

import com.ibm.mallproject.entity.SkuInfo;
import com.ibm.mallproject.service.SkuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SkuController
 * @Description
 * @Author yjq
 * @Date 2022/6/5 14:12
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/sku")
public class SkuController {

    @Autowired
    SkuService skuService;

//    //删除所有
//    Integer deleteSkuAll();


//    //新增
//    Integer insertSku(Map<String,String> skuMap);
    public String insertSku(@RequestParam("skuMap") Map<String,String> skuMap){
        Integer integer = skuService.insertSku(skuMap);
        if (integer > 0){
            return "添加成功";
        }else return "添加失败";
    }

//    //通过id删除(可批量删除)
//    Integer deleteSkuById(List<String> user_Id);

//    //修改商品信息
//    Integer updateSku(Map<String,String> skuMap);

//    //通过id 修改 商品描述
//    Integer updateSkuDesc(String id, String desc);

//    //查询商品信息ById
//    SkuInfo selectSkuById(String id);

//    //查询所有商品
//    List<SkuInfo> selectSkuAll();

//    //按商品名，价格(区间)查询
//    List<SkuInfo> selectSkuNamePrice(String name,
//                                     Double up,
//                                     Double low);

}
