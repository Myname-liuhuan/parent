package com.example.product.controller;

import com.example.java.web.model.entity.WebProductDetailEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author 刘欢
 * @Date 2019/12/19
 *
 * 访问方法生成模板 数据从数据库 获取
 *
 */
@Controller
@RequestMapping("webProductTemplate")
public class WebProductDetailTemplateController extends BaseController<WebProductDetailEntity>{

    @RequestMapping("makeTemplate")
    @ResponseBody
    public String makeTemplate(){
        return webProductDetailService.makeTemplate();
    }
}
