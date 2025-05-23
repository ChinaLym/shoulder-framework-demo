package com.example.demo2.config;

import org.shoulder.autoconfigure.batch.BatchActivityEnumRepositoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfiguration {

    @Bean
    public BatchActivityEnumRepositoryCustomizer batch() {
        return repository -> {
            repository.register(MyTaskEnum.class, "并行任务");
            repository.register(MySimpleTaskEnum.class, "AI 思考过程");
        };
    }
}
