package com.springboot.demo.web;

import com.springboot.demo.common.ReturnString;
import com.springboot.demo.model.CsAdmin;
import com.springboot.demo.model.CsUser;
import com.springboot.demo.service.CsAdminService;
import com.springboot.demo.utils.DESUtil;
import com.springboot.demo.web.vo.AdminVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ReturnString addAdmin(CsAdmin csAdmin){
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
    public ReturnString updateAdmin(CsAdmin csAdmin){
        try {
            csAdminService.updateAdmin(csAdmin);
            return new ReturnString(0,"修改管理员成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnString("修改管理员失败！");
        }

    }

    @ApiOperation(value = "管理员登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "adminVo", dataType = "Object",value = "管理员资料"),
    })

    @PostMapping("adminLogin")
    public ReturnString userLogin(AdminVo adminVo){
        CsAdmin admin = csAdminService.getAdmin(adminVo.getAdminName());
        if(admin == null){
            return new ReturnString("管理员不存在");
        }
        try {
            if(!admin.getAdminPassword().equals(DESUtil.encrypt(adminVo.getAdminPassword()))){
                return new ReturnString("登录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnString("登录失败");
        }
        Map map = new HashMap();
        map.put("type", admin.getType());
        return new ReturnString(map);
    }

    @ApiOperation(value = "验证管理员名称是否唯一接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "adminName", dataType = "String", required = true,value = "管理员名称"),
    })
    @PostMapping("checkAdminName/{adminName}")
    public ReturnString checkUserName(@PathVariable String adminName){
        CsAdmin admin = csAdminService.getAdmin(adminName);
        if(admin == null){
            return new ReturnString(0,"管理员名称注册！");
        }else {
            return new ReturnString("管理员名称已经存在！");
        }
    }

    @ApiOperation(value = "查询管理员列表接口")
    @PostMapping("getAdminList")
    public ReturnString getAdminList(){
        try {
            List<CsAdmin> adminList = csAdminService.getAdminList();
            return new ReturnString(adminList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnString("查询管理员列表失败！");
        }
    }
}
