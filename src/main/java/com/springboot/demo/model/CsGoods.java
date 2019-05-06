package com.springboot.demo.model;

import sun.dc.pr.PRError;

/**
 * Created by xiegege on 2019/5/6.
 */
public class CsGoods {

    private Integer id;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 原价
     */
    private Double goodsPrice;
    /**
     * 优惠价格
     */
    private Double goodsOfferPrice;
    /**
     * 商品数量
     */
    private Integer goodsNum;
    /**
     * 商品图片
     */
    private String goodsImage;
    /**
     * 商品介绍
     */
    private String goodsIntroduce;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Double getGoodsOfferPrice() {
        return goodsOfferPrice;
    }

    public void setGoodsOfferPrice(Double goodsOfferPrice) {
        this.goodsOfferPrice = goodsOfferPrice;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsIntroduce() {
        return goodsIntroduce;
    }

    public void setGoodsIntroduce(String goodsIntroduce) {
        this.goodsIntroduce = goodsIntroduce;
    }
}
