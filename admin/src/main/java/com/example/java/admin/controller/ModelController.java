package com.example.java.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 刘欢
 * @Date 2019/12/9
 */
@RequestMapping("/model")
@Controller
public class ModelController {

    /**
     * 去到主界面
     * @return
     */
    @RequestMapping("toLayout")
    public String toLayout(){
        return "layout";
    }

    /**
     * 去到登录界面
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin(){
        return "login/login";
    }

}
