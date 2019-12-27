package com.example.java.web.register.mapper;

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


    /**
     * 根据传来的Javabean 去数据库中查询字段是否存在 当JavaBean中只含有一个字段时就是查询该字段的count
     * 多个字段的时候 就是查询满足这些多个字段值条件的行数
     * JavaBean为null的时候就查询全部 防止前台故意传null 越过字段检查.为null那么就返回的是有值 有值就表示该对本次请求的答复是不可用
     * @param t
     * @return
     */
    Integer queryCountByBean(T t);

    /**
     * 向表中插入一行数据
     * @return
     */
    Integer insert(T t);

}
