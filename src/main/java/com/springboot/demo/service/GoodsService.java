package com.springboot.demo.service;

import com.springboot.demo.model.CsGoods;
import org.beetl.sql.core.SQLManager;
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

    public List<CsGoods> getGoodsList(Integer searchNumber, Integer searchType) {
        Map<String, Integer> map = new HashMap<>();
        map.put("searchNumber", searchNumber);
        map.put("searchType", searchType);
        return sqlManager.select("csGoods.getGoodsList", CsGoods.class, map);
    }
}