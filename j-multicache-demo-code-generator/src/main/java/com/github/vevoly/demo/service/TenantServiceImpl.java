package com.github.vevoly.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.vevoly.demo.entity.Tenant;
import com.github.vevoly.demo.mapper.TenantMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 租户信息表 服务实现类
 * </p>
 *
 * @author demo-generator
 * @since 2025-09-15
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements IService<Tenant> {

}
