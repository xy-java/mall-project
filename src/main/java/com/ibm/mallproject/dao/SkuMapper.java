package com.ibm.mallproject.dao;

import com.ibm.mallproject.entity.SkuInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName SkuMapper
 * @Description
 * @Author yjq
 * @Date 2022/6/5 14:14
 * @Version 1.0
 */
@Mapper
@Repository
public interface SkuMapper {

    //删除所有
    Integer deleteSkuAll();

    //查询所有商品
    List<SkuInfo> selectSkuAll();

    //新增
    Integer insertSku(SkuInfo skuInfo);

    //通过id删除(可批量删除)
    Integer deleteSkuById(@Param("ids") List<String> sku_ids);

    //修改商品信息
    Integer updateSku(SkuInfo skuInfo);

    //通过id 修改 商品描述
    Integer updateSkuDesc(@Param("id") String id, @Param("desc") String desc);

    //查询商品信息ById
    SkuInfo selectSkuById(@Param("id") String id);

    //按商品名模糊查询，价格(区间)查询,也可查询全部
    List<SkuInfo> selectSkuNamePrice(@Param("name") String name,
                                     @Param("low") Double low,
                                     @Param("up") Double up);

    //按库存查询(升降)
    //List<SkuInfo> selectSkuStore();

    //按销量查询(升降)
    //List<SkuInfo> selectSkuSalcount();
}
