package com.guo.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guo.commonutils.R;
import com.guo.educms.pojo.CrmBanner;

import com.guo.educms.service.CrmBannerService;
import com.guo.servicebase.exceptionhandler.DiyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author guoshuai
 * @since 2021-06-07
 */


@CrossOrigin
@RestController
@RequestMapping("/educms/banneradmin")

public class BannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("pageBanner/{current}/{limit}")
    public R pageBanner(@PathVariable long current,
                        @PathVariable long limit){

        Page<CrmBanner> page =new Page<>(current,limit);

        crmBannerService.page(page,null);

        return R
                .ok()
                .data("total",page.getTotal())
                .data("items",page.getRecords());
    }

    @ApiOperation(value = "添加banner")
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner banner){

        crmBannerService.save(banner);
            return R.ok();
    }

    @ApiOperation(value = "修改banner")
    @PostMapping("updateBanner")
    public R updateBanner(@RequestBody CrmBanner banner){

        crmBannerService.updateById(banner);

        return R.ok();

    }
    @ApiOperation(value = "删除banner")
    @PostMapping("deleteBanner/{id}")
    public R deleteBanner(@PathVariable String id){

        crmBannerService.removeById(id);

        return R.ok();

    }
}

