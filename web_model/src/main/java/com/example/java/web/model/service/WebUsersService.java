package com.example.java.web.model.service;


import com.example.java.web.model.entity.WebUsersEntity;

/**
 * 
 * @author huan
 *    WebUsers业务层接口
 * @date 2019-12-09 19:07:02
 */
public interface WebUsersService extends BaseService<WebUsersEntity>{
    /**
     * 根据条件查询出bean集合
     * @param usersEntity
     * @return
     */
    String findObjectByParams(WebUsersEntity usersEntity);

    /**
     * 根据token 到redis 中获取用户信息
     * @param token
     * @return
     */
    WebUsersEntity getUserEntity(String token);

    /**
     * 根据token 删除redis 中的该条数据
     * @param token
     * @return
     */
    String deleteFormRedis(String token);
}
