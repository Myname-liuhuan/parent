package com.example.java.web.register.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 刘欢
 * @Date 2019/12/23
 */
@RequestMapping("model")
@Controller
public class ModelController {
    @RequestMapping("toRegister")
    public String toRegister(){
        return "register";
    }
}
