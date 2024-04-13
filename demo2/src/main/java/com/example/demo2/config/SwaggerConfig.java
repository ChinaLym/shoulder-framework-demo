package com.example.demo2.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.mybatis.spring.annotation.MapperScan;
import org.shoulder.web.template.oplog.controller.OperationLogQueryController;
import org.shoulder.web.template.oplog.service.OperationLogService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * 接口文档配置：
 * <p><a href="http://127.0.0.1:8080/swagger-ui.html">接口文档-ui页面</a>
 * <p><a href="http://127.0.0.1:8080/v3/api-docs.yaml">接口文档-json形式地址</a>
 * <p><a href="http://127.0.0.1:8080/v3/api-docs.yaml">接口文档-yaml形式地址</a>
 *
 * @author lym
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("Auth-AppName", new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name("Auth-AppName"))
                        .addSecuritySchemes("Auth-Token", new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name("Auth-Token")))
                .info(new Info().title("SHOULDER-DEMO")
                        .description("SHOULDER-DEMO-接口文档")
                        .version("v1.0"));
    }
}