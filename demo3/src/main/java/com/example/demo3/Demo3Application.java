package com.example.demo3;

import org.shoulder.core.exception.CommonErrorCodeEnum;
import org.shoulder.core.util.AssertUtils;
import org.shoulder.security.authentication.BeforeAuthEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import redis.embedded.RedisServer;

import java.io.IOException;

/**
 * 尝试进入主页 http://localhost:8080/ 因引入了框架的认证，若未登录会自动跳转至认证页面，认证通过后才可访问
 *
 * @author lym
 * @see BeforeAuthEndpoint 认证前会跳到这里先
 */
@SpringBootApplication
public class Demo3Application {
// todo h2 数据库
    public static void main(String[] args) {
        SpringApplication.run(Demo3Application.class, args);
    }

    @Bean
    AccessDeniedHandlerImpl accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }
}
