package com.example.demo2.controller.base;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo2.entity.UserEntity;
import com.example.demo2.repository.UserMapper;
import com.example.demo2.service.IUserService;
import org.shoulder.web.template.crud.CrudController;
import org.shoulder.web.template.crud.DeleteController;
import org.shoulder.web.template.crud.QueryController;
import org.shoulder.web.template.crud.SaveController;
import org.shoulder.web.template.crud.UpdateController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用 mybatis-plus，基本不需要写基础代码
 * <p>
 * 查询 id 为 1 的用户信息
 * http://localhost:8080/user/1
 * <p>
 * POST http://localhost:8080/user/listAll?limit=20 【注意POST】
 * POST http://localhost:8080/user/page 【注意POST】
 * BODY {}
 *
 * @author lym
 * <p>
 * extends {@link CrudController} 会暴露哪些接口？ 见其继承的接口
 * @see QueryController
 * @see SaveController
 * @see UpdateController
 * @see DeleteController
 */
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


    /**
     * 查询 id 为 1 的用户信息
     * http://localhost:8080/user/1
     */
    @RequestMapping("1")
    public UserEntity get() {
        // 自动根据当前 Controller 泛型注入对应的 IService（IUserService），可通过 service 调用
        return service.getById(1);
    }
    /**
     * 查询 id 为 1 的用户信息
     * http://localhost:8080/user/2
     */
    @RequestMapping("2")
    public void test() {
        // 自动根据当前 Controller 泛型注入对应的 IService（IUserService），可通过 service 调用
        System.out.println("ok");
        return ;
    }

    /**
     * 查询 name 为 input 的用户信息
     * http://localhost:8080/user/getOne?name=Shoulder
     */
    @RequestMapping("getOne")
    public UserEntity getOne(@RequestParam("name") String name) {
        // 已经自动注入 service，即 IUserService
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(UserEntity::getName, name);
        return service.getOne(queryWrapper);
    }

    /**
     * 查询 name 为 input 的用户信息
     * http://localhost:8080/user/getOne?name=Shoulder
     */
    @RequestMapping("logicDelete")
    public String logicDelete(@RequestParam("id") String id) {
        ((UserMapper)service.getBaseMapper()).omitById(id);
        return "";
    }

}
