package com.example.java.web.register.service.impl;

import com.example.java.web.register.entity.WebUsersEntity;
import com.example.java.web.register.service.WebUsersService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author huan
 *    WebUsers业务层实现类
 * @date 2019-12-09 19:07:02
 */
@Service
@Transactional(readOnly = false)
@Primary
public class WebUsersServiceImpl extends BaseServiceImpl<WebUsersEntity> implements WebUsersService {

}
