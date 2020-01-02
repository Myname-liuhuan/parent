package com.example.web.shoppingcart.controller;

import com.example.java.web.model.entity.Good;
import com.example.java.web.model.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
     * @param token
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("addGoods")
    @ResponseBody
    public String addGoods(Integer id, Integer goodNum, String token, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return shoppingCartService.addGoods(id,goodNum,token,request,response);
    }



    @RequestMapping("cookieToRedis")
    @ResponseBody
    public String cookieToRedis(HttpServletRequest request, HttpServletResponse response, String token) throws Exception {
        return shoppingCartService.cookieToRedis(request, response, token);
    }

    /**
     * 在页面加载的时候显示购物车里面的数据
     * @param token
     * @return
     */
    @RequestMapping("showGoods")
    @ResponseBody
    public List<Good> showGoods(String token, HttpServletRequest request){
        return shoppingCartService.showGoods(token, request);
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
