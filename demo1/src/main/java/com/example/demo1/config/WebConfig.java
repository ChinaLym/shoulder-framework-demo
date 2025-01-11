package com.example.demo1.config;

import org.shoulder.core.converter.ShoulderConversionService;
import org.shoulder.core.converter.ShoulderConversionServiceImpl;
import org.shoulder.core.util.ConvertUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.boot.autoconfigure.web.format.WebConversionService;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Bean
    public FormattingConversionService mvcConversionService() {
        DateTimeFormatters dateTimeFormatters = new DateTimeFormatters()
                .dateFormat("iso")
                .timeFormat("iso")
                .dateTimeFormat("iso");

        ShoulderConversionServiceImpl conversionService = new ShoulderConversionServiceImpl(dateTimeFormatters);
        ConvertUtil.setConversionService(conversionService);
        return conversionService;
    }
}