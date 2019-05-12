package com.springboot.demo.model;

/**
 * Created by xiegege on 2019/5/12.
 */
public class CsGoodsOrder {

    private Integer orderId;
    private Integer goodsId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
}
