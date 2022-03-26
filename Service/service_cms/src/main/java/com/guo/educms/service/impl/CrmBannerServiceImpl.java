package com.guo.educms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guo.educms.pojo.CrmBanner;
import com.guo.educms.mapper.CrmBannerMapper;
import com.guo.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author guoshuai
 * @since 2021-06-07
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {




    @Cacheable(value  = "banner",key = "'selectIndexList'")
    @Override
    public List<CrmBanner> selectAllBanner() {

        QueryWrapper<CrmBanner> wrapper =new QueryWrapper<>();

        wrapper.orderByDesc("id");

        wrapper.last("limit 2");

        List<CrmBanner> crmBanners = baseMapper.selectList(null);

        return crmBanners;
    }
}
