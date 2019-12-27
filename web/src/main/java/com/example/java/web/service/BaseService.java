package com.example.java.web.service;

import java.util.List;

/**
 * @author 刘欢
 * @Date 2019/12/9
 */
public interface BaseService<T> {

    /**
     * 查询某表的所有信息
     * @return
     */
    List<T> findAll();

}
