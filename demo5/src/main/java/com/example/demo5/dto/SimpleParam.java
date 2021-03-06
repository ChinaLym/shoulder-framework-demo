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
public class SimpleParam {

    /**
     * 便于debug的标记字段
     */
    String paramMark = "param";

    String text;

    @Sensitive
    String cipher;

}
