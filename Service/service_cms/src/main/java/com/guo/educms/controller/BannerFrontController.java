package com.guo.educms.controller;

import com.guo.commonutils.R;
import com.guo.educms.pojo.CrmBanner;
import com.guo.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

/**
 * @ClassName BannerFrontController
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/6/7  12:11
 * @Version 1.0
 **/

@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontController {
    @Autowired
    private CrmBannerService crmBannerService;


    @GetMapping("getAllBanner")
    public R getAllBanner(){
        List<CrmBanner> list =crmBannerService.selectAllBanner();
        return R.ok().data("list",list);
    }
}

