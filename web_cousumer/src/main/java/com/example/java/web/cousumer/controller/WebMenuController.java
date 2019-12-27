package com.example.java.web.cousumer.controller;

import com.example.java.web.model.entity.WebMenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 
 * @author huan
 * WebMenu控制器
 * @date 2019-12-09 19:07:03
 */
@Controller
@RequestMapping("/webmenu")
public class WebMenuController{


    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("loadAll")
    @ResponseBody
    public List<WebMenuEntity> loadAll(){
        System.out.println("cousumer: loadAll menu");

        return restTemplate.getForObject("http://web-provider/webmenu/loadAll",List.class);
    }

}
