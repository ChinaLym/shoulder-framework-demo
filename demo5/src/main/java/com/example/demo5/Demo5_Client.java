package com.example.demo5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * 进程通信安全demo-server
 *
 * @author lym
 */
@SpringBootApplication
@PropertySource("classpath:/application-common.properties")
@PropertySource("classpath:/application-client.properties")// 指定配置文件，模拟 client 端（占用 8080端口）
public class Demo5_Client {
    public static void main(String[]
 args) {
        SpringApplication.run(Demo5_Client.class, args);
    }

}
