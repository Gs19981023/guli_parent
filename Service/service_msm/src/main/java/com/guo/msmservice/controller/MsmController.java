package com.guo.msmservice.controller;

import com.guo.commonutils.R;
import com.guo.commonutils.RandomUtil;
import com.guo.msmservice.service.MsmService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName MsmController
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/6/13  18:24
 * @Version 1.0
 **/


@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin

public class MsmController {


    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone){

        String code = redisTemplate.opsForValue().get(phone);

        if(!StringUtils.isEmpty(code)){
            return R.ok();
        }

        code = RandomUtil.getFourBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code",code);

        boolean isSend = msmService.send(param,phone);


        if(isSend){
            return R.ok();
        }else{
            return R.error().message("短信发送失败");
        }

    }
}
