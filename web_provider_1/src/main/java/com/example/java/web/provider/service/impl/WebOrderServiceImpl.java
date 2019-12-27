package com.example.java.web.provider.service.impl;

import com.example.java.web.model.entity.WebOrderEntity;
import com.example.java.web.model.service.WebOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author huan
 *    WebOrder业务层实现类
 * @date 2019-12-09 19:07:03
 */
@Service
@Transactional
public class WebOrderServiceImpl extends BaseServiceImpl<WebOrderEntity> implements WebOrderService {
	
}
