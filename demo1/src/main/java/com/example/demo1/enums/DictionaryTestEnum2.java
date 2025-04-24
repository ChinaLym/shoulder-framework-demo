package com.example.demo1.enums;

import org.shoulder.core.dictionary.model.NameAsIdDictionaryItemEnum;
import org.shoulder.web.template.dictionary.controller.DictionaryEnumController;

/**
 * 让枚举实现 NameAsIdDictionaryEnum 接口，前段就可以调接口查询有哪些枚举啦
 * <p>
 * <a href="http://localhost:8080/ui/dictionaries/page.html">查看默认ui页</a>
 * <p>
 * <a href="http://localhost:8080/api/v1/dictionaries/types/listAll">访问查询所有 api</a>
 * @author lym
 * @see DictionaryEnumController 查看分页查询、模糊搜索等更多接口
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
