package com.example.demo5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.shoulder.crypto.negotiation.support.Sensitive;

/**
 * 测试加解密 DTO
 *
 * @author lym
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleResult {

    /**
     * 便于debug的标记字段
     */
    String paramMark = "result";

    String text;

    @Sensitive
    String cipher;
}
