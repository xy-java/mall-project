package com.ibm.mallproject.controller;

import com.ibm.mallproject.entity.SkuInfo;
import com.ibm.mallproject.service.SkuService;
import com.ibm.mallproject.util.CommonUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
@RequestMapping("/sku")
@CrossOrigin
public class SkuController {

    @Autowired
    SkuService skuService;

    @RequestMapping("/selectSkuByStatus")
    @ResponseBody
    public List<SkuInfo> selectSkuByStatus(@RequestParam Integer sku_status) {
        return skuService.selectSkuByStatus(sku_status);
    }

    //通过商品名模糊查询
   @RequestMapping("/queryByName")
   @ResponseBody
   public List<SkuInfo> queryByName(@RequestParam String sku_name){
        return skuService.queryByName(sku_name);
   }

    //删除所有
    @RequestMapping("/deleteSkuAll")
    public String deleteSkuAll() {
        return (skuService.deleteSkuAll() > 0) ? "删除成功" : "删除失败";
    }

    //新增
    @RequestMapping("/insertSku")
    public String insertSku(@RequestParam Map<String, String> skuMap) {

        return (skuService.insertSku(skuMap) > 0) ? "添加成功" : "添加失败";
    }

    //通过id删除(可批量删除)
    @RequestMapping("/deleteSkuById")
    public String deleteSkuById(@RequestParam List<String> sku_Id) {
        return (skuService.deleteSkuById(sku_Id) > 0) ? "删除成功" : "删除失败";
    }

    //修改商品信息
    @RequestMapping("/updateSku")
    public String updateSku(@RequestParam Map<String, String> skuMap) {
        return (skuService.updateSku(skuMap) > 0) ? "修改成功" : "修改失败";
    }

    //通过id 修改 商品描述
    @RequestMapping("/updateSkuDesc")
    public String updateSkuDesc(@RequestParam String sku_id,
                                @RequestParam String sku_desc) {
        return (skuService.updateSkuDesc(sku_id, sku_desc) > 0) ? "修改成功" : "修改失败";
    }


    //查询商品信息ById
    @RequestMapping("/selectSkuById")
    public SkuInfo selectSkuById(@RequestParam String sku_id) {
        return skuService.selectSkuById(sku_id);
    }

    //查询所有商品
    @RequestMapping("/selectSkuAll")
    public List<SkuInfo> selectSkuAll() {
        return skuService.selectSkuAll();
    }

    //按商品名模糊查询，价格(区间)查询,也可查询全部
    @RequestMapping("/selectSkuNamePrice")
    public List<SkuInfo> selectSkuNamePrice(@RequestParam String sku_name,
                                            @RequestParam Double low_price,
                                            @RequestParam Double up_price) {
        return skuService.selectSkuNamePrice(sku_name, low_price, up_price);
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String  upload(@RequestParam MultipartFile file) throws IOException {

        if(file.isEmpty()){
            return "上传失败";
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String newfileName = CommonUtil.getUUID().toUpperCase() + suffixName;

        ClassPathResource resource = new ClassPathResource("static/");
        String path = resource.getFile().getPath();

        File dest = new File(path.split("target")[0] + "src\\main\\resources\\static\\" + newfileName);
        try {

            file.transferTo(dest);
            //拷贝一份在target里
            CommonUtil.fileCopy(path.split("target")[0] + "src\\main\\resources\\static\\" + newfileName
                    ,path+"\\" + newfileName);

            System.out.println("上传成功");
            return newfileName;
        } catch (IOException e) {
            System.out.println("上传失败");
            System.out.println(e.getMessage());
        }


        return "上传失败！";
    }



}
