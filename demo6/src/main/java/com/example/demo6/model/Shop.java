package com.example.demo6.model;


import lombok.Data;
import org.shoulder.core.model.Operable;

import java.util.Date;

/**
 * 测试 bean 转换（DTO、BO等之间转换）
 *
 * @author lym
 */
@Data
public class Shop implements Operable {

    String id;
    String name;
    /**
     * 枚举转字符串，默认使用其名称
     */
    MyColorEnum color;
    /**
     * 属性名不同需要在 @Mapping 中描述
     */
    String addr;
    Owner boss;
    Date createTime;

    @Override
    public String getObjectId() {
        return id;
    }

    @Override
    public String getObjectName() {
        return name;
    }

    @Override
    public String getObjectType() {
        return getClass().getSimpleName();
    }
}
