package com.springboot.demo.web;

import com.springboot.demo.common.ReturnString;
import com.springboot.demo.model.CsUser;
import com.springboot.demo.service.CsUserService;
import com.springboot.demo.utils.DESUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiegege on 2019/5/6.
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("user")
public class UserAction {

    @Autowired
    private CsUserService csUserService;

    @ApiOperation(value = "用户注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "userName", dataType = "String", required = true, value = "用户名称"),
            @ApiImplicitParam(paramType = "path", name = "userEmail", dataType = "String", required = true, value = "邮箱"),
            @ApiImplicitParam(paramType = "path", name = "userPassword", dataType = "String", required = true, value = "密码"),
    })

    @PostMapping("addUser/{userName}/{userEmail}/{userPassword}")
    public ReturnString addUser(@PathVariable String userName, @PathVariable String userEmail, @PathVariable String userPassword) {
        CsUser csUser = new CsUser();
        try {
            csUser.setUserName(userName);
            csUser.setUserEmail(userEmail);
            csUser.setUserPassword(DESUtil.encrypt(userPassword));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int count = csUserService.addUser(csUser);
        if (count < 1) {
            return new ReturnString("注册失败！");
        }
        return new ReturnString(0, "注册成功！");
    }

    @ApiOperation(value = "用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "userName", dataType = "String", required = true, value = "用户名称"),
            @ApiImplicitParam(paramType = "path", name = "userPassword", dataType = "String", required = true, value = "密码"),
    })

    @PostMapping("userLogin/{userName}/{userPassword}")
    public ReturnString userLogin(@PathVariable String userName, @PathVariable String userPassword) {
        CsUser user = csUserService.getUser(userName);
        if (user == null) {
            return new ReturnString("用户不存在");
        }
        try {
            if (!user.getUserPassword().equals(DESUtil.encrypt(userPassword))) {
                return new ReturnString("登录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnString("登录失败");
        }
        return new ReturnString(user.getId());
    }

    @ApiOperation(value = "验证用户名是否唯一接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "userName", dataType = "String", required = true, value = "用户名称"),
    })
    @PostMapping("checkUserName/{userName}")
    public ReturnString checkUserName(@PathVariable String userName) {
        CsUser user = csUserService.getUser(userName);
        if (user == null) {
            return new ReturnString(0, "用户名可以注册！");
        } else {
            return new ReturnString("用户名已经存在！");
        }
    }

    @ApiOperation(value = "用户修改密码接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "userId", dataType = "int", required = true, value = "用户id"),
            @ApiImplicitParam(paramType = "path", name = "password", dataType = "String", required = true, value = "旧密码"),
            @ApiImplicitParam(paramType = "path", name = "newPassword", dataType = "String", required = true, value = "新密码")
    })
    @PostMapping("updatePassword/{userId}/{password}/{newPassword}")
    public ReturnString updatePassword(@PathVariable Integer userId, @PathVariable String password, @PathVariable String newPassword) {
        try {
            // 获取用户
            CsUser user = csUserService.getUserById(userId);
            // 判断密码是否相同
            if (!user.getUserPassword().equals(DESUtil.encrypt(password))) {
                return new ReturnString("旧密码错误");
            } else {
                user.setUserPassword(DESUtil.encrypt(newPassword));
                int count = csUserService.updatePassword(user);
                if (count < 1) {
                    return new ReturnString("修改密码失败");
                }
                return new ReturnString(0, "修改密码成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnString("修改密码失败");
        }
    }

    @ApiOperation(value = "会员列表接口")
    @ApiImplicitParams({

    })
    @PostMapping("getUserList")
    public ReturnString getUserList() {
        try {
            Map<String, Object> map = new HashMap<>();
            List<CsUser> userList = csUserService.getUserList();
            map.put("userList", userList);
            map.put("userAmount", userList.size());
            return new ReturnString(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnString("获取会员列表接口出错");
        }
    }
}