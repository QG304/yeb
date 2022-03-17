package com.cc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.mapper.DepartmentMapper;
import com.cc.pojo.Department;
import com.cc.service.DepartmentService;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
