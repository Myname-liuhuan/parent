package com.example.web.sso.service.impl;

import com.example.java.web.model.entity.WebUsersEntity;
import com.example.java.web.model.service.BaseService;
import com.example.web.sso.mapper.BaseMapper;
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
