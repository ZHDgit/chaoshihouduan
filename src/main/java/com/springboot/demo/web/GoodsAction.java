package com.springboot.demo.web;

import com.springboot.demo.common.ReturnString;
import com.springboot.demo.model.CsGoods;
import com.springboot.demo.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by xiegege on 2019/5/7.
 */
@Api(tags = "商品接口")
@RestController
@RequestMapping("goods")
public class GoodsAction {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "添加商品接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "goodsName", dataType = "String", required = true, value = "商品名称"),
            @ApiImplicitParam(paramType = "query", name = "goodsPrice", dataType = "String", required = true, value = "原价"),
            @ApiImplicitParam(paramType = "query", name = "goodsOfferPrice", dataType = "String", required = true, value = "优惠价格"),
            @ApiImplicitParam(paramType = "query", name = "goodsNum", dataType = "int", required = true, value = "商品数量"),
            @ApiImplicitParam(paramType = "query", name = "goodsImage", dataType = "String", required = true, value = "商品图片"),
            @ApiImplicitParam(paramType = "query", name = "goodsIntroduce", dataType = "String", required = true, value = "商品介绍"),
            @ApiImplicitParam(paramType = "query", name = "goodsCategory", dataType = "int", required = true, value = "商品分类：1|杂货  2|家用  3|个人护理  4|包装食品  5|饮料&酒水  6|水果"),
            @ApiImplicitParam(paramType = "query", name = "goodsType", dataType = "int", required = true, value = "商品类型：1|今日热卖  2|今日优惠  3|新品推荐"),
    })
    @PostMapping("addGoods")
    public ReturnString addGoods(CsGoods csGoods, HttpSession session) {
        try {
            int count = goodsService.addGoods(csGoods);
            if (count < 1) {
                return new ReturnString("添加商品接口出错");
            }
            return new ReturnString(0, "添加商品接口成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnString("添加商品接口出错");
        }
    }

    @ApiOperation(value = "获取商品接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "searchNumber", dataType = "String", required = true, value = "0|所有商品  商品分类：商品分类：1|杂货 2|家用 3|个人护理 4|包装食品 5|饮料&酒水 6|水果 或商品类型：1|今日热卖  2|今日优惠  3|新品推荐"),
            @ApiImplicitParam(paramType = "path", name = "searchType", dataType = "String", required = true, value = "0|所有商品  1|商品分类  2|商品类型"),
    })
    @PostMapping("getGoodsList/{searchNumber}/{searchType}")
    public ReturnString getGoodsList(@PathVariable Integer searchNumber, @PathVariable Integer searchType, HttpSession session) {
        try {
            List<CsGoods> goodsList = goodsService.getGoodsList(searchNumber, searchType);
            return new ReturnString(goodsList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnString("获取获取商品接口出错");
        }
    }

    @ApiOperation(value = "删除商品接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "goodsId", dataType = "int", required = true, value = "商品id"),
    })
    @PostMapping("deleteGoodsById/{goodsId}")
    public ReturnString deleteGoodsById(@PathVariable Integer goodsId, HttpSession session) {
        try {
            CsGoods goods = new CsGoods();
            goods.setId(goodsId);
            int count = goodsService.deleteGoodsById(goods);
            if (count < 1) {
                return new ReturnString("删除商品失败");
            }
            return new ReturnString(0, "删除商品成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnString("删除商品失败");
        }
    }

    @ApiOperation(value = "根据id查询商品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "goodsId", dataType = "int", required = true, value = "商品id"),
    })
    @PostMapping("getGoodsById/{goodsId}")
    public ReturnString getGoodsById(@PathVariable Integer goodsId, HttpSession session) {
        try {
            CsGoods goods = goodsService.getGoodsById(goodsId);
            return new ReturnString(goods);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnString("根据id查询商品出错");
        }
    }

    @ApiOperation(value = "编辑商品接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "goodsId", dataType = "int", required = true, value = "商品id"),
            @ApiImplicitParam(paramType = "query", name = "goodsName", dataType = "String", value = "商品名称"),
            @ApiImplicitParam(paramType = "query", name = "goodsPrice", dataType = "String", value = "原价"),
            @ApiImplicitParam(paramType = "query", name = "goodsOfferPrice", dataType = "String", value = "优惠价格"),
            @ApiImplicitParam(paramType = "query", name = "goodsNum", dataType = "int", value = "商品数量"),
            @ApiImplicitParam(paramType = "query", name = "goodsImage", dataType = "String", value = "商品图片"),
            @ApiImplicitParam(paramType = "query", name = "goodsIntroduce", dataType = "String", value = "商品介绍"),
            @ApiImplicitParam(paramType = "query", name = "goodsCategory", dataType = "int", value = "商品分类：1|杂货  2|家用  3|个人护理  4|包装食品  5|饮料&酒水  6|水果"),
            @ApiImplicitParam(paramType = "query", name = "goodsType", dataType = "int", value = "商品类型：1|今日热卖  2|今日优惠  3|新品推荐"),})
    @PostMapping("updateGoods/{goodsId}")
    public ReturnString updateGoods(@PathVariable Integer goodsId, CsGoods goods, HttpSession session) {
        try {
            goods.setId(goodsId);
            int count = goodsService.updateGoods(goods);
            if (count < 1) {
                return new ReturnString("编辑商品出错");
            }
            return new ReturnString(0, "编辑商品成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnString("编辑商品出错");
        }
    }
}