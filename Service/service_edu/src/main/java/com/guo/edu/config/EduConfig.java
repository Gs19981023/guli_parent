package com.guo.edu.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName EduConifg
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/5/17  18:15
 * @Version 1.0
 **/
@Configuration
@MapperScan("com.guo.edu.mapper")
public class EduConfig {

    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }


    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
