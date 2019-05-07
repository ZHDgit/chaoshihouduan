package com.springboot.demo.service;

import com.springboot.demo.model.CsAdmin;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangpeikun
 * @date 2019-05-06
 */
@Service
public class CsAdminService {

    @Autowired
    private SQLManager sqlManager;

    public void addAdmin(CsAdmin csAdmin) {
        sqlManager.insert(csAdmin);
    }

    public void updateAdmin (CsAdmin csAdmin) {
        sqlManager.updateTemplateById(csAdmin);
    }

    public CsAdmin getAdmin(String adminName) {
        Map map = new HashMap<>();
        map.put("adminName", adminName);
        return sqlManager.selectSingle("csAdmin.getAdmin", map, CsAdmin.class);
    }

}
