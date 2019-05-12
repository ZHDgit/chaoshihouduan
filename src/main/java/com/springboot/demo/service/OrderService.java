package com.springboot.demo.service;

import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangpeikun
 * @date 2019-05-12
 */
@Service
public class OrderService {

    @Autowired
    private SQLManager sqlManager;
}
