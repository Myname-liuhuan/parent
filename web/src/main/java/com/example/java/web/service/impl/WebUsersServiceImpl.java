package com.example.java.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.java.web.entity.WebUsersEntity;
import com.example.java.web.service.WebUsersService;

/**
 * 
 * @author huan
 *    WebUsers业务层实现类
 * @date 2019-12-09 19:07:02
 */
@Service
@Transactional
public class WebUsersServiceImpl extends BaseServiceImpl<WebUsersEntity> implements WebUsersService {
	
}
