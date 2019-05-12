package com.springboot.demo.service;

import com.springboot.demo.model.CsAdmin;
import com.springboot.demo.model.CsGoods;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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

    public List<CsAdmin> getAdminList() {
        return sqlManager.select("csAdmin.getAdminList", CsAdmin.class);
    }

    public CsAdmin getAdminById(String adminId) {
        return sqlManager.single(CsAdmin.class, adminId);
    }

    public int deleteAdminById(CsAdmin admin) {
        return sqlManager.deleteObject(admin);
    }


}
