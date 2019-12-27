package com.example.product.mapper;

import java.util.List;

/**
 * @author 刘欢
 * @Date 2019/12/9
 */
public interface BaseMapper<T> {

    /**
     * 查询该表中所有数据
     * @return
     */
    List<T> queryAll();

}
