package com.guo.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName OssAppliction
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/5/25  15:42
 * @Version 1.0
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.guo"})
@EnableSwagger2
@EnableDiscoveryClient
public class OssApplication {

    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class,args);
    }
}