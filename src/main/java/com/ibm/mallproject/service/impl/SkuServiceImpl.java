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

    @Override
    public Integer deleteSkuAll() {
        return null;
    }

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
        return skuMapper.insertSku(skuInfo);
    }

    @Override
    public Integer deleteSkuById(List<String> user_Id) {
        return null;
    }

    @Override
    public Integer updateSku(Map<String, String> skuMap) {
        return null;
    }

    @Override
    public Integer updateSkuDesc(String id, String desc) {
        return null;
    }

    @Override
    public SkuInfo selectSkuById(String id) {
        return null;
    }

    @Override
    public List<SkuInfo> selectSkuAll() {
        return null;
    }

    @Override
    public List<SkuInfo> selectSkuNamePrice(String name, Double up, Double low) {
        return null;
    }
}
