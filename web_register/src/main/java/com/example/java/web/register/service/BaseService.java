package com.example.java.web.register.service;

import java.util.List;

/**
 * @author 刘欢
 * @Date 2019/12/9
 */
public interface BaseService<T> {

    //访问数据成功的常量
    Integer SUCCESSCODE = 0;
    //访问数据失败的常量
    Integer FAILCODE = 200;
    //访问数据失败的数据条数
    Integer FAILCOUNT = 0;
    //访问数据失败时的提示
    String FAILMSG = "数据访问失败！！！";
    //员工离职提醒的常量
    String JURISDICTION = "jurisdiction";
    //操作成功的常量
    String SUCCESS = "success";
    //操作失败的常量
    String FAIL = "fail";
    //操作异常的常量
    String ERROR = "error";

    /**
     * 查询某表的所有信息
     * @return
     */
    List<T> findAll();


    /**
     * 用来检测该java bean 中的参数是不是全部存在(只要有一个不匹配就为fail)
     * @param t
     * @return
     */
    String checkExisted(T t);

    /**
     * 向指定表中插入一行数据
     * @param t
     * @return
     */
    String insert(T t);

}
