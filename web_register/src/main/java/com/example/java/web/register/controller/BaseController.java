package com.example.java.web.register.controller;

import com.example.java.web.register.service.BaseService;
import com.example.java.web.register.service.WebUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 刘欢
 * @Date 2019/12/9
 */
public class BaseController<T> {


    //访问数据成功的常量
    protected static final Integer SUCCESSCODE = 0;
    //访问数据失败的常量
    protected static final Integer FAILCODE = 200;
    //访问数据失败的数据条数
    protected static final Integer FAILCOUNT = 0;
    //访问数据失败时的提示
    protected static final String FAILMSG = "数据访问失败！！！";
    //员工离职提醒的常量
    protected static final String JURISDICTION = "jurisdiction";
    //操作成功的常量
    protected static final String SUCCESS = "success";
    //操作失败的常量
    protected static final String FAIL = "fail";
    //操作异常的常量
    protected static final String ERROR = "error";

    @Autowired
    BaseService<T> baseService;

    @Autowired
    WebUsersService webUsersService;


    @RequestMapping("loadAll")
    @ResponseBody
    public List<T> loadAll(){
        return baseService.findAll();
    }


    /**
     * 用来检测该java bean 中的参数是不是全部存在(只要有一个不匹配就为fail)
     * @param t
     * @return
     */
    @RequestMapping("/checkExisted")
    @ResponseBody
    public String checkExisted(T t){
        return baseService.checkExisted(t);
    }


    /**
     * 向表中添加一行数据
     * @param t
     * @return
     */
    @RequestMapping("insert")
    @ResponseBody
    public String insert(T t){
        return baseService.insert(t);
    }
}
