package com.example.product.mapper;

import com.example.java.web.model.entity.WebProductImgEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @author huan
 *    WebProductImgMapper层
 * @date 2019-12-09 19:07:02
 */
@Repository
public interface WebProductImgMapper extends BaseMapper<WebProductImgEntity> {

    /**
     * 根据web_product_detail 表的id 查询出在web_product_img 表中所对应的行中imgUrl的集合(结果为多行 但每行只取一个字段imgUrl)
     * @param id
     * @return
     */
    List<String> queryImageUrlByDetailId(String id);
}
