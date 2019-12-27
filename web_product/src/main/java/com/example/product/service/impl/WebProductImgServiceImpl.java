package com.example.product.service.impl;

import com.example.java.web.model.entity.WebProductImgEntity;
import com.example.java.web.model.service.WebProductImgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 * @author huan
 *    WebProductImg业务层实现类
 * @date 2019-12-09 19:07:02
 */
@Service
@Transactional
public class WebProductImgServiceImpl extends BaseServiceImpl<WebProductImgEntity> implements WebProductImgService {

    @Override
    public List<String> findImageUrlByDetailId(String id) {
        return webProductImgMapper.queryImageUrlByDetailId(id);
    }
}
