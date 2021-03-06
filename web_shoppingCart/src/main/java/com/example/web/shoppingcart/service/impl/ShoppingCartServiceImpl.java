package com.example.web.shoppingcart.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.java.web.model.entity.Good;
import com.example.java.web.model.entity.ShoppingCart;
import com.example.java.web.model.service.ShoppingCartService;
import com.example.web.shoppingcart.utils.Base64Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘欢
 * @Date 2019/12/23
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * cookie中存储的数据以 key-value {"shoppingCart" : shoppingCartJson} 方式存储
     *
     * 在该类中判断是否登录 然后再将数据以适当的方式处理
     * 该方法中做参数是否合法的判断操作
     * @param id
     * @param goodNum
     * @param token
     * @param request
     * @param response
     * @return
     */
    @Override
    public String addGoods(Integer id, Integer goodNum, String token, HttpServletRequest request, HttpServletResponse response) throws Exception{
        if (id != null && goodNum != null && goodNum > 0){
            try {
                //先判断是否是登录状态
                if (token != null){
                    addGoodsToRedis(id, goodNum,token);
                }else{
                    addGoodsToCookie(id, goodNum,request,response);
                }
                return "success";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("id : " + id + " goodNum" + goodNum + " token" + token);
            return "传入参数不合法";
        }
        return "fail";
    }

    /**
     * 将cookie中的数据存到redis中并且将cookie删除,
     * 要做cookie和redis中数据的合并
     * @param request
     * @param response
     * @param token
     * @return
     */
    @Override
    public String cookieToRedis(HttpServletRequest request, HttpServletResponse response, String token) throws Exception{
        if (token != null){
            Cookie[] cookies = request.getCookies();
            if (cookies != null){
                for (Cookie cookie : cookies) {
                    if ("shoppingCart".equals(cookie.getName())){
                        //最后都要删除该cookie,不能放到外层 因为这样的写法会删除所有cookie
                        ShoppingCart shoppingCartCookie = JSON.parseObject(Base64Utils.getFromBASE64(cookie.getValue()), ShoppingCart.class);
                        ValueOperations vop = redisTemplate.opsForValue();
                        //判断redis中是否以及存在数据 存在就合并 不存在就创建新的key存放应用数据
                        if (redisTemplate.hasKey("shoppingCart"+token)){
                            String redisObjStr =(String) vop.get("shoppingCart" + token);
                            ShoppingCart shoppingCartRedis = JSON.parseObject(redisObjStr, ShoppingCart.class);
                            List<Good> shoppingCartListCookie = shoppingCartCookie.getGoods();
                            List<Good> shoppingCartListRedis = shoppingCartRedis.getGoods();
                            //比较两个list 方法是遍历一个list 在每一个循环中 将当前Good和另一个List 的所有比较
                            for (Good good : shoppingCartListCookie) {
                                if (shoppingCartListRedis.contains(good)){
                                    Good good1 = shoppingCartListRedis.get(shoppingCartListRedis.indexOf(good));
                                    good1.setGoodNum(good1.getGoodNum() + good.getGoodNum());
                                }else{
                                    shoppingCartListRedis.add(good);
                                }
                            }
                            //现在shoppingCartListRedis 中已经存放了合并后的数据，接下来存数据到redis 删除cookie
                            shoppingCartRedis.setGoods(shoppingCartListRedis);
                            vop.set("shoppingCart" + token, JSON.toJSONString(shoppingCartRedis));
                        }else{
                            //redis中没有存过那就直接将shoppingCartCookie存到redis
                            vop.set("shoppingCart" + token, JSON.toJSONString(shoppingCartCookie));
                        }
                        cookie.setMaxAge(0);
                        //指定要删除的该cookie的path 错误的话 将无法删除成功
                        cookie.setPath("/shoppingcart");
                        response.addCookie(cookie);
                        break;
                    }
                }
                return "success";
            }
            return "cookie为 null";
        }
        return "token为 null";
    }


    /**
     * 根据token 得到购物车中的数据，token为null 去cookie中找 不为null 去redis 中找
     * @param token
     * @return
     */
    @Override
    public List<Good> showGoods(String token, HttpServletRequest request) {
        if (token != null){
            if (redisTemplate.hasKey("shoppingCart"+token)){
                String goodsStr = (String) redisTemplate.opsForValue().get("shoppingCart" + token);
                System.out.println("redis: " + goodsStr);
                return JSON.parseObject(goodsStr,ShoppingCart.class).getGoods();
            }
        }else{
            Cookie[] cookies = request.getCookies();
            if (cookies != null){
                for (Cookie cookie : cookies) {
                    if ("shoppingCart".equals(cookie.getName())){
                        List<Good> goods = JSON.parseObject(Base64Utils.getFromBASE64(cookie.getValue()), ShoppingCart.class).getGoods();
                        System.out.println("cookie: " + Base64Utils.getFromBASE64(cookie.getValue()));
                        return goods;
                    }
                }
            }
        }
        System.out.println("null");
        return null;
    }


    /**
     * 未登录状态的添加购物车方法
     * 在将json存入cookie的时候 如果使用原始json 那么访问该方法的时候就会报错，因为cookie中存的字符串不能包含有引号"" '',而使用fastJson
     * 转化而来的字符串是含有引号的，解决方式有很对 无非就是去掉引号
     * 解法1: 使用加密算法将字符串加密 因为为加密后的支付穿中一般是不会出现特殊字符串的 所以可以实现
     * 解法2: 使用URLEncoding 将字符串转换编码 被转换后的字符串一般也是没有特殊字符的 可以实现
     * @param id
     * @param goodNum
     * @param request
     * @param response
     * @return
     */
    private void addGoodsToCookie(Integer id, Integer goodNum, HttpServletRequest request, HttpServletResponse response) throws Exception{
        //不是登录状态 就像数据转化为json 后添加到cookie中
        Cookie[] cookies = request.getCookies();
        Cookie resCookie = null;

        if (cookies != null){
            //1,循环判断cookie中是否已经存在数据 存在时将数据合并
            for (Cookie cookie : cookies) {
                if ("shoppingCart".equals(cookie.getName())){
                    //得到cookie 中的对象
                    String value = cookie.getValue();
                    //将字符串解密base64 并由fastJson解析为对象
                    ShoppingCart shoppingCartCookieValue = JSON.parseObject(Base64Utils.getFromBASE64(value), ShoppingCart.class);

                    //1.1,比较list集合中的数据看是否有id相同的
                    setShoppingCart(shoppingCartCookieValue,id,goodNum);

                    //1.2,接下来将完成合并后的 goodList封装到shoppingCart后 转化为json字符串 转化编码 然后添加到cookie中去。
                    System.out.println("value : " + JSON.toJSONString(shoppingCartCookieValue));

                    //将 json 字符串先加密 再添加到cookie
                    value =Base64Utils.getBASE64(JSON.toJSONString(shoppingCartCookieValue));
                    cookie.setValue(value);
                    resCookie = cookie;
                    break;
                }
            }
        }
        //不存在就直接 新建对象 将数据填充到里面，再添加到Cookie
        if(resCookie == null){
            Good good = new Good();
            good.setId(Long.valueOf(id));
            good.setGoodNum(goodNum);
            List<Good> goodList = new ArrayList<>();
            goodList.add(good);
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setGoods(goodList);
            System.out.println(JSON.toJSONString(shoppingCart));
            //将json字符串先加密 然后再存到cookie 中
            String encodingStr = Base64Utils.getBASE64(JSON.toJSONString(shoppingCart));
            resCookie = new Cookie("shoppingCart",encodingStr);
            resCookie.setPath("/shoppingcart");
        }

        //将包含完整数据的cookie返回给客户端
        response.addCookie(resCookie);
    }

    /**
     * 当登录后添加购物车的方法
     * 将传来的信息和 redis 中已经存在的信息合并。
     * 思路: 将
     * @param id
     * @param goodNum
     * @return
     * @throws Exception
     */
    private void addGoodsToRedis(Integer id, Integer goodNum, String token) throws Exception{
        //声明对象作为最后提交的参数
        ShoppingCart shoppingCart;

        //先判断redis中是否存在该参数
        if (redisTemplate.hasKey("shoppingCart" + token)){
            String objJson =(String) redisTemplate.opsForValue().get("shoppingCart" + token);
            shoppingCart = JSON.parseObject(objJson, ShoppingCart.class);
            setShoppingCart(shoppingCart,id,goodNum);
        }else{
            shoppingCart = new ShoppingCart();
            Good good = new Good();
            good.setId(Long.valueOf(id));
            good.setGoodNum(goodNum);
            List<Good> goodList = new ArrayList<>();
            goodList.add(good);
            shoppingCart.setGoods(goodList);
        }

        //上面的代码 得到含有数据的shoppingCart 现在 将其Json化 并存到redis中
        String jsonStrRedis = JSON.toJSONString(shoppingCart);
        System.out.println(jsonStrRedis);
        redisTemplate.opsForValue().set("shoppingCart" + token,jsonStrRedis);
    }

    /**
     * 得到封装好后的goodList
     * @param
     * @return
     */
    private void setShoppingCart(ShoppingCart shoppingCart,Integer id, Integer goodNum){
        List<Good> goodsList = shoppingCart.getGoods();
        boolean isExist = false;
        for (Good good : goodsList) {
            //同一商品 就将数量相加 同时结束循环
            if (good.getId().equals(Long.valueOf(id))){
                good.setGoodNum(good.getGoodNum() + goodNum);
                isExist = true;
                break;
            }
        }
        //根据标志位判断是否存在，为false表示不存在 将传来数据作为一个新的good添加到goodList
        if (!isExist){
            Good good = new Good();
            good.setId(Long.valueOf(id));
            good.setGoodNum(goodNum);
            goodsList.add(good);
        }
        shoppingCart.setGoods(goodsList);
    }
}
