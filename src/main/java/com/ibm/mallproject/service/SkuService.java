package com.ibm.mallproject.service;

import com.ibm.mallproject.entity.SkuInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SkuService
 * @Description
 * @Author yjq
 * @Date 2022/6/5 14:16
 * @Version 1.0
 */
public interface SkuService {
    //查询所有商品是否上架
    List<SkuInfo> selectSkuByStatus(Integer sku_status);

    //通过商品名模糊查询
    List<SkuInfo> queryByName(String sku_name);
    //通过商品名模糊查询
    List<SkuInfo> queryByNameStatus(String sku_name);

    //查询所有商品
    List<SkuInfo> selectSkuAll();

    //未使用
    //删除所有
    Integer deleteSkuAll();

    //新增
    Integer insertSku(Map<String,String> skuMap);

    //通过id删除(可批量删除)
    Integer deleteSkuById(List<String> sku_id);

    //通过id上架下架(可批量上架下架)
    Integer updateStatusById(List<String> sku_id,String sku_status);

    //通过id批量查找
    List<SkuInfo> selectByIds(List<String> sku_id);

    //通过id批量查找
    List<Map> selectParamter(List<String> sku_id);


    List<SkuInfo> searchSkuName();


    //修改商品信息
    Integer updateSku(Map<String,String> skuMap);

    //通过id 修改 商品描述
    Integer updateSkuDesc(String sku_Id, String sku_desc);

    //查询商品信息ById
    SkuInfo selectSkuById(String sku_Id);

    //按商品名模糊查询，价格(区间)查询,也可查询全部
    List<SkuInfo> selectSkuNamePrice(String sku_name,Double up_price,Double low_price);
}
