package com.example.product.service.impl;

import com.example.java.web.model.entity.WebProductDetailEntity;
import com.example.java.web.model.service.WebProductDetailService;
import freemarker.template.Template;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author huan
 *    WebProductDetail业务层实现类
 * @date 2019-12-09 19:07:02
 */
@Service
@Transactional
public class WebProductDetailServiceImpl extends BaseServiceImpl<WebProductDetailEntity> implements WebProductDetailService {

    /**
     * @Scheduled(cron = "0/10 * * * * ? ") 指定该方法在指定的时间后会再次被运行 这里是设定10s 后再次运行
     * @return
     */
    //@Scheduled(cron = "0/10 * * * * ? ")
    @Override
    public String makeTemplate(){
        //1,先查询出web_product_detail表的所有数据
        List<WebProductDetailEntity> detailEntityList = baseMapper.queryAll();
        //每循环一次 生成一个模板
        for (WebProductDetailEntity detailEntity : detailEntityList) {
            try {
                //2,根据id 生成html文件名(全路径) 以及输出流对象
                File file = new File("E:\\ftl_html",detailEntity.getId()+".html");
                FileWriter fw = new FileWriter(file);

                //3,实体类中的数据用map封装
                Map<String,Object> dataMap = new HashMap<>();
                //根据id 去web_product_img表查询出图片链接的集合
                List<String> imgUrlList = webProductImgMapper.queryImageUrlByDetailId(detailEntity.getId().toString());
                dataMap.put("imgUrlList",imgUrlList);
                dataMap.put("title",detailEntity.getTitle());
                dataMap.put("subTitle",detailEntity.getSubtitle());
                dataMap.put("price",detailEntity.getPrice());
                dataMap.put("type",detailEntity.getType());
                dataMap.put("color",detailEntity.getColor());
                dataMap.put("goodId",detailEntity.getId());
                dataMap.put("href",detailEntity.getHref());
                dataMap.put("avatorimg",detailEntity.getAvatorimg());
                dataMap.put("num",detailEntity.getNum());

                //4，得到模板对象
                Template template = freeMarkerConfig.getTemplate("Product.ftl");

                //5,使用freeMarker的方法将数据填充到模板 并且将对应的html 文件 写入到目的地址
                template.process(dataMap,fw);
            } catch (Exception e) {
                e.printStackTrace();
                //产生错误就返回"fail"
                return "fail";
            }
        }
        return "success";
    }

}
