package com.example.java.web.model.entity;

import java.io.Serializable;

/**
 * @author 刘欢
 * @Date 2019/12/24
 *
 * 商品信息类的实体类 存放添加到购物车的商品信息
 */
public class Good extends WebProductDetailEntity implements Serializable {


    /**
     * 标记当前货物的数量 默认为 1
     * 继承而来的num与其不能混为一谈
     */
    private Integer goodNum = 1;

    public Integer getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(Integer goodNum) {
        this.goodNum = goodNum;
    }

    /**
     * 重写equals 方法当两者的 id相等的时候就返回true否则返回false
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof Good)){
            Good good = (Good)obj;
            if (good.getId().equals(this.getId())){
                return true;
            }
        }
        return false;
    }
}
