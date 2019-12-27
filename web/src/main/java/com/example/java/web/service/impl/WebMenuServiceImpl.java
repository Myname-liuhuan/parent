package com.example.java.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.java.web.entity.WebMenuEntity;
import com.example.java.web.service.WebMenuService;

import java.util.List;

/**
 * 
 * @author huan
 *    WebMenu业务层实现类
 * @date 2019-12-09 19:07:03
 */
@Service
@Transactional
public class WebMenuServiceImpl extends BaseServiceImpl<WebMenuEntity> implements WebMenuService {

    /**
     * 先依赖注入redis对象
     */
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * redis做缓存
     * 通过重写方法 实现第一次加载数据库 然后后面都从redis中获取数据
     * @return
     */
    @Override
    public List<WebMenuEntity> findAll() {
        //在方法中做对redis查询结果的判断 如果为空 那么就从mysql 中去查询结果，存到redis中，并且将查询结果赋值给返回值
        //如果不为空 那就直接返回redis中缓存的结果
        /**redisTemplate.opsForList() 得到操作list 的对象 然后 range("key",0,-1) 得到该list中的所有值
         * 当该menuList里面没有值的时候 也不会返回null 而是返回一个没有元素的集合，即可以用size是否为0来判断 从哪取数据
         * */
        List<WebMenuEntity> menuList = null;
        ListOperations lops = redisTemplate.opsForList();
        menuList = lops.range("menuList",0,-1);
        if (menuList.size() == 0){
            menuList = baseMapper.queryAll();
            //将结果存到redis
            lops.leftPushAll("menuList", menuList);
        }

        return menuList;
    }
}
