package com.example.demo5;

import org.shoulder.autoconfigure.redis.EnableRedisMock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

/**
 * 进程通信安全demo-server
 *
 * @author lym
 */
@SpringBootApplication
@EnableRedisMock
@Profile("client")// 额外激活 application-client.properties 配置文件，模拟 client 端（占用 8080端口）
public class Demo5_Client {

    public static void main(String[] args) {
        SpringApplication.run(Demo5_Client.class, args);
    }

}
