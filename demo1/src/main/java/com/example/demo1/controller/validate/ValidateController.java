package com.example.demo1.controller.validate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.shoulder.core.dto.response.BaseResult;
import org.shoulder.validate.annotation.Enum;
import org.shoulder.validate.groups.Update;
import org.shoulder.web.validate.ValidateRuleEndPoint;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * JSR303 注解式校验
 * <a href="http://localhost:8080/" />
 * <p>
 *     另外可通过以下 地址 获取特定接口的校验规则【这里仅支持一次获取单个接口的规则，避免黑客获取该app有哪些接口,或遍历破解】
 * <p>
 * 动态校验规则-测试1 <a href="http://localhost:8080/api/v1/validate/rule?method=GET&uri=/validate/jsr/1" />
 * 动态校验规则-测试2 <a href="http://localhost:8080/api/v1/validate/rule/validate/jsr/1?method=GET" />
 * <p>
 * 动态校验规则-测试3 <a href="http://localhost:8080/api/v1/validate/rule?method=GET&uri=/validate/jsr/2" />
 * 动态校验规则-测试4 <a href="http://localhost:8080/api/v1/validate/rule?method=GETuri=/validate/jsr/3" />
 *
 * @author lym
 * @see ValidateRuleEndPoint 动态获取校验规则
 */
@Validated
@RestController
@RequestMapping("validate")
public class ValidateController {

    /**
     * shoulder 写法举例
     * http://localhost:8080/validate/1?value=abc
     */
    @GetMapping("1")
    public String caseEnum(@Enum(enums = {"abc", "def"}) String value) {
        System.out.println(value);
        return "ok";
    }


    /**
     * 正常写法举例
     * http://localhost:8080/validate/jsr/1?value=x
     */
    @GetMapping("jsr/1")
    public String caseJsr(@Validated @Valid @NotNull @NotBlank String value) {
        System.out.println(value);
        return "ok";
    }

    /**
     * POST http://localhost:8080/validate/jsr/2?value=x
     * 动态校验规则 <a href="http://localhost:8080/api/v1/validate/rule?method=get&uri=/validate/jsr/2" />
     */
    @PostMapping("jsr/2")
    public String caseJsr2(@Validated @Valid @NotBlank User user) {
        System.out.println(user);
        return "ok";
    }

    /**
     * POST http://localhost:8080/validate/jsr/3?value=x
     * 动态校验规则 <a href="http://localhost:8080/api/v1/validate/rule?method=get&uri=/validate/jsr/3" />
     */
    @PostMapping("jsr/3")
    @Validated(Update.class)
    public String caseJsr3(@Validated @NotBlank User user) {
        System.out.println(user);
        return "ok";
    }

    /**
     * 自定义类型
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {
        @NotEmpty
        private String id;

        @Min(0)
        @Max(66)
        private Integer age;

        @Pattern(regexp = "\\w+")
        private String name;

        @Pattern(regexp = "\\d+", groups = Update.class)
        private String name2;
    }

}
