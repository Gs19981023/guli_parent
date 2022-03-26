package com.guo.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName CmsApplication
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/6/7  12:07
 * @Version 1.0
 **/
@EnableFeignClients    //服务调用
@EnableDiscoveryClient  //nacos注册
@SpringBootApplication
@ComponentScan({"com.guo"}) //指定扫描位置,组件扫描
@MapperScan("com.guo.educms.mapper")
@EnableSwagger2
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}