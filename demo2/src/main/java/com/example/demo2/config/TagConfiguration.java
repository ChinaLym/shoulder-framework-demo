package com.example.demo2.config;

import org.mybatis.spring.annotation.MapperScan;
import org.shoulder.web.template.tag.mapper.TagMappingMapper;
import org.shoulder.web.template.tag.repository.TagMappingService;
import org.shoulder.web.template.tag.repository.TagRepository;
import org.shoulder.web.template.tag.service.TagServiceImpl;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration(proxyBeanMethods = false)
@MapperScan(basePackages = {"org.shoulder.web.template.tag.mapper", "com.example.demo2.repository"})
public class TagConfiguration {

    @Bean
    public TagMappingService tagMappingService() {
        return new TagMappingService();
    }
    @Bean
    public TagRepository tagRepository() {
        return new TagRepository();
    }

}
