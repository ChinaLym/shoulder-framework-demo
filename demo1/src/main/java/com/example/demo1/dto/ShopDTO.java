package com.example.demo1.dto;

import lombok.Data;

/**
 * 测试 bean 转换（DTO、BO等之间转换）
 * <p>
 * 引入了 Lombok，get/set方法不再需要写
 *
 * @author lym
 */
@Data
public class ShopDTO {

    String id;
    String name;
    String color;
    String address;
    String owner;
    /**
     * 演示目标属性需要根据已有属性计算
     */
    String description;
    String createTime;

}
