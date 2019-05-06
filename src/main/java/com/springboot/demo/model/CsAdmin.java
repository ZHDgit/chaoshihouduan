package com.springboot.demo.model;

import java.util.Date;

/**
 * @author zhangpeikun
 * @date 2019-05-06
 */
public class CsAdmin {

    private Integer id;
    private String  adminName;
    private String  adminPassword;
    private Integer type;
    private Date    createDate;

    public CsAdmin() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
