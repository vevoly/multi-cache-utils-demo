package com.github.vevoly.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.vevoly.demo.entity.BannerUserType;
import com.github.vevoly.demo.mapper.BannerUserTypeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Banner与用户类型的多对多关联表 服务实现类
 * </p>
 *
 * @author demo-generator
 * @since 2025-09-15
 */
@Service
public class BannerUserTypeServiceImpl extends ServiceImpl<BannerUserTypeMapper, BannerUserType> implements IService<BannerUserType> {

}
