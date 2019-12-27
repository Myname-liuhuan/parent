package com.example.product.controller;

import com.example.product.service.impl.DiscussServiceImpl;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 刘欢
 * @Date 2019/12/19
 */
@Controller
@RequestMapping("discuss")
public class DiscussController {

    @Autowired
    DiscussServiceImpl discussService;

    /**
     * @CrossOrigin(origins = "*", maxAge = 3600)
     * 该注解添加在控制层的方法上面 在这里添加参数 表示 该方法中返回的数据支持跨域访问
     *
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("loadDiscuss")
    @ResponseBody
    public List<Document> loadDiscuss(){
        return discussService.loadDiscuss();
    }

}
