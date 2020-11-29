package com.example.demo6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;

import java.util.HashMap;
import java.util.Map;

/**
 * shoulder-framework 实例工程
 * TIP：运行后（默认为本机8080端口），进入 controller 目录（已按照功能分类），点击方法上的超链接（IDE支持），即可快速查看效果
 *
 * @author lym
 */
@SpringBootApplication
public class Demo6Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo6Application.class, args);
    }

    @Bean
    @ConditionalOnMissingBean(ClientDetailsService.class)
    public ClientDetailsService clientDetailsService() {
        InMemoryClientDetailsService service = new InMemoryClientDetailsService();

        Map<String, ClientDetails> clientDetailsStore = new HashMap<>();
        //String clientId, String resourceIds, String scopes, String grantTypes, String authorities
        BaseClientDetails details = new BaseClientDetails("shoulder", "oauth2-resource", "scopes", "grantTypes", "authorities");
        details.setClientSecret("shoulder");
        clientDetailsStore.put(details.getClientId(), details);

        service.setClientDetailsStore(clientDetailsStore);
        return service;
    }
}
