package com.example.demo2.controller.base;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo2.entity.UserEntity;
import com.example.demo2.repository.UserMapper;
import com.example.demo2.service.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.shoulder.core.converter.ShoulderConversionService;
import org.shoulder.core.dto.response.ListResult;
import org.shoulder.core.exception.CommonErrorCodeEnum;
import org.shoulder.core.util.AssertUtils;
import org.shoulder.web.template.crud.CrudController;
import org.shoulder.web.template.crud.DeleteController;
import org.shoulder.web.template.crud.QueryController;
import org.shoulder.web.template.crud.SaveController;
import org.shoulder.web.template.crud.UpdateController;
import org.shoulder.web.template.tag.dto.TagDTO;
import org.shoulder.web.template.tag.model.TagEntity;
import org.shoulder.web.template.tag.model.TagMappingEntity;
import org.shoulder.web.template.tag.service.TagCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
 * debug查看所有接口 ContextUtils.getBean(RequestMappingHandlerMapping.class).getHandlerMethods()
 *
 * @author lym
 * <p>
 * extends {@link CrudController} 会暴露哪些接口？ 见其继承的接口
 * @see QueryController
 * @see SaveController
 * @see UpdateController
 * @see DeleteController
 */
@Tag(name = "[DEMO2-UserController]-USER 接口演示", description = "USER 接口演示")
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

    @Autowired
    private TagCoreService tagCoreService;

    public UserController(IUserService service, ShoulderConversionService conversionService) {
        super(service, conversionService);
    }

    /**
     * 查询 id 为 1 的用户信息
     * http://localhost:8080/user/1
     */
    @GetMapping("1")
    public UserEntity get() throws Exception {
        // 自动根据当前 Controller 泛型注入对应的 IService（IUserService），可通过 service 调用
        return service.getById(1);
    }

    /**
     * 查询 id 为 1 的用户信息
     * http://localhost:8080/user/2
     */
    @GetMapping("2")
    public void test() {
        // 自动根据当前 Controller 泛型注入对应的 IService（IUserService），可通过 service 调用
        System.out.println("ok");
        return;
    }

    /**
     * 查询 name 为 input 的用户信息
     * http://localhost:8080/user/getOne?name=Shoulder
     */
    @GetMapping("getOne")
    public UserEntity getOne(@RequestParam("name") String name) {
        // 已经自动注入 service，即 IUserService
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(UserEntity::getName, name);
        return service.getOne(queryWrapper);
    }

    /**
     * 为 id=uid的用户打标签
     * http://localhost:8080/user/attachTag?uid=1&type=age&name=20_30
     * http://localhost:8080/user/attachTag?uid=2&type=age&name=20_30
     */
    @PostMapping("attachTag")
    public TagDTO attachTag(@RequestParam("uid") String uid, @RequestParam("type") String type, @RequestParam("name") String name) {

        UserEntity userInDb = service.getById(uid);
        AssertUtils.notNull(userInDb, CommonErrorCodeEnum.DATA_NOT_EXISTS);
        TagEntity tag = tagCoreService.attachTag("USER", uid, type, name);
        return conversionService.convert(tag, TagDTO.class);
    }

    /**
     * 查询被 tagId 打标的所有用户信息
     * http://localhost:8080/user/searchByTag?tagId=1
     */
    @GetMapping("searchByTagId")
    public ListResult<UserEntity> searchByTagId(@RequestParam("tagId") Long tagId) {
        List<TagMappingEntity> tagMappingList = tagCoreService.queryAllRefIdByRefTypeAndTagId("USER", tagId);
        return queryUserByTagMapping(tagMappingList);
    }

    private ListResult<UserEntity> queryUserByTagMapping(List<TagMappingEntity> tagMappingList) {
        if(CollectionUtils.isEmpty(tagMappingList)) {
            return ListResult.empty();
        }
        List<Long> userIds = tagMappingList.stream()
                .map(TagMappingEntity::getOid)
                .map(Long::valueOf)
                .toList();
        List<UserEntity> userList = service.listByIds(userIds);
        return ListResult.of(userList);
    }

    /**
     * 查询被 tagId 打标的所有用户信息
     * http://localhost:8080/user/searchByTag?tagId=1
     */
    @GetMapping("searchByTagName")
    public ListResult<UserEntity> searchByTagName(@RequestParam("type") String tagType, @RequestParam("name") String tagName) {
        List<TagMappingEntity> tagMappingList = tagCoreService.queryAllRefIdByRefTypeAndTagName("USER", tagType, tagName);
        return queryUserByTagMapping(tagMappingList);
    }

    /**
     * 查询 name 为 input 的用户信息
     * http://localhost:8080/user/getOne?name=Shoulder
     */
    @GetMapping("logicDelete")
    public String logicDelete(@RequestParam("id") String id) {
        ((UserMapper) service.getBaseMapper()).omitById(id);
        return "";
    }

}
