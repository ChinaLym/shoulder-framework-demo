package com.example.demo1.controller.convert;

import com.example.demo1.enums.MyColorEnum;
import lombok.Data;
import org.shoulder.core.converter.EnumConverter;
import org.shoulder.core.exception.BaseRuntimeException;
import org.springframework.web.bind.annotation.*;

/**
 * 可以自动将字符串转为对应的枚举
 * <p>
 * 实现与扩展：
 *
 * @author lym
 * @see org.shoulder.core.converter.EnumConverter#convert 默认使用名称匹配，可以自行继承来扩展 shoulder 提供的匹配方式
 * 注入 Spring Boot 的 EnumConverterFactory 可替换 shoulder 的实现
 */
@RestController
@RequestMapping("enum")
public class EnumConvertController {

    /**
     * <a href="http://localhost:8080/enum/0?color=RED"/> 输入枚举对应的名称可以成功转换
     * <p>
     * 【不推荐的方式】 写太多代码，又乱又不好维护
     */
    @GetMapping("0")
    public MyColorEnum notRecommended(String color) {

        MyColorEnum colorEnum = null;

        MyColorEnum[] enums = MyColorEnum.values();
        for (MyColorEnum e : enums) {
            if (e.name().equals(color)) {
                // 找到了
                colorEnum = e;
                break;
            }
        }
        if (colorEnum != null) {
            System.out.println(colorEnum);
            return colorEnum;
        }
        // 不存在的枚举值
        throw new BaseRuntimeException("0x123246", "参数输入错误");
    }


    /**
     * <a href="http://localhost:8080/enum/1?color=RED"/> 输入枚举对应的名称可以成功转换
     * <p>
     * 默认采用名称严格匹配，也可以实现自己的转换器，实现方式参见 {@link EnumConverter}
     */
    @GetMapping("1")
    public MyColorEnum case1(MyColorEnum color) {
        System.out.println(color);
        return color;
    }

    /**
     * 接收多个参数包含枚举  <a href="http://localhost:8080/enum/1?id=123&favoriteColor=RED"/>
     */
    @GetMapping("2")
    public User case2(User user) {
        System.out.println(user);
        return user;
    }

    /**
     * 接收请求体包含枚举类型
     */
    @PostMapping("3")
    public User case3(@RequestBody User user) {
        System.out.println(user);
        return user;
    }

    /**
     * 自定义类型
     */
    @Data
    public static class User {
        private String id;
        private MyColorEnum favoriteColor;
    }

}
