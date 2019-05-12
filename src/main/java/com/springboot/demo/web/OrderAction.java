package com.springboot.demo.web;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangpeikun
 * @date 2019-05-12
 */
@Api(tags = "订单接口")
@RestController
@RequestMapping("order")
public class OrderAction {
}
