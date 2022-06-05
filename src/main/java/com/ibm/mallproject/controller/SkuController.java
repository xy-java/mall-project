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

    //删除所有
    @RequestMapping("/deleteSkuAll")
    public String deleteSkuAll() {
        return (skuService.deleteSkuAll() > 0) ? "删除成功" : "删除失败";
    }

    //新增
    @RequestMapping("/insertSku")
    public String insertSku(@RequestParam("skuMap") Map<String, String> skuMap) {
        return (skuService.insertSku(skuMap) > 0) ? "添加成功" : "添加失败";
    }

    //通过id删除(可批量删除)
    @RequestMapping("/deleteSkuById")
    public String deleteSkuById(@RequestParam("sku_id") List<String> sku_Id) {
        return (skuService.deleteSkuById(sku_Id) > 0) ? "删除成功" : "删除失败";
    }

    //修改商品信息
    @RequestMapping("/updateSku")
    public String updateSku(@RequestParam("skuMap") Map<String, String> skuMap) {
        return (skuService.updateSku(skuMap) > 0) ? "修改成功" : "修改失败";
    }

    //通过id 修改 商品描述
    @RequestMapping("/updateSkuDesc")
    public String updateSkuDesc(@RequestParam("id") String id,
                                @RequestParam("/desc") String desc) {
        return (skuService.updateSkuDesc(id, desc) > 0) ? "修改成功" : "修改失败";
    }


    //查询商品信息ById
    @RequestMapping("/selectSkuById")
    public SkuInfo selectSkuById(@RequestParam("id") String id) {
        return skuService.selectSkuById(id);
    }

    //查询所有商品
    @RequestMapping("/selectSkuAll")
    public List<SkuInfo> selectSkuAll() {
        return skuService.selectSkuAll();
    }

    //按商品名模糊查询，价格(区间)查询,也可查询全部
    @RequestMapping("/selectSkuNamePrice")
    public List<SkuInfo> selectSkuNamePrice(@RequestParam("name") String name,
                                            @RequestParam("low") Double low,
                                            @RequestParam("up") Double up) {
        return skuService.selectSkuNamePrice(name, low, up);
    }

}
