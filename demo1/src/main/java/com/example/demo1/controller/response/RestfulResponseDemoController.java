package com.example.demo1.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.shoulder.autoconfigure.web.WebAdvanceAutoConfiguration;
import org.shoulder.core.dto.response.BaseResult;
import org.shoulder.web.annotation.SkipResponseWrap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 统一 Restful 返回值类型示例
 * 自动包装返回值为标准返回值（包装 json 和 string 类型）
 * 可以通过 {@link SkipResponseWrap} 或返回值继承 {@link BaseResult} 跳过包装
 *
 * @author lym
 * @see SkipResponseWrap 跳过包装
 * @see WebAdvanceAutoConfiguration#restControllerUnionResponseAdvice 框架实现方式
 */
@RestController
@RequestMapping("response")
public class RestfulResponseDemoController {


    /**
     * 正常写法举例，框架不会嵌套包装   <a href="http://localhost:8080/response/0" />
     */
    @GetMapping("0")
    public BaseResult<String> alreadyUnionType() {
        BaseResult<String> response = new BaseResult<>();
        response.setCode("0");
        response.setMsg("success");
        response.setData("data");
        return response;
    }

    /**
     * 字符类型返回值自动包装   <a href="http://localhost:8080/response/1" />
     */
    @GetMapping("1")
    public String autoWrapCase1() {
        return "data";
    }


    /**
     * json 类型返回值自动包装   <a href="http://localhost:8080/response/2" />
     */
    @GetMapping("2")
    public Map<String, User> autoWrapCase2() {
        Map<String, User> map = new HashMap<>(2);
        map.put("1", new User("id1", "name1"));
        map.put("2", new User("id2", "name2"));
        return map;
    }

    /**
     * 跳过包装   <a href="http://localhost:8080/response/3" />
     */
    @SkipResponseWrap
    @GetMapping("3")
    public String autoWrapCase3() {
        return "noWarp";
    }

    /**
     * 自己定义的标准返回值不自动包装   <a href="http://localhost:8080/response/4" />
     */
    @SkipResponseWrap
    @GetMapping("4")
    public CustomizedResponse<String> autoWrapCase4() {
        CustomizedResponse<String> response = new CustomizedResponse<>();
        response.setCode("0");
        response.setMsg("msg");
        response.setData("data");
        response.addArgs("red", "black");
        return response;
    }


    /**
     * 自定义类型
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {
        private String id = UUID.randomUUID().toString();
        private String name = UUID.randomUUID().toString();
    }


    /**
     * 使用者定义的返回值类型
     *
     * @param <T>
     */
    @Getter @Setter public static class CustomizedResponse<T> extends BaseResult<T> {

        /**
         * 在 shoulder 标准之上额外定义了一个通用返回字段
         * 如为了兼容某个前端框架，新增一个专门用于填充 msg 占位符的子弹
         */
        private List<String> args = new LinkedList<>();

        public void addArgs(String... args) {
            this.args.addAll(Arrays.asList(args));
        }
    }

}
