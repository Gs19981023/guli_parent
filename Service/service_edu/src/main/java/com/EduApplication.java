package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName EduApplication
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/5/17  18:13
 * @Version 1.0
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.guo"})
@EnableDiscoveryClient      //nacos注册服务
@EnableSwagger2
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
