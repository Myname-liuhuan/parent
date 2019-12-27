package com.example;

import com.alibaba.fastjson.JSON;
import com.example.java.web.model.entity.Good;
import com.example.java.web.model.entity.ShoppingCart;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 刘欢
 * @Date 2019/12/24
 */
public class MethodTest {

    /**
     * contains 的比较实现 也是依赖于类的equals 方法。
     */
    @Test
    public void test1(){
        Good good = new Good();
        good.setId(123L);

        List<Good> list = new ArrayList<>();
        Good good1 = new Good();
        good1.setId(123L);
        list.add(good1);

        boolean b = list.contains(good);
        System.out.println(b);

    }


    /**
     * 查看ShoppingCart 实体类被转化为json 字符串后的样子
     */
    @Test
    public void test02(){
        Good good = new Good();
        good.setId(123L);
        good.setGoodNum(5);
        Good good1 = new Good();
        good1.setId(1234L);
        good1.setGoodNum(10);

        List<Good> list = new ArrayList<>();
        list.add(good);
        list.add(good1);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setGoods(list);

        String strJson = JSON.toJSONString(shoppingCart);
        System.out.println(strJson);

        ShoppingCart shoppingCart1 = JSON.parseObject(strJson, ShoppingCart.class);
        System.out.println(Arrays.toString(shoppingCart1.getGoods().toArray()));
    }

}
