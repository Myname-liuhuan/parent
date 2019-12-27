package com.example.web.shoppingcart.controller;

import com.example.java.web.model.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 刘欢
 * @Date 2019/12/23
 */
@RequestMapping("shoppingcart")
@Controller
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    /**
     * 所有的添加购物车操作都要经过该方法 在该方法中将该请求判断为是哪一类 然后调用不同的方法实现。
     * @param id
     * @param goodNum
     * @param logined
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("addGoods")
    @ResponseBody
    public String addGoods(Integer id, Integer goodNum, Boolean logined, HttpServletRequest request, HttpServletResponse response){
        return shoppingCartService.addGoods(id,goodNum,logined,request,response);
    }



    @RequestMapping()
    @ResponseBody
    public String cookieToRedis(HttpServletRequest request, HttpServletResponse response, String token){
        return shoppingCartService.cookieToRedis(request, response, token);
    }

    @RequestMapping("showCookie")
    @ResponseBody
    public String showCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0){
            for (Cookie cookie : cookies) {
                if ("shoppingCart".equals(cookie.getName())){
                    return  cookie.getValue();
                }
            }
        }
        return "购物车为空";
    }

}
