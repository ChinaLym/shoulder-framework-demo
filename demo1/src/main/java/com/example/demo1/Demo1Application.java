package com.example.demo1;

import org.shoulder.core.exception.CommonErrorCodeEnum;
import org.shoulder.core.i18.Translator;
import org.shoulder.core.util.ContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * shoulder-framework 实例工程
 * TIP：运行后（默认为本机8080端口），进入 controller 目录（已按照功能分类），点击方法上的超链接（IDE支持），即可快速查看效果
 *
 * @author lym
 */
@EnableScheduling
@SpringBootApplication
public class Demo1Application implements ApplicationListener<ContextRefreshedEvent> {
    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("demo1 翻译测试-错误码翻译：" + ContextUtils.getBean(Translator.class).getMessage(CommonErrorCodeEnum.AUTH_401_EXPIRED));
        System.out.println("上下文加载完毕");
    }

}
