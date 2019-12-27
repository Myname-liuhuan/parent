package com.example.web.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 刘欢
 * @Date 2019/12/23
 */
@RequestMapping("model")
@Controller
public class ModelController {

    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }

}
