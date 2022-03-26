package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName com.MsmApplication
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/6/13  20:49
 * @Version 1.0
 **/

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消自动加载数据库的信息
@ComponentScan("com.guo")
@EnableDiscoveryClient
public class MsmApplication{

    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class,args);
    }
}
