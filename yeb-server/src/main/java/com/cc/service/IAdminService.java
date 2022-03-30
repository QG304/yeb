package com.cc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.pojo.Admin;
import com.cc.pojo.RespBean;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
@Service
public interface IAdminService extends IService<Admin> {
    //登录之后返回token

    RespBean login(String username, String password, HttpServletRequest request);
    //根据用户名获取用户
    Admin getAdminByUserName(String username);
}
