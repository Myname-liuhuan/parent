package com.example.web.seckill.service.impl;

import com.example.java.web.model.entity.WebSeckillEntity;
import com.example.java.web.model.service.WebSeckillService;
import com.example.web.seckill.mapper.WebSeckillMapper;
import com.example.web.seckill.utils.NearDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @author huan
 *    WebSeckill业务层实现类
 * @date 2019-12-09 19:07:02
 */
@Service
@Transactional
public class WebSeckillServiceImpl implements WebSeckillService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    WebSeckillMapper webSeckillMapper;

    @Override
    public List<WebSeckillEntity> findAll() {
        return null;
    }

    /**
     * 设置定时任务 在每天的几点会被执行，到时候会从数据库中取数据填充到redis中 这个要比前台页面的开始时间要早一点
     * 提取标准为 开始时间
     * 定时器的执行规则是 如果该服务启动时已经过了定时器开始的时间 那么定时器会在下一次进入该时间区间的时候开启
     * 如果未到时间 那么到时间就会开始执行定时器代码
     * cron = "0 0 17-23/2 * * ? " （表示每天的17点到23点 每隔2小时执行一次）
     */
    @Scheduled(cron = "0/2 0 10-22/2 * * ? ")
    @Override
    public void findProductDetailsToRedis() throws Exception{

        //获取当前时间最近的整分钟
        Date nearDate = NearDateUtil.getNearDate(new Date());
        nearDate = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse("2020-01-02 16:00:00");
        //根据条件将mysql 查出来
        WebSeckillEntity webSeckillEntity = new WebSeckillEntity();
        webSeckillEntity.setStarttime(nearDate);
        List<WebSeckillEntity> webSeckillList = webSeckillMapper.queryByParams(webSeckillEntity);
        //得到的数据合法就遍历将其存到redis (list)中,保存商品id
        if (webSeckillList != null && webSeckillList.size() >0){
            SetOperations sops = redisTemplate.opsForSet();
            for (WebSeckillEntity seckillEntity : webSeckillList) {
                //将本时段要秒杀的商品存到set中 将商品id 和 数量拼接为字符串。
                sops.add("seckillSet",seckillEntity.getProductid() + ":" + seckillEntity.getNum());
                //同时将这些数据的status 属性改为1
                WebSeckillEntity seckill = new WebSeckillEntity();
                seckill.setStatus("1");
                seckill.setId(seckillEntity.getId());
                webSeckillMapper.updateByParams(seckill);
            }
        }
    }

    @Override
    public List<Map<String, Object>> findProductDetail() {
        List<Map<String, Object>> mapList = webSeckillMapper.queryProductDetails();
        if (mapList != null && mapList.size() >0){
            return mapList;
        }
        return null;
    }

    @Override
    public String doSecKill(String uid, String productId) {
        if (redisTemplate.hasKey("seckillSet") && uid != null && productId != null){
            System.out.println("uid : " + uid + " productId : "+productId);
            SetOperations sops = redisTemplate.opsForSet();
            ListOperations ops = redisTemplate.opsForList();
            Set<String> seckillSet = sops.members("seckillSet");
            System.out.println(Arrays.toString(seckillSet.toArray()));
            boolean isSuccess = false;
            if (seckillSet != null && seckillSet.size() > 0){
                //遍历判断是否有该商品
                for (String s : seckillSet) {
                    //判断该商品是否存在以及是否还有库存
                    String productIdStr = s.substring(0,s.indexOf(":"));
                    Integer num = Integer.valueOf(s.substring(s.indexOf(":")+1));
                    System.out.println("redis = "+productIdStr+"+"+num);
                    if (productIdStr.equals(productId)){
                        if (num > 0){
                            //再来判断 用户是否是第一次秒杀该商品
                            if (redisTemplate.hasKey("hasSecKill")){
                                List<String> hasSecKill = ops.range("hasSecKill", 0, -1);
                                if (hasSecKill != null){
                                    if (!hasSecKill.contains(uid + ":" + productId)){
                                        //将商品添加到新的redis key 中
                                        ops.leftPush("hasSecKill", uid + ":" + productId);
                                        //同时将该商品的数量减一
                                        sops.remove("hasSecKill",s);
                                        num = num - 1;
                                        s = productIdStr + ":" + num;
                                        sops.add("hasSecKill",s);
                                        return "success";
                                    }
                                    return "你已经秒杀过该商品";
                                }
                                return "";
                            }
                            //将商品添加到新的redis key 中
                            ops.leftPush("hasSecKill", uid + ":" + productId);
                            //同时将该商品的数量减一
                            sops.remove("hasSecKill",s);
                            num = num - 1;
                            s = productIdStr + ":" + num;
                            sops.add("hasSecKill",s);
                            return "success";
                        }
                    }
                }
            }
            return "秒杀错误";
        }
        return "还未到秒杀的时间";
    }
}
