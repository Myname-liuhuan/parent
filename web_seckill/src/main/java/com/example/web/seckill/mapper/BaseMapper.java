package com.example.web.seckill.mapper;

import java.util.List;
import java.util.Map;

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


    /**
     * 通过参数查询出符合条件的数据集合
     * @param t
     * @return
     */
    List<T> queryByParams(T t);

    /**
     * 根据条件更新数据库
     * @param t
     * @return
     */
    Integer updateByParams(T t);

    /**
     * 查询出当前在秒杀的商品 并且关联得到每个商品的具体信息
     * @return
     */
    List<Map<String,Object>> queryProductDetails();

}
