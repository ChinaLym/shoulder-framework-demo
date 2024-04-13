package com.example.demo3.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo3.entity.UserEntity;
import com.example.demo3.service.IUserService;
import org.shoulder.core.converter.ShoulderConversionService;
import org.shoulder.web.annotation.SkipResponseWrap;
import org.shoulder.web.template.crud.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用 mybatis-plus，基本不需要写基础代码
 *
 * @author lym
 */
@SkipResponseWrap // 跳过包装方便演示
@RestController
@RequestMapping("user")
public class UserController extends CrudController<
        IUserService,
        UserEntity,
        Long,
        UserEntity,
        UserEntity,
        UserEntity,
        UserEntity> {


    public UserController(IUserService service, ShoulderConversionService conversionService) {
        super(service, conversionService);
    }

}
