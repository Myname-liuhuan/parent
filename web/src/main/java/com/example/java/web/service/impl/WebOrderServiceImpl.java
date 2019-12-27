package com.example.java.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.java.web.entity.WebOrderEntity;
import com.example.java.web.service.WebOrderService;

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
