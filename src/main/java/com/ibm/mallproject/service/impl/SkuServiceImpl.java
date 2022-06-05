package com.ibm.mallproject.service.impl;

import com.ibm.mallproject.dao.SkuMapper;
import com.ibm.mallproject.entity.SkuInfo;
import com.ibm.mallproject.service.SkuService;
import com.ibm.mallproject.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SkuServiceImpl
 * @Description
 * @Author yjq
 * @Date 2022/6/5 14:16
 * @Version 1.0
 */
@Service
@Transactional
public class SkuServiceImpl implements SkuService {

    @Autowired
    SkuMapper skuMapper;

    //删除所有
    @Override
    public Integer deleteSkuAll() {
        return skuMapper.deleteSkuAll();
    }

    //新增
    @Override
    public Integer insertSku(Map<String, String> skuMap) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setSku_id(CommonUtil.getUUID());
        skuInfo.setPrice(Double.parseDouble(skuMap.get("price")));
        skuInfo.setSku_name(skuMap.get("sku_name"));
        skuInfo.setSku_desc(skuMap.get("sku_desc"));
        skuInfo.setStore(Integer.parseInt(skuMap.get("store")));
        skuInfo.setSalcount(Integer.parseInt(skuMap.get("salcount")));
        skuInfo.setImg(skuMap.get("img"));
        skuInfo.setCreate_time(new Date());
        skuInfo.setSku_summary(skuMap.get("sku_summary"));
        skuInfo.setParameter_id(skuMap.get("parameter_id"));

        return skuMapper.insertSku(skuInfo);
    }

    //通过id删除(可批量删除)
    @Override
    public Integer deleteSkuById(List<String> user_Id) {
        return skuMapper.deleteSkuById(user_Id);
    }

    //修改商品信息
    @Override
    public Integer updateSku(Map<String, String> skuMap) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setSku_id(skuMap.get("sku_id"));
        skuInfo.setPrice(Double.parseDouble(skuMap.get("price")));
        skuInfo.setSku_name(skuMap.get("sku_name"));
        skuInfo.setSku_desc(skuMap.get("sku_desc"));
        skuInfo.setStore(Integer.parseInt(skuMap.get("store")));
        skuInfo.setSalcount(Integer.parseInt(skuMap.get("salcount")));

        return skuMapper.updateSku(skuInfo);
    }

    //通过id 修改 商品描述
    @Override
    public Integer updateSkuDesc(String id, String desc) {
        return skuMapper.updateSkuDesc(id, desc);
    }

    //查询商品信息ById
    @Override
    public SkuInfo selectSkuById(String id) {
        return skuMapper.selectSkuById(id);
    }

    //查询所有商品
    @Override
    public List<SkuInfo> selectSkuAll() {
        return skuMapper.selectSkuAll();
    }

    //按商品名模糊查询，价格(区间)查询,也可查询全部
    @Override
    public List<SkuInfo> selectSkuNamePrice(String name, Double low, Double up) {
        double i;
        if (low > up) {
            i = low;
            low = up;
            up = i;
        }
        return skuMapper.selectSkuNamePrice(name, low, up);
    }
}
