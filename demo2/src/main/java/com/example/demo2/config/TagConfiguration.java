/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.example.demo2.config;

import org.mybatis.spring.annotation.MapperScan;
import org.shoulder.web.template.tag.service.TagServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuyanming
 * @version : TagConfiguration.java, v 0.1 2024年01月31日 14:33 liuyanming Exp $
 */
@MapperScan(value = { "com.example.demo2.repository", "org.shoulder.web.template.tag.mapper" })
@Configuration
public class TagConfiguration {

    @Bean public TagServiceImpl tagServiceImp() {
        return new TagServiceImpl();
    }

}