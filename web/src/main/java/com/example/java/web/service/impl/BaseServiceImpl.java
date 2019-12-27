package com.example.java.web.service.impl;

import com.example.java.web.mapper.BaseMapper;
import com.example.java.web.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 刘欢
 * @Date 2019/12/9
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    BaseMapper<T> baseMapper;

    @Override
    public List<T> findAll() {
        return baseMapper.queryAll();
    }
}
