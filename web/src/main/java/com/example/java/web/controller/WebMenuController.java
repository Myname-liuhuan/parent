package com.example.java.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.example.java.web.entity.WebMenuEntity;
 
/**
 * 
 * @author huan
 *   WebMenu控制器
 * @date 2019-12-09 19:07:03
 */
@Controller
@RequestMapping("/webmenu")
public class WebMenuController extends BaseController<WebMenuEntity>{
}
