package com.example.demo3;

import org.shoulder.autoconfigure.security.token.TokenEndPointConfiguration;
import org.shoulder.security.authentication.BeforeAuthEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

/**
 * 尝试进入主页 http://localhost:8080/ 因引入了框架的认证，若未登录会自动跳转至认证页面，认证通过后才可访问
 *
 * @see BeforeAuthEndpoint 认证前会跳到这里先
 * @author lym
 */
@SpringBootApplication// 0.4版本 session 验证需要加这里 (exclude = {TokenEndPointConfiguration.class})
public class Demo3Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo3Application.class, args);
    }

    @Bean
    AccessDeniedHandlerImpl accessDeniedHandler(){
        return new AccessDeniedHandlerImpl();

    }}
