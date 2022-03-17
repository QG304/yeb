package com.cc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.mapper.EmployeeMapper;
import com.cc.pojo.Employee;
import com.cc.service.EmployeeService;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
