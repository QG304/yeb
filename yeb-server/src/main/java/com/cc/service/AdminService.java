package com.cc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.pojo.Admin;

public interface AdminService extends IService<Admin> {
    Admin getAdminByUserName(String username);
}
