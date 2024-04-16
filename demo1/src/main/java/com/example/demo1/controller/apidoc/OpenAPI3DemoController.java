package com.example.demo1.controller.apidoc;

import com.example.demo1.dto.ApiDocV3;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.shoulder.web.annotation.SkipResponseWrap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 介绍如何使用 OpenAPI 3 的注解
 *
 * @author lym
 */
@Tag(name = "api 文档 demo 相关接口")
@SkipResponseWrap
@RestController
@RequestMapping("apidoc")
public class OpenAPI3DemoController {


    /**
     * <a href="http://localhost:8080/apidoc/1"/>
     */
    @Operation(description = "最简单的无参数")
    @GetMapping("1")
    public String case1() {
        return "xxxx";
    }

    @Operation(description = "OpenApi3 带参数")
    @PostMapping(value = "4", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String case4(@RequestBody ApiDocV3 param) {
        return "xxxx";
    }

}
