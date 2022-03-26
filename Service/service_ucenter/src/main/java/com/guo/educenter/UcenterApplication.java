package com.guo.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName UcenterApplication
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/6/15  15:24
 * @Version 1.0
 **/

@EnableSwagger2
@SpringBootApplication
@ComponentScan({"com.guo"})
@MapperScan("com.guo.educenter.mapper")
@EnableDiscoveryClient
public class UcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
