package com.example.java.web.cousumer.controller;

import com.example.java.web.model.entity.WebBannerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author 刘欢
 * @Date 2019/12/16
 */
@RequestMapping("webbanner")
@Controller
public class WebBannerController{

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("loadAll")
    @ResponseBody
    public List<WebBannerEntity> loadAll(){
        System.out.println("cousumer: loadAll banner");
        return restTemplate.getForObject("http://web-provider/webbanner/loadAll",List.class);
    }
}
