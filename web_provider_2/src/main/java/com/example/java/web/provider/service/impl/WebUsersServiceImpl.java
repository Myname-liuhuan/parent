package com.example.java.web.provider.service.impl;

import com.example.java.web.model.entity.WebUsersEntity;
import com.example.java.web.model.service.WebUsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author huan
 *    WebUsers业务层实现类
 * @date 2019-12-09 19:07:02
 */
@Service
@Transactional
public class WebUsersServiceImpl extends BaseServiceImpl<WebUsersEntity> implements WebUsersService {

    @Override
    public String findObjectByParams(WebUsersEntity usersEntity) {
        return null;
    }

    @Override
    public WebUsersEntity getUserEntity(String token) {
        return null;
    }

    @Override
    public String deleteFormRedis(String token) {
        return null;
    }
}
