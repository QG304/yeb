package com.cc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.mapper.AdminMapper;
import com.cc.pojo.Admin;
import com.cc.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cc
 * @since 2022-03-16
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
