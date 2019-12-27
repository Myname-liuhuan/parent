package com.example.web.sso.mapper;

import com.example.java.web.model.entity.WebUsersEntity;

import java.util.List;

/**
 * @author 刘欢
 * @Date 2019/12/20
 */
public interface BaseMapper<T> {

    /**
     * 查询该表中所有数据
     * @return
     */
    List<T> queryAll();

    /**
     * 根据条件 查询出bean集合
     * @param user
     * @return
     */
    List<T> queryByParams(WebUsersEntity user);
}
