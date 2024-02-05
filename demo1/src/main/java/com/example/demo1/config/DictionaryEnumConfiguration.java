package com.example.demo1.config;

import com.example.demo1.enums.DictionaryTestEnum;
import org.shoulder.autoconfigure.web.DictionaryEnumRepositoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动生成枚举接口，方便下拉框的开发
 *
 * @author lym
 */
@Configuration
public class DictionaryEnumConfiguration {

    /**
     * 枚举自带 api
     * 查询有哪些字典（枚举）<a href="http://localhost:8080/api/v1/dictionary/type/all"/>
     * 查询枚举有哪些字段 <a href="http://localhost:8080/api/v1/dictionary/listByType/DictionaryTestEnum"/>
     * 查询枚举有哪些字段，一次查多个枚举（批量性能更好） <a href="http://localhost:8080/api/v1/dictionary/listByTypes?dictionaryTypeList=DictionaryTestEnum,DictionaryTestEnum2"/>
     *
     * DB 类型 api see demo2
     *
     * @return DictionaryEnumRepositoryCustomizer
     * @see org.shoulder.web.template.dictionary.controller.DictionaryEnumController
     */
    @Bean
    public DictionaryEnumRepositoryCustomizer dictionaryEnumRepositoryCustomizer() {
        return repo -> {
            repo.register(DictionaryTestEnum.class);
            //repo.register(DictionaryTestEnum2.class);
        };
    }

}
