package com.example.demo5;

import org.shoulder.autoconfigure.redis.EnableEmbeddedRedis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * 进程通信安全demo-server
 *
 * @author lym
 */
@SpringBootApplication
@EnableEmbeddedRedis
@PropertySource("classpath:/application-server.properties")// 指定配置文件，模拟 Server 端（占用 80端口）
public class Demo5_Server {

    public static void main(String[] args) {
        SpringApplication.run(Demo5_Server.class, args);
    }

}
