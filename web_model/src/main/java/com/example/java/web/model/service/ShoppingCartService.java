package com.example.java.web.model.service;

import com.example.java.web.model.entity.Good;
import com.example.java.web.model.entity.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 刘欢
 * @Date 2019/12/23
 */
public interface ShoppingCartService {

    /**
     * 添加购物车的业务层类
     * @param id
     * @param goodNum
     * @param token
     * @param request
     * @param response
     * @return
     */
    String addGoods(Integer id, Integer goodNum, String token, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 将cookie中的数据转移到redis中 并cookie 删除
     * @param request
     * @param response
     * @param token
     * @return
     */
    String cookieToRedis(HttpServletRequest request, HttpServletResponse response, String token) throws Exception;

    /**
     * 根据token 得到购物车中的数据，token为null 去cookie中找 不为null 去redis 中找
     * @param token
     * @param request
     * @return
     */
    List<Good> showGoods(String token, HttpServletRequest request);



}
