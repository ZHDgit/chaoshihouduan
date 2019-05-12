package com.springboot.demo.web;

import com.springboot.demo.common.ReturnString;
import com.springboot.demo.model.CsOrder;
import com.springboot.demo.service.OrderService;
import com.springboot.demo.web.vo.OrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Date;

/**
 * @author zhangpeikun
 * @date 2019-05-12
 */
@Api(tags = "订单接口")
@RestController
@RequestMapping("order")
public class OrderAction {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "添加订单接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "goodsIds", allowMultiple = true, required = true, value = "商品id-数量"),
            @ApiImplicitParam(paramType = "query", name = "phone", dataType = "String", required = true, value = "联系电话"),
            @ApiImplicitParam(paramType = "query", name = "address", dataType = "String", required = true, value = "收货地址"),
            @ApiImplicitParam(paramType = "query", name = "price", dataType = "String", required = true, value = "价格"),
    })
    @PostMapping("addOrder")
    public ReturnString addGoods(CsOrder csOrder, HttpSession session) {
        try {
            csOrder.setCreateDate(new Date());
            orderService.addOrder(csOrder);
            return new ReturnString(0, "添加订单接口成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnString("添加订单接口出错");
        }
    }

    @ApiOperation(value = "订单列表接口")
    @ApiImplicitParams({

    })
    @PostMapping("getOrderList")
    public ReturnString getOrderList(HttpSession session) {
        try {
            List<CsOrder> orderList = orderService.getOrderList();
            return new ReturnString(orderList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnString("获取订单列表接口出错");
        }
    }

    @ApiOperation(value = "订单详情接口")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "orderId", dataType = "int", required = true, value = "订单id")
    })
    @PostMapping("getOrder/{orderId}")
    public ReturnString getOrder(@PathVariable Integer orderId,HttpSession session) {
        try{
            List<OrderVo> orderVos = orderService.getOrderDetail(orderId);
            return new ReturnString(orderVos);
        }catch(Exception e){
            e.printStackTrace();
            return new ReturnString("获取订单详情接口出错");
        }
    }
}