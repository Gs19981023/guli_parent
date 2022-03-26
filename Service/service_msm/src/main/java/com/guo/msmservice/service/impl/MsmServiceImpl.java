package com.guo.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.guo.msmservice.service.MsmService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName MsmServiceImp
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/6/13  20:41
 * @Version 1.0
 **/


@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(Map<String, Object> param, String phone) {
        if(StringUtils.isEmpty(phone)) return false;

        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAI5tH5Ko2HgseaD3AWSu38", "ZCZmF4ZU8A5bY20OCKeViTSe6p2sly");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);固定参数，不需要更改
        request.setMethod(MethodType.POST);     //默认使用post方法
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");


        request.putQueryParameter("PhoneNumbers", phone);//设置手机号
        request.putQueryParameter("SignName", "我的好学网在线教育平台");//签名名
        request.putQueryParameter("TemplateCode", "SMS_199222499");//模板编号
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));


        return true;


    }
}
