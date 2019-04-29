package com.springboot.demo.service;

import com.springboot.demo.model.CsUser;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CsUserService {

    @Autowired
    private SQLManager sqlManager;

    public int addUser(CsUser user) {
        return sqlManager.insert(user);
    }

    public CsUser getUser(String userName) {
        Map map = new HashMap<>();
        map.put("userName", userName);
        return sqlManager.selectSingle("csUser.getUser", map, CsUser.class);
    }
}
