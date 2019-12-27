package com.example.product.service.impl;

import com.example.java.web.model.service.BaseService;
import com.example.product.mapper.BaseMapper;
import com.example.product.mapper.WebProductDetailMapper;
import com.example.product.mapper.WebProductImgMapper;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 刘欢
 * @Date 2019/12/9
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    Configuration freeMarkerConfig;

    @Autowired
    BaseMapper<T> baseMapper;

    @Autowired
    WebProductImgMapper webProductImgMapper;

    @Autowired
    WebProductDetailMapper webProductDetailMapper;

    @Override
    public List<T> findAll() {
        System.out.println("I am serviceImpl findAll");
        return baseMapper.queryAll();
    }
}
