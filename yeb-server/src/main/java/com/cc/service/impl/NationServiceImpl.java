package com.cc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.mapper.NationMapper;
import com.cc.pojo.Nation;
import com.cc.service.NationService;
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
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements NationService {

}
