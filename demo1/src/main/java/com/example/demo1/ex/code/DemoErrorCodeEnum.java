package com.example.demo1.ex.code;

import org.shoulder.core.context.AppInfo;
import org.shoulder.core.exception.BaseRuntimeException;
import org.shoulder.core.exception.CommonErrorCodeEnum;
import org.shoulder.core.exception.ErrorCode;
import org.shoulder.core.util.AssertUtils;
import org.slf4j.event.Level;
import org.springframework.http.HttpStatus;


/**
 * 工程中常用错误，可以按照业务划分，一个模块一个错误码枚举
 * <p>
 * 框架中已经定义了一些常用错误 {@link CommonErrorCodeEnum}
 * <p>
 * 这里以活动报名相关业务举例
 *
 * @author lym
 */
public enum DemoErrorCodeEnum implements ErrorCode {

    /**
     * @desc 报名者年龄不符合要求
     * 转为异常抛出时，记录 info 级别日志，若接口中抛出未捕获，返回客户端 400 状态码
     */
    AGE_OUT_OF_RANGE(10001, "age out of range", Level.INFO, HttpStatus.BAD_REQUEST),

    /**
     * @desc 错误描述：第三方服务失败
     * @sug 检查日志
     * <p>
     * 若接口中抛出未捕获，返回客户端 500 状态码
     */
    SIGN_UP_FAIL(10002, "third service error"),

    ;

    private String code;

    private String message;

    private Level logLevel;

    private HttpStatus httpStatus;

    DemoErrorCodeEnum(String code) {
        this.code = code;
    }

    /**
     * 转为异常抛出时，默认记录 warn 日志，返回 500 状态码
     */
    DemoErrorCodeEnum(long code, String message) {
        this(code, message, DEFAULT_LOG_LEVEL, DEFAULT_HTTP_STATUS_CODE);
    }

    DemoErrorCodeEnum(long code, String message, HttpStatus httpStatus) {
        this(code, message, DEFAULT_LOG_LEVEL, httpStatus);
    }

    DemoErrorCodeEnum(long code, String message, Level logLevel) {
        this(code, message, logLevel, DEFAULT_HTTP_STATUS_CODE);
    }

    DemoErrorCodeEnum(long code, String message, Level logLevel, HttpStatus httpStatus) {
        String hex = Long.toHexString(code);
        AssertUtils.isTrue(hex.length() <= 4, CommonErrorCodeEnum.CODING, "错误码长度不正确");
        this.code = AppInfo.errorCodePrefix() + "0".repeat(Math.max(0, 4 - hex.length())) + hex;
        this.message = message;
        this.logLevel = logLevel;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Level getLogLevel() {
        return logLevel;
    }

    /**
     * @deprecated 废弃
     */
    @Override
    public HttpStatus getHttpStatusCode() {
        return httpStatus;
    }

    // 提供了两个生成异常的方法，可选择使用

    @Override
    public BaseRuntimeException toException(Object... args) {
        return new BaseRuntimeException(this, args);
    }

    @Override
    public BaseRuntimeException toException(Throwable t, Object... args) {
        return new BaseRuntimeException(this, t, args);
    }

}
