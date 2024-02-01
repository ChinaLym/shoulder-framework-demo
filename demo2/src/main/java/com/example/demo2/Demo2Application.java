package com.example.demo2;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * @author lym
 */
@SpringBootApplication
@MapperScan("com.example.demo2.repository")
public class Demo2Application {
    //添加分页拦截器
//    @Bean public PaginationInnerInterceptor pageInnerInterceptor() {
//        return new PaginationInnerInterceptor();
//    }
//    //添加乐观锁拦截器
//    @Bean public OptimisticLockerInnerInterceptor optimizationInnerInterceptor() {
//        return new OptimisticLockerInnerInterceptor();
//    }
    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }

}
