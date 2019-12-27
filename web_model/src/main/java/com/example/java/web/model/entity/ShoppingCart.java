package com.example.java.web.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author 刘欢
 * @Date 2019/12/24
 *
 * 购物车对应的实体类 里面存放good 的集合
 *
 */
public class ShoppingCart implements Serializable {
    private static final long serialVersionUID = 4282685045524045824L;

    private List<Good> goods;

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }
}
