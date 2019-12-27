package com.example.java.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 刘欢
 * @Date 2019/12/10
 */
@Controller
@RequestMapping("model")
public class ModelController {

    @RequestMapping("test")
    public String toTest(){
        return "test";
    }

}
