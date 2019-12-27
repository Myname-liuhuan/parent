package com.example.java.web.provider.controller;

import com.example.java.web.model.entity.WebUsersEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 刘欢
 * @Date 2019/12/10
 *
 * 测试thymeleaf配置
 *
 */
@Controller
@RequestMapping("templates")
public class ThymeleafController {

    /**
     * @param model
     * @return
     *
     * 测试各种类型的thymeleaf解析方式
     */
    @RequestMapping("/toShow")
    public String toShow(Model model, HttpServletRequest request){
        //文本
        model.addAttribute("uid","1000001");
        model.addAttribute("name","Henry");

        //对象
        WebUsersEntity user = new WebUsersEntity();
        user.setId(1L);
        user.setUsername("Henry");
        user.setPhone("13255554444");
        user.setPwd("123456");
        model.addAttribute("user",user);

        //set 和 list
        Set set = new HashSet();
        set.add("adele");
        set.add("justin Bieber");
        set.add("Rihanna");
        model.addAttribute("set",set);
        List list = new ArrayList();
        list.add("阿黛尔");
        list.add("贾斯汀 比伯");
        list.add("蕾哈娜");
        model.addAttribute("list",list);

        //域对象
        request.setAttribute("requestAttr","request域中的值");
        request.getSession().setAttribute("sessionAttr","session域中的值");
        request.getServletContext().setAttribute("application", "context域中的值");

        return "show";
    }
}
