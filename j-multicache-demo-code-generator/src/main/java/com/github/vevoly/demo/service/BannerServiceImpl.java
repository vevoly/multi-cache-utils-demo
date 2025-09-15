package com.github.vevoly.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.vevoly.demo.entity.Banner;
import com.github.vevoly.demo.mapper.BannerMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Banner基础信息表 服务实现类
 * </p>
 *
 * @author demo-generator
 * @since 2025-09-15
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements IService<Banner> {

}
