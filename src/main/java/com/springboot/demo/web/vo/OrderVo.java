package com.springboot.demo.web.vo;

/**
 * Created by xiegege on 2019/5/12.
 */
public class OrderVo {

    private Integer orderId;
    private Integer goodsId;
    private String goodsName;
    private Integer num;
    private Double goodsOfferPrice;

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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getGoodsOfferPrice() {
        return goodsOfferPrice;
    }

    public void setGoodsOfferPrice(Double goodsOfferPrice) {
        this.goodsOfferPrice = goodsOfferPrice;
    }
}
