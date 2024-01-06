package com.example.demo1.controller.apidoc;

import org.shoulder.crypto.negotiation.support.Sensitive;
import org.shoulder.web.annotation.SkipResponseWrap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lym
 */
@SkipResponseWrap
@RestController
@RequestMapping("sensitive")
public class SensitiveApiController {


    /**
     * <a href="http://localhost:8080/sensitive/1"/> 最简单的无参数
     * 【但作为服务接口，不应直接调用，需要先进行 ECDH 握手，加密算法支持通过配置替换，如 AES / SM 国密等满足不同国家监管的算法】
     */
    @Sensitive(sensitiveRequest = false, sensitiveResponse = false)
    @GetMapping("1")
    public String mySensitiveTest() {
        return "xxxx";
    }

}
