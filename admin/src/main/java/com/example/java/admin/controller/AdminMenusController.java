package com.example.java.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.example.java.admin.entity.AdminMenusEntity;
 
/**
 * 
 * @author huan
 *   AdminMenus控制器
 * @date 2019-12-09 10:04:24
 */
@Controller
@RequestMapping("/adminmenus")
public class AdminMenusController extends BaseController<AdminMenusEntity>{
	
	/**
	 * 页面jsp
	 */
	@RequestMapping("/page")
	public String page(){
		return "adminmenus";
	}

    /**
     * 页面html
     */
    @RequestMapping("/html")
    public String html(){
        return "redirect:/html/adminmenus.html";
    }
}
