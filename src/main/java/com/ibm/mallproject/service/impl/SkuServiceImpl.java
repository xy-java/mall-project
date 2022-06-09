package com.ibm.mallproject.service.impl;

import com.ibm.mallproject.dao.ParameterMapper;
import com.ibm.mallproject.dao.SkuMapper;
import com.ibm.mallproject.entity.ParamterInfo;
import com.ibm.mallproject.entity.SkuInfo;
import com.ibm.mallproject.service.SkuService;
import com.ibm.mallproject.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
    private SkuMapper skuMapper;
    @Autowired
    private ParameterMapper parameterMapper;

    @Override
    public List<SkuInfo> selectSkuByStatus(Integer sku_status) {
        return skuMapper.selectSkuByStatus(sku_status);
    }

    @Override
    public List<SkuInfo> queryByName(String sku_name) {
        return skuMapper.queryByName(sku_name);
    }

    @Override
    public List<SkuInfo> queryByNameStatus(String sku_name) {
        return skuMapper.queryByNameStatus(sku_name);
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
        ParamterInfo paramterInfo = new ParamterInfo();
        paramterInfo.setParameter_id(CommonUtil.getUUID());
        paramterInfo.setParameter_versions(skuMap.get("parameter_versions"));
        paramterInfo.setParameter_color(skuMap.get("parameter_color"));
        paramterInfo.setParameter_cp(skuMap.get("parameter_cp"));
        paramterInfo.setParameter_series(skuMap.get("parameter_series"));
        paramterInfo.setCreate_time(new Date());


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
        skuInfo.setParameter_id(paramterInfo.getParameter_id());
        skuInfo.setSku_type(skuMap.get("sku_type"));
        skuInfo.setSku_status(Integer.valueOf(skuMap.get("sku_status")));


        parameterMapper.insertParameter(paramterInfo);

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

    @Override
    public List<Map> selectParamter(List<String> sku_id) {
        List<SkuInfo> list = skuMapper.selectByIds(sku_id);
        List<ParamterInfo> list1 = parameterMapper.selectBySkuId(list.get(0).getParameter_id());
        List<Map> list2 = new ArrayList<Map>();
        HashMap<String,Object> hashMap = new HashMap<String,Object>();
        hashMap.put("sku_id",list.get(0).getSku_id());
        hashMap.put("sku_name",list.get(0).getSku_name());
        hashMap.put("price",list.get(0).getPrice());
        hashMap.put("sku_desc",list.get(0).getSku_desc());
        hashMap.put("store",list.get(0).getStore());
        hashMap.put("img",list.get(0).getImg());
        hashMap.put("sku_type",list.get(0).getSku_type());
        hashMap.put("sku_status",list.get(0).getSku_status());
        hashMap.put("salcount",list.get(0).getSalcount());
        hashMap.put("sku_summary",list.get(0).getSku_summary());
        hashMap.put("parameter_id",list.get(0).getParameter_id());

        hashMap.put("parameter_versions",list1.get(0).getParameter_versions());
        hashMap.put("parameter_color",list1.get(0).getParameter_color());
        hashMap.put("parameter_cp",list1.get(0).getParameter_cp());
        hashMap.put("parameter_series",list1.get(0).getParameter_series());
        list2.add(hashMap);
        return list2;
    }

    @Override
    public List<SkuInfo> searchSkuName() {
        return skuMapper.searchSkuName();
    }

    //修改商品信息
    @Override
    public Integer updateSku(Map<String, String> skuMap) {
        ParamterInfo paramterInfo = new ParamterInfo();
        paramterInfo.setCreate_time(new Date());
        paramterInfo.setParameter_id(skuMap.get("parameter_id"));
        paramterInfo.setParameter_versions(skuMap.get("parameter_versions"));
        paramterInfo.setParameter_color(skuMap.get("parameter_color"));
        paramterInfo.setParameter_cp(skuMap.get("parameter_cp"));
        paramterInfo.setParameter_series(skuMap.get("parameter_series"));


        parameterMapper.updateParameter(paramterInfo);

        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setSku_id(skuMap.get("sku_id"));
        skuInfo.setPrice(Double.parseDouble(skuMap.get("price")));
        skuInfo.setSku_name(skuMap.get("sku_name"));
        skuInfo.setSku_desc(skuMap.get("sku_desc"));
        skuInfo.setStore(Integer.parseInt(skuMap.get("store")));

		skuInfo.setImg(skuMap.get("img").contains("http")? skuMap.get("img").substring(22) : skuMap.get("img"));
		skuInfo.setSku_type(skuMap.get("sku_type"));
		skuInfo.setSku_status(Integer.valueOf(skuMap.get("sku_status")));

		//删除之前的图片
		SkuInfo sku = skuMapper.selectSkuById(skuMap.get("sku_id"));
		String img = sku.getImg();
        ClassPathResource resource = new ClassPathResource("static/");
        String path = null;
        if((skuMap.get("img").contains("http")? skuMap.get("img").substring(22) : skuMap.get("img")).equals(img)){
            return skuMapper.updateSku(skuInfo);
        }else{
            try {
                path = resource.getFile().getPath();
                new File(path.split("target")[0] + "src\\main\\resources\\static\\" + img).delete();
                new File(path+"\\" + img).delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return skuMapper.updateSku(skuInfo);
        }

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
