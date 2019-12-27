package com.example.java.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.example.java.admin.entity.AdminUserAuthorityEntity;
 
/**
 * 
 * @author huan
 *   AdminUserAuthority控制器
 * @date 2019-12-09 10:04:24
 */
@Controller
@RequestMapping("/adminuserauthority")
public class AdminUserAuthorityController extends BaseController<AdminUserAuthorityEntity>{
	
	/**
	 * 页面jsp
	 */
	@RequestMapping("/page")
	public String page(){
		return "adminuserauthority";
	}

    /**
     * 页面html
     */
    @RequestMapping("/html")
    public String html(){
        return "redirect:/html/adminuserauthority.html";
    }
}
