package com.example.product.controller;

import com.example.java.web.model.service.BaseService;
import com.example.java.web.model.service.WebProductDetailService;
import com.example.java.web.model.service.WebProductImgService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 刘欢
 * @Date 2019/12/19
 */
public class BaseController<T> {

    @Autowired
    BaseService<T> baseService;

    @Autowired
    WebProductImgService webProductImgService;

    @Autowired
    WebProductDetailService webProductDetailService;
}
