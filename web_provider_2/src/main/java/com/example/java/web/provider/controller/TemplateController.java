package com.example.java.web.provider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 刘欢
 * @Date 2019/12/15
 */
@Controller
@RequestMapping("template")
public class TemplateController {

    @RequestMapping("loadName")
    @ResponseBody
    public String loadName(@RequestParam("name") String name){
        System.out.println("provider_2: " + name);
        return "Henry";
    }

    @RequestMapping("loadName2")
    @ResponseBody
    public String loadName2(@RequestBody String name2){
        System.out.println("provider_2: " + name2);
        return "Huan";
    }

}
