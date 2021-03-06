package com.example.demo1.controller.convert;

import org.shoulder.core.converter.BaseDateConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * 可以自动将日期转为对应的类
 *
 * @author lym
 * @see BaseDateConverter 看其子类查看支持的类型与格式
 */
@RestController
@RequestMapping("date")
public class DateParamConvertController {

    /**
     * 自动将字符串转换为 {@link Date}，如访问 <a href="http://localhost:8080/date/1?date=2020"/>
     * 格式要求：yyyy-MM-dd HH:mm:ss 可以只填前面一段，如 yyyy-MM，'-' 可以替换为 / 如 yyyy/MM/dd
     * 注：输出时，java 默认以 0 时区格式输出
     * 但由于 {@link org.shoulder.core.util.JsonUtils#createObjectMapper} 设置了时区，收到，输出时间时，均以设置的 timeZone 为准
     */
    @GetMapping("1")
    public Date case1(Date date) {
        System.out.println(date);
        return date;
    }

    /**
     * 自动将字符串转换为 {@link LocalDate}，如访问 <a href="http://localhost:8080/date/2?date=2020-1-01"/>
     * 入参格式要求
     * yyyy-MM-dd 或 yyyy/MM/dd
     */
    @GetMapping("2")
    public LocalDate case2(LocalDate date) {
        System.out.println(date);
        return date;
    }

    /**
     * 自动将字符串转换为 {@link LocalDateTime}，如访问 <a href="http://localhost:8080/date/3?date=2020-1-01%2012:20:13"/>
     * 入参格式要求
     * yyyy-MM-dd HH:mm:ss 或 yyyy/MM/dd HH:mm:ss
     */
    @GetMapping("3")
    public LocalDateTime case3(LocalDateTime date) {
        System.out.println(date);
        return date;
    }

    /**
     * 自动将字符串转换为 {@link LocalTime}，如访问 <a href="http://localhost:8080/date/4?date=12:20:13"/>
     * 入参格式要求：HH:mm:ss
     */
    @GetMapping("4")
    public LocalTime case4(LocalTime date) {
        System.out.println(date);
        return date;
    }

}
