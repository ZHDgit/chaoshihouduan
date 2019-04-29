package com.springboot.demo.web;
import com.springboot.demo.common.ReturnString;
import com.springboot.demo.model.CsUser;
import com.springboot.demo.service.CsUserService;
import com.springboot.demo.utils.DESUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserAction {

    @Autowired
    private CsUserService csUserService;

    @ApiOperation(value = "用户注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "userName", dataType = "String", required = true,value = "用户名称"),
            @ApiImplicitParam(paramType = "path", name = "userEmail", dataType = "String", required = true, value = "邮箱"),
            @ApiImplicitParam(paramType = "path", name = "userPassword", dataType = "String", required = true,value = "密码"),
    })

    @PostMapping("addUser/{userName}/{userEmail}/{userPassword}")
    public ReturnString addUser(@PathVariable String userName,@PathVariable String userEmail,@PathVariable String userPassword){
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
        return new ReturnString(0,"注册成功！");
    }

    @ApiOperation(value = "用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "userName", dataType = "String", required = true,value = "用户名称"),
            @ApiImplicitParam(paramType = "path", name = "userPassword", dataType = "String", required = true,value = "密码"),
    })

    @PostMapping("userLogin/{userName}/{userPassword}")
    public ReturnString userLogin(@PathVariable String userName,@PathVariable String userPassword){
        CsUser user = csUserService.getUser(userName);
        if(user == null){
            return new ReturnString("用户不存在");
        }
        try {
            if(!user.getUserPassword().equals(DESUtil.encrypt(userPassword))){
                return new ReturnString("登录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnString("登录失败");
        }
        return new ReturnString(0,"登录成功");
    }
}