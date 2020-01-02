package com.example.java.web.model.service;

import com.example.java.web.model.entity.WebSeckillEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author huan
 *    WebSeckill业务层接口
 * @date 2019-12-09 19:07:02
 */
public interface WebSeckillService extends BaseService<WebSeckillEntity>{
    /**
     * 设置定时任务 在每天的几点会被执行，到时候会从数据库中取数据填充套redis中
     * 因为两表联查 接受的实体类没有做映射 所以使用map接收
     */
    void findProductDetailsToRedis() throws Exception;


    /**
     * 查询出待秒杀的商品，以及当前正在秒杀的商品
     * @return
     */
    List<Map<String,Object>> findProductDetail();

    /**
     * 前台做秒杀操作 去redis 中判断是否存在该商品 不存在 说已经被秒杀 存在就将其存放到另一个redis key 中，存的时候判断另一个里面是否已经存在该key
     * 已经存在的话就提示已经秒杀过该商品了。
     * @param uid
     * @param productId
     * @return
     */
    String doSecKill(String uid, String productId);
	
}
