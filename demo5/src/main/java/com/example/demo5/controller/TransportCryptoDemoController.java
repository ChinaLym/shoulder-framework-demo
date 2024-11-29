package com.example.demo5.controller;

import com.example.demo5.dto.SimpleParam;
import com.example.demo5.dto.SimpleResult;
import lombok.extern.slf4j.Slf4j;
import org.shoulder.core.util.JsonUtils;
import org.shoulder.crypto.asymmetric.exception.AsymmetricCryptoException;
import org.shoulder.crypto.asymmetric.impl.DefaultAsymmetricCipher;
import org.shoulder.crypto.negotiation.support.SecurityRestTemplate;
import org.shoulder.crypto.negotiation.support.Sensitive;
import org.shoulder.crypto.negotiation.support.client.SensitiveRequestEncryptMessageConverter;
import org.shoulder.crypto.negotiation.support.server.SensitiveRequestDecryptAdvance;
import org.shoulder.crypto.negotiation.support.server.SensitiveRequestDecryptHandlerInterceptor;
import org.shoulder.crypto.negotiation.support.server.SensitiveResponseEncryptAdvice;
import org.shoulder.web.annotation.SkipResponseWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver;

/**
 * 接口加密
 *
 * @author lym
 * @see DefaultAsymmetricCipher#ecc256
 */
@Slf4j
@SkipResponseWrap
@RestController
@RequestMapping("simple")
public class TransportCryptoDemoController {

    private final RestTemplate restTemplate;

    public TransportCryptoDemoController(SecurityRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "coding_client_like_me", method = {RequestMethod.GET, RequestMethod.POST})
    public void coding_client_like_me() {
        HttpEntity<SimpleParam> httpEntity = new HttpEntity<>(
                new SimpleParam("text1", "text2", "cipherText"), null);
        restTemplate.exchange("http://localhost:80/simple/coding_server_like_me", HttpMethod.POST,
                httpEntity, new ParameterizedTypeReference<SimpleResult>() {});

    }

    @Sensitive // <-----------
    @RequestMapping(value = "coding_server_like_me", method = {RequestMethod.GET, RequestMethod.POST})
    public SimpleResult coding_server_like_me(@RequestBody(required = false) SimpleParam param) {
        System.out.println(param);
        SimpleResult result = new SimpleResult();
        result.setCipher("shoulder");
        result.setText("666");
        return result;
    }


}
