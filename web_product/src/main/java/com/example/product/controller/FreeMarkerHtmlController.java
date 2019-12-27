package com.example.product.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 刘欢
 * @Date 2019/12/18
 *
 * (测试)
 * 该控制类的作用就是 在被调用的时候就生成指定的html 模板
 *
 */
@Controller
@RequestMapping("freeMarkerHtml")
public class FreeMarkerHtmlController {

    @Autowired
    private Configuration freeMarkerConfig;


    /**
     * 该方法做的就是从数据库查询数据 然后将其生成为html 文件
     * @param fileName
     * @return
     */
    @RequestMapping("makeHtml/{fileName}")
    @ResponseBody
    public String makeHtml(@PathVariable("fileName") String fileName){

        //1，先得到指向指定文件的file 对象
        File file = new File("E:\\ftl_html",fileName+".html");

        try {
            //2，得到输出流对象
            FileWriter fw = new FileWriter(file);

            //3，获取模板对象 参数为静态页面的模板在项目中的路径
            Template template = freeMarkerConfig.getTemplate("Product.ftl");

            //4，将数据封装为map 传到模板
            Map<String,Object> dataMap = new HashMap<>();
            List<String> imgUrlList = Arrays.asList("http://pybdab5db.bkt.clouddn.com/g101201.jpg","http://pybdab5db.bkt.clouddn.com/g101301.jpg","http://pybdab5db.bkt.clouddn.com/g101401.jpg");
            dataMap.put("imgUrlList",imgUrlList);
            dataMap.put("title","苹果12");
            dataMap.put("subTitle","即将发布的苹果新一代手机");
            dataMap.put("price",8899.99);
            dataMap.put("type","高配");
            dataMap.put("color","白色");
            dataMap.put("goodId","123456");
            dataMap.put("href","www.baidu.com");
            dataMap.put("avatorimg","http://q1cydzrcd.bkt.clouddn.com/c36b3f60efe84ac493aba62bf0c4ad8e");
            dataMap.put("num",390);

            //5,使用freeMarker的方法将数据填充到模板 并且将对应的html 文件 写入到目的地址
            template.process(dataMap,fw);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return "success";
    }

}
