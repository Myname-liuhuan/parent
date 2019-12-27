package com.example.web.sso.controller;


import com.example.java.web.model.service.WebUsersService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 刘欢
 * @Date 2019/12/20
 */
public class BaseController<T> {
    @Autowired
    WebUsersService webUsersService;
}
