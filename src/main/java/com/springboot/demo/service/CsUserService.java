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

    public CsUser getUserById(Integer userId) {
        Map map = new HashMap<>();
        map.put("userId", userId);
        return sqlManager.selectSingle("csUser.getUserById", map, CsUser.class);
    }

    public int updatePassword(CsUser user) {
        return sqlManager.updateById(user);
    }
}
