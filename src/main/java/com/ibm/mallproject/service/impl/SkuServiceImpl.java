package com.ibm.mallproject.service.impl;

import com.ibm.mallproject.dao.SkuMapper;
import com.ibm.mallproject.entity.SkuInfo;
import com.ibm.mallproject.service.SkuService;
import com.ibm.mallproject.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
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
    public List<SkuInfo> selectSkuByStatus(Integer sku_status) {
        return skuMapper.selectSkuByStatus(sku_status);
    }

    @Override
    public List<SkuInfo> queryByName(String sku_name) {
        return skuMapper.queryByName(sku_name);
    }
    //查询所有商品
    @Override
    public List<SkuInfo> selectSkuAll() {
        return skuMapper.selectSkuAll();
    }

    //未使用
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
        skuInfo.setSku_type(skuMap.get("sku_type"));
        skuInfo.setSku_status(Integer.valueOf(skuMap.get("sku_status")));

        return skuMapper.insertSku(skuInfo);
    }

    //通过id删除(可批量删除)
    @Override
    public Integer deleteSkuById(List<String> sku_id) {
        //查找对应的图片信息，从staic删除
        List<SkuInfo> list = skuMapper.selectByIds(sku_id);
        ClassPathResource resource = new ClassPathResource("static/");
        String path = null;
        try {
            path = resource.getFile().getPath();
            for (int i = 0; i < list.size(); i++) {
                new File(path.split("target")[0] + "src\\main\\resources\\static\\" + list.get(i).getImg()).delete();
                new File(path+"\\" + list.get(i).getImg()).delete();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return skuMapper.deleteSkuById(sku_id);
    }

    @Override
    public Integer updateStatusById(List<String> sku_id, String sku_status) {
        return skuMapper.updateStatusById(sku_id,sku_status);
    }


    @Override
    public List<SkuInfo> selectByIds(List<String> sku_id) {
        return skuMapper.selectByIds(sku_id);
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
		skuInfo.setImg(skuMap.get("img"));
		skuInfo.setSku_type(skuMap.get("sku_type"));
		skuInfo.setSku_status(Integer.valueOf(skuMap.get("sku_status")));

		//删除之前的图片
		SkuInfo sku = skuMapper.selectSkuById(skuMap.get("sku_id"));
		String img = sku.getImg();

		ClassPathResource resource = new ClassPathResource("static/");
		String path = null;
		try {
			path = resource.getFile().getPath();
			new File(path.split("target")[0] + "src\\main\\resources\\static\\" + img).delete();
			new File(path+"\\" + img).delete();
		} catch (IOException e) {
			e.printStackTrace();
		}


        return skuMapper.updateSku(skuInfo);
    }

    //通过id 修改 商品描述
    @Override
    public Integer updateSkuDesc(String sku_id, String sku_desc) {
        return skuMapper.updateSkuDesc(sku_id, sku_desc);
    }

    //查询商品信息ById
    @Override
    public SkuInfo selectSkuById(String sku_id) {
        return skuMapper.selectSkuById(sku_id);
    }


    //按商品名模糊查询，价格(区间)查询,也可查询全部
    @Override
    public List<SkuInfo> selectSkuNamePrice(String sku_name, Double low_price, Double up_price) {
        double temp;
        if (low_price > up_price) {
            temp = low_price;
            low_price = up_price;
            up_price = temp;
        }
        return skuMapper.selectSkuNamePrice(sku_name, low_price, up_price);
    }
}
