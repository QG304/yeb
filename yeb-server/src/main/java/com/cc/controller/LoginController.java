package com.cc.controller;

import com.cc.pojo.AdminLoginParam;
import com.cc.pojo.RespBean;
import com.cc.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

//登录
@Api(tags = "LoginController")
@RestController
public class LoginController {
    @Autowired
    private IAdminService adminService;
    @ApiOperation(value = "登路之后返回token")
    @PostMapping("/login")
    public RespBean login(AdminLoginParam adminLoginParam, HttpServletRequest request){
       return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),request);
    }

}
