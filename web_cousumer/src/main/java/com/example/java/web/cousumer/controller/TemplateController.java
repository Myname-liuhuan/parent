package com.example.java.web.cousumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * @author 刘欢
 * @Date 2019/12/15
 *
 * 在cousumer 的控制层做的是对请求的接受 然后将其转发给不同的provider
 * 在正式使用中 路径应为实际访问的功能名 eg: /webMenu 下面的方法再接 /insert  , /query等
 *
 * 本类中用于测试所以取名不规范
 *
 * 链接两个模块 不如说是链接两个模块中的方法
 * 每一个cousumer模块中的路径(方法)在provider中都有唯一的访问路径与之对应
 *
 */
@Controller
@RequestMapping("template")
public class TemplateController {

    /**
     * 注入 RestTemplate 实例对象
     */
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("loadName")
    @ResponseBody
    public String loadName(String name){
        System.out.println("cousumer:" + name);
        //其中http参数 为在注册中心注册的访问路径
        return restTemplate.getForObject("http://web-provider/template/loadName?name="+name,String.class);
    }


    @RequestMapping("loadName2")
    @ResponseBody
    public String loadName2(String name2){
        System.out.println("cousumer:" + name2);
        //其中http参数 为在注册中心注册的访问路径
        return restTemplate.postForObject("http://web-provider/template/loadName2",name2,String.class);
    }
}
