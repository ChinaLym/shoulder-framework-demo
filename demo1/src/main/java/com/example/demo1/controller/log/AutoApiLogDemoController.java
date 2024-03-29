package com.example.demo1.controller.log;

import org.shoulder.autoconfigure.web.WebAdvanceAutoConfiguration;
import org.shoulder.core.exception.BaseRuntimeException;
import org.shoulder.core.util.JsonUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自动记录 api 接口日志示例
 * 自动打印入参出参，不需要手动打印。
 *
 * @author lym
 * @see WebAdvanceAutoConfiguration#restControllerColorfulLogAspect
 * @see WebAdvanceAutoConfiguration#restControllerJsonLogAspect
 */
@RestController
@RequestMapping("apilog")
public class AutoApiLogDemoController {

    private static final String TIP = "去控制台查看日志吧.";

    /**
     * 无参数 http://localhost:8080/apilog/1
     */
    @GetMapping("1")
    public String simple() {
        return TIP;
    }

    /**
     * 带参数 http://localhost:8080/apilog/2?param1=value1&param2=2
     */
    @GetMapping("2")
    public String common(String param1, Integer param2) {
        return TIP;
    }

    /**
     * 抛异常 http://localhost:8080/apilog/3
     */
    @GetMapping("3")
    public String notRecommended() {
        throw new BaseRuntimeException("test ex");
    }
    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        a.add("123");
        a.add("a");
        a.add("b");
        System.out.println(a);
        System.out.println(Arrays.toString(a.toArray()));
        System.out.println(JsonUtils.toJson(a));
    }
}
