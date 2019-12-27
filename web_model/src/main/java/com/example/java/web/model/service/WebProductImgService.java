package com.example.java.web.model.service;


import com.example.java.web.model.entity.WebProductImgEntity;

import java.util.List;

/**
 * 
 * @author huan
 *    WebProductImg业务层接口
 * @date 2019-12-09 19:07:02
 */
public interface WebProductImgService extends BaseService<WebProductImgEntity>{

    /**
     * 根据web_product_detail 表的id 查询出在web_product_img 表中所对应的行中imgUrl的集合(结果为多行 但每行只取一个字段imgUrl)
     * @param id
     * @return
     */
    List<String> findImageUrlByDetailId(String id);
}
