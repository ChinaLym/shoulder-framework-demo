package com.example.demo1.enums;

import com.example.demo1.config.DictionaryEnumConfiguration;
import org.shoulder.core.dictionary.model.NameAsIdDictionaryItemEnum;

/**
 * 让枚举实现 NameAsIdDictionaryEnum 接口，前段就可以调接口查询有哪些枚举啦
 * @see DictionaryEnumConfiguration#dictionaryEnumRepositoryCustomizer()
 *
 * @author lym
 */
public enum DictionaryTestEnum2 implements NameAsIdDictionaryItemEnum<DictionaryTestEnum2> {

    /**
     * pc
     */
    WEB,
    /**
     * 支持移动设备浏览器的 H5
     */
    WAP,
    /**
     * 移动 app
     */
    APP,
    /**
     * 轻 app
     */
    MINI_APP,
    ;

    @Override
    public String getName() {
        return name();
    }

    @Override
    public Integer getDisplayOrder() {
        return ordinal();
    }

}
