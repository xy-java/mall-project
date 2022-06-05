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

    //删除所有
    Integer deleteSkuAll();

    //新增
    Integer insertSku(Map<String,String> skuMap);

    //通过id删除(可批量删除)
    Integer deleteSkuById(List<String> user_Id);

    //修改商品信息
    Integer updateSku(Map<String,String> skuMap);

    //通过id 修改 商品描述
    Integer updateSkuDesc(String id, String desc);

    //查询商品信息ById
    SkuInfo selectSkuById(String id);

    //查询所有商品
    List<SkuInfo> selectSkuAll();

    //按商品名模糊查询，价格(区间)查询,也可查询全部
    List<SkuInfo> selectSkuNamePrice(String name,
                                     Double up,
                                     Double low);
}
