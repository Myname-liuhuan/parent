package com.example.web.sso.service.impl;

import com.example.java.web.model.entity.WebUsersEntity;
import com.example.java.web.model.service.WebUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author huan
 *    WebUsers业务层实现类
 * @date 2019-12-09 19:07:02
 */
@Service
@Transactional
public class WebUsersServiceImpl extends BaseServiceImpl<WebUsersEntity> implements WebUsersService {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 如果是正确的账户登录 那么就将用户信息存到redis中去返回 其key,否则就返回 ""
     * @param usersEntity
     * @return
     */
    @Override
    public String findObjectByParams(WebUsersEntity usersEntity) {
        List<WebUsersEntity> webUsersEntities = baseMapper.queryByParams(usersEntity);
        if (webUsersEntities != null && webUsersEntities.size() == 1){
            //生成UUID 作为唯一标识的key
            String key = UUID.randomUUID().toString();
            //将用户数据存放到redis
            ValueOperations vps = redisTemplate.opsForValue();
            //存放数据的同时 为其设置过期时间 20分钟
            vps.set(key,webUsersEntities.get(0),20, TimeUnit.MINUTES);
            return key;
        }
        return "";
    }

    @Override
    public WebUsersEntity getUserEntity(String token) {
        WebUsersEntity userEntity = null;
        //判断 该key值是否存在
        if (token != null && redisTemplate.hasKey(token)){
            ValueOperations vps = redisTemplate.opsForValue();
            userEntity = (WebUsersEntity)vps.get(token);
        }
        return userEntity;
    }

    @Override
    public String deleteFormRedis(String token) {
        //判断 该key值是否存在
        if (token != null){
            redisTemplate.delete(token);
            return "success";
        }
        return "fail";
    }


}
