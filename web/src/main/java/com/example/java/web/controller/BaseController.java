package com.example.java.web.controller;

import com.example.java.web.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 刘欢
 * @Date 2019/12/9
 */
public class BaseController<T> {

    @Autowired
    BaseService<T> baseService;


    @RequestMapping("loadAll")
    @ResponseBody
    public List<T> loadAll(){
        return baseService.findAll();
    }

}
