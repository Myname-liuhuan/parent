package com.example.java.web.provider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String loadName(String name){
        System.out.println("provider_1 : " + name);
        return "Henry";
    }

}
