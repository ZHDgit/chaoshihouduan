package com.springboot.demo.service;

import com.springboot.demo.model.CsGoods;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiegege on 2019/5/7.
 */
@Service
public class GoodsService {

    @Autowired
    private SQLManager sqlManager;

    public int addGoods(CsGoods csGoods) {
        return sqlManager.insert(csGoods);
    }

    public List<CsGoods> getGoodsList(Integer searchNumber, Integer searchType, Integer pageNo, Integer pageSize, String searchText) {
        Map map = new HashMap<>();
        map.put("searchNumber", searchNumber);
        map.put("searchType", searchType);
        map.put("searchText", searchText);

        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageNumber(pageNo);
        pageQuery.setPageSize(pageSize);
        pageQuery.setParas(map);
        return sqlManager.pageQuery("csGoods.getGoodsList", CsGoods.class, pageQuery).getList();
    }

    public int deleteGoodsById(CsGoods goods) {
        return sqlManager.deleteObject(goods);
    }

    public CsGoods getGoodsById(Integer goodsId) {
        Map map = new HashMap<>();
        map.put("goodsId", goodsId);
        return sqlManager.selectSingle("csGoods.getGoodsById", map, CsGoods.class);
    }

    public int updateGoods(CsGoods goods) {
        return sqlManager.updateById(goods);
    }
}