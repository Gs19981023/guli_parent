package com.guo.msmservice.service;

import java.util.Map;

/**
 * @ClassName MsmService
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/6/13  20:41
 * @Version 1.0
 **/


public interface MsmService {
    boolean send(Map<String, Object> param, String phone);
}
