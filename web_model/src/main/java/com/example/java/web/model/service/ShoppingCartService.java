package com.example.java.web.model.service;

import com.example.java.web.model.entity.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 刘欢
 * @Date 2019/12/23
 */
public interface ShoppingCartService {

    /**
     * 添加购物车的业务层类
     * @param id
     * @param goodNum
     * @param logined
     * @param request
     * @param response
     * @return
     */
    String addGoods(Integer id, Integer goodNum, Boolean logined, HttpServletRequest request, HttpServletResponse response);

    /**
     * 将cookie中的数据转移到redis中 并cookie 删除
     * @param request
     * @param response
     * @param token
     * @return
     */
    String cookieToRedis(HttpServletRequest request, HttpServletResponse response, String token);

}
