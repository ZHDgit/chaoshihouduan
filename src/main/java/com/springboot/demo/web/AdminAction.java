package com.springboot.demo.web;

import com.springboot.demo.common.ReturnString;
import com.springboot.demo.model.CsAdmin;
import com.springboot.demo.model.CsUser;
import com.springboot.demo.service.CsAdminService;
import com.springboot.demo.utils.DESUtil;
import io.micrometer.core.instrument.Tags;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author zhangpeikun
 * @date 2019-05-06
 */
@Api(tags = "管理员接口")
@RestController
@RequestMapping("admin")
public class AdminAction {

    @Autowired
    private CsAdminService csAdminService;

    @ApiOperation(value = "添加管理员接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "csAdmin", dataType = "Object",value = "管理员资料"),
    })
    @PostMapping("addAdmin")
    public ReturnString addAdmin(@RequestBody CsAdmin csAdmin){
        try {
            csAdmin.setCreateDate(new Date());
            csAdmin.setAdminPassword(DESUtil.encrypt(csAdmin.getAdminPassword()));
            csAdminService.addAdmin(csAdmin);
            return new ReturnString(0,"添加管理员成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnString("添加管理员失败！");
        }

    }

    @ApiOperation(value = "修改管理员接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "csAdmin", dataType = "Object",value = "管理员资料"),
    })
    @PostMapping("updateAdmin")
    public ReturnString updateAdmin(@RequestBody CsAdmin csAdmin){
        try {
            csAdminService.updateAdmin(csAdmin);
            return new ReturnString(0,"修改管理员成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnString("修改管理员失败！");
        }

    }

}
