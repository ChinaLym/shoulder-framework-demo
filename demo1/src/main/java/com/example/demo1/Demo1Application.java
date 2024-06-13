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
 * <li>运行 Demo1Application（默认为本机8080端口）</li>
 * <ol>
 * <li>参考 README.MD 进入 controller 目录（已按照功能分类）</li>
 * <li>点击方法上的超链接（IDE支持），即可快速查看效果</li>
 * </ol>
 * </p>
 * 方式二-接口：<a href="http://localhost:8080/swagger-ui/index.html">进入 http://localhost:8080/swagger-ui/index.html 通过 swagger 快速上手</a>
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
        System.out.println("demo1 翻译测试（Demo1Application#onApplicationEvent）-错误码翻译：CommonErrorCodeEnum.AUTH_401_EXPIRED ->" + ContextUtils.getBean(Translator.class).getMessage(CommonErrorCodeEnum.AUTH_401_EXPIRED));
        System.out.println("上下文加载完毕");
    }

}
