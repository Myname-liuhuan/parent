package com.example.web.seckill.controller;

import com.example.java.web.model.service.WebSeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author 刘欢
 * @Date 2019/12/29
 */
@Controller
@RequestMapping("webseckill")
public class WebSeckillController {

    @Autowired
    WebSeckillService webSeckillService;

    /**
     * 手动启动定时器的方法 以免在定时器当前时间无法启动
     * @return
     */
    @RequestMapping("loadProductDetailsToRedis")
    @ResponseBody
    public String loadProductDetailsToRedis(){
        try {
            webSeckillService.findProductDetailsToRedis();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }


    /**
     * 在前台秒杀页面刚加载的时候获取待秒杀的商品信息
     * @return
     */
    @RequestMapping("loadProductDetail")
    @ResponseBody
    public List<Map<String,Object>> loadProductDetail(){
        return webSeckillService.findProductDetail();
    }


    /**
     * 前台做秒杀操作 去redis 中判断是否存在该商品 不存在 说已经被秒杀 存在就将其存放到另一个redis key 中，存的时候判断另一个里面是否已经存在该key
     * 已经存在的话就提示已经秒杀过该商品了。
     * @param uid
     * @param productId
     * @return
     */
    @RequestMapping("doSecKill")
    @ResponseBody
    public String doSecKill(String uid, String productId){
        try {
            return webSeckillService.doSecKill(uid, productId);
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }


}
