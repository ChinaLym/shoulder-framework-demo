package com.example.demo1.controller.log;

import lombok.extern.slf4j.Slf4j;
import org.shoulder.http.interceptor.RestTemplateColorfulLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 自动记录 http 接口日志示例
 * 自动打印入参出参，不需要手动打印。
 *
 * @author lym
 * @see RestTemplateColorfulLogInterceptor
 */
@Slf4j
@RestController
@RequestMapping("httpLog")
public class HttpLogDemoController {

    private static final String TIP = "去控制台查看日志吧.";

    /**
     * 使用 http
     *
     * @see org.springframework.boot.actuate.autoconfigure.cloudfoundry.servlet.SkipSslVerificationHttpRequestFactory https 需要认证管理器或跳过验证
     */
    private static final String DEMO_URL = "http://github.com/chinaLym";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 无参数 http://localhost:8080/httpLog/1
     */
    @GetMapping("1")
    public String simple() {
        // 注意啦，调用时间的颜色是会随着响应改变的，github可能慢点~
        restTemplate.getForObject(DEMO_URL, String.class);
        return TIP;
    }

    /**
     * http://localhost:8080/httpLog/2
     */
    @GetMapping("2")
    public String common(String param) {
        // 响应时间颜色会根据响应时间长短变化
        log.debug("准备发起请求。。。");
        restTemplate.getForObject("http://gitee.com/ChinaLym/shoulder-framework", String.class);
        log.debug("请求结束。。。");
        return "";
    }

    /**
     * 抛异常 http://localhost:8080/httpLog/3
     */
    @GetMapping("3")
    public String onError() {
        restTemplate.getForObject("http://fakerurl", String.class);
        return TIP;
    }

}
