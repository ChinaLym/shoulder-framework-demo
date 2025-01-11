package com.example.demo2;

import org.mybatis.spring.annotation.MapperScan;
import org.shoulder.core.log.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.function.Supplier;


/**
 * @author lym
 */
@SpringBootApplication
@MapperScan("com.example.demo2.repository")
public class Demo2Application {
    private final static Logger l = LoggerFactory.getLogger(Demo2Application.class);
    public static void main(String[] args) {
        Supplier supplier = () -> "xxxx";
        l.info("xxx{}", supplier);
        System.out.println(supplier);
        SpringApplication.run(Demo2Application.class, args);
    }

}
