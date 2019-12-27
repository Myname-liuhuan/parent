package com.example.java.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.example.java.admin.entity.AdminUsersEntity;
 
/**
 * 
 * @author huan
 *   AdminUsers控制器
 * @date 2019-12-09 10:04:24
 */
@Controller
@RequestMapping("/adminusers")
public class AdminUsersController extends BaseController<AdminUsersEntity>{
	
	/**
	 * 页面jsp
	 */
	@RequestMapping("/page")
	public String page(){
		return "adminusers";
	}

    /**
     * 页面html
     */
    @RequestMapping("/html")
    public String html(){
        return "redirect:/html/adminusers.html";
    }
}
