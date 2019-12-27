package com.example.java.web.provider.controller;

import com.example.java.web.model.entity.WebUsersEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author huan
 *   WebUsers控制器
 * @date 2019-12-09 19:07:02
 */
@Controller
@RequestMapping("/webusers")
public class WebUsersController extends BaseController<WebUsersEntity>{}
