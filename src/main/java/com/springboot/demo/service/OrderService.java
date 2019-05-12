package com.springboot.demo.service;

import com.springboot.demo.model.CsGoodsOrder;
import com.springboot.demo.model.CsOrder;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangpeikun
 * @date 2019-05-12
 */
@Service
public class OrderService {

    @Autowired
    private SQLManager sqlManager;

    @Transactional
    public void addOrder (CsOrder order){
        sqlManager.insertTemplate(order, true);
        Map map = new HashMap();
        Integer orderId = sqlManager.selectSingle("order.getMaxOrderId", map,Integer.class);
        CsGoodsOrder goodsOrder = new CsGoodsOrder();
        String[] goodsIds = order.getGoodsIds();
        for (String goodsId : goodsIds) {
            String[] goods = goodsId.split("-");
            goodsOrder.setGoodsId(Integer.parseInt(goods[0]));
            goodsOrder.setNum(Integer.parseInt(goods[1]));
            goodsOrder.setOrderId(orderId);
            sqlManager.insert(goodsOrder);
        }
    }
}
