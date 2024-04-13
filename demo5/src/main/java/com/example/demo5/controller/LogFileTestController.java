package com.example.demo5.controller;

import cn.hutool.core.util.ReflectUtil;
import org.shoulder.core.log.AppLoggers;
import org.shoulder.core.log.Logger;
import org.shoulder.core.log.ShoulderLoggers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 测试日志打印分文件
 *
 * http://localhost:8080/log/test
 *
 * @author lym
 */
@RestController
public class LogFileTestController {

    @RequestMapping("log/test")
    public String testLog(){
        Map<String, Field> fieldMap = ReflectUtil.getFieldMap(ShoulderLoggers.class);
        fieldMap.forEach((name, field) -> {
            Logger logger = null;
            try {
                logger = ((Logger)field.get(ShoulderLoggers.class));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            logger.info(name + " Test");
            logger.warn(name + " Test");
            logger.error(name + " Test");
        });
        Map<String, Field> fieldMap2 = ReflectUtil.getFieldMap(AppLoggers.class);
        fieldMap2.forEach((name, field) -> {
            Logger logger = null;
            try {
                logger = ((Logger)field.get(AppLoggers.class));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            logger.info(name + " Test");
            logger.warn(name + " Test");
            logger.error(name + " Test");
        });
        return "ok";
    }

}
