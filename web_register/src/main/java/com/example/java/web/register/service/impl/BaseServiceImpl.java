package com.example.java.web.register.service.impl;

import com.example.java.web.register.mapper.BaseMapper;
import com.example.java.web.register.service.BaseService;
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

    @Override
    public String checkExisted(T t) {
        Integer count = baseMapper.queryCountByBean(t);
        if (count > 0){
            return SUCCESS;
        }else{
            return FAIL;
        }
    }

    @Override
    public String insert(T t) {
        Integer i = baseMapper.insert(t);
        if (i == 1){
            return SUCCESS;
        }
        return FAIL;
    }
}
