package com.example.java.web.model.service;


import com.example.java.web.model.entity.WebProductDetailEntity;

/**
 * 
 * @author huan
 *    WebProductDetail业务层接口
 * @date 2019-12-09 19:07:02
 */
public interface WebProductDetailService extends BaseService<WebProductDetailEntity>{

    /**
     * 生成模板
     * @return
     */
    String makeTemplate();
}
