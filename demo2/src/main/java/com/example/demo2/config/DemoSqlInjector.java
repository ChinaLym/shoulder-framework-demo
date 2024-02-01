package com.example.demo2.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.shoulder.data.mybatis.injector.ShoulderSqlInjector;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义的 mybatis-plus 方法注入-注册
 * 继承 ShoulderSqlInjector：仍然保留 mybatis-plus、shoulder 提供的方法注入
 *
 * @author lym
 */
@Component
public class DemoSqlInjector extends ShoulderSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);

        methodList.add(new OmitById());

        return methodList;
    }

}
