package com.example.demo1.controller.convert;

import com.example.demo1.enums.DictionaryTestEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.Valid;
import lombok.Data;
import org.shoulder.core.converter.ShoulderConversionService;
import org.shoulder.core.exception.BaseRuntimeException;
import org.shoulder.core.log.AppLoggers;
import org.shoulder.web.template.dictionary.convert.AdaptiveDictionaryItemDTODeserializer;
import org.shoulder.web.template.dictionary.convert.DictionaryItemDTODeserializer;
import org.shoulder.web.template.dictionary.dto.DictionaryItemDTO;
import org.shoulder.web.template.dictionary.validation.DictionaryEnumItem;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 可以自动将字符串转为对应的字典
 *
 * @author lym
 */
@RestController
@RequestMapping("dictionaryEnum")
public class DictionaryEnumConvertController {

    private final ConversionService conversionService;

    public DictionaryEnumConvertController(ShoulderConversionService conversionService) {
        this.conversionService = conversionService;
    }

    /**
     * <a href="http://localhost:8080/dictionaryEnum/0?color=BLUE"/> 输入枚举对应的名称可以成功转换
     * <p>
     * 【不推荐的方式】 写太多代码，又乱又不好维护
     */
    @GetMapping("0")
    public DictionaryTestEnum notRecommended(String color) {

        DictionaryTestEnum colorEnum = null;

        DictionaryTestEnum[] enums = DictionaryTestEnum.values();
        for (DictionaryTestEnum e : enums) {
            if (e.name().equals(color)) {
                // 找到了
                colorEnum = e;
                break;
            }
        }
        if (colorEnum != null) {
            System.out.println(colorEnum);
            return colorEnum;
        }
        // 不存在的枚举值
        throw new BaseRuntimeException("0x123246", "参数输入错误");
    }


    /**
     * 【推荐】<a href="http://localhost:8080/dictionaryEnum/1?colorStr=RED"/> 只需一行代码，且支持多种类型自由转换
     */
    @RequestMapping("1")
    public String case1(String colorStr) {
        System.out.println(colorStr);

        DictionaryTestEnum fromStrEnum = conversionService.convert(colorStr, DictionaryTestEnum.class);
        AppLoggers.APP_SERVICE.info("str '{}' convertToEnum = {}", colorStr, fromStrEnum);

        int dictionaryItemOrder = 3;
        DictionaryTestEnum fromIntEnum = conversionService.convert(dictionaryItemOrder, DictionaryTestEnum.class);
        AppLoggers.APP_SERVICE.info("int '{}' convertToEnum = {}", dictionaryItemOrder, fromStrEnum);

        DictionaryTestEnum originEnum = DictionaryTestEnum.BLUE;
        String strFromEnum = conversionService.convert(originEnum, String.class);
        AppLoggers.APP_SERVICE.info("enum '{}' convertToStr = {}", originEnum, strFromEnum);

        Integer intFromEnum = conversionService.convert(originEnum, Integer.class);
        AppLoggers.APP_SERVICE.info("enum '{}' convertToInt = {}", originEnum, intFromEnum);

        DictionaryItemDTO dtoFromEnum = conversionService.convert(originEnum, DictionaryItemDTO.class);
        AppLoggers.APP_SERVICE.info("enum '{}' convertToDto = {}", originEnum, dtoFromEnum);

        DictionaryTestEnum enumFromDto1 = conversionService.convert(dtoFromEnum, DictionaryTestEnum.class);
        AppLoggers.APP_SERVICE.info("dto '{}' convertToEnum = {}", dtoFromEnum, enumFromDto1);

        DictionaryItemDTO dtoOnlyWithCode = new DictionaryItemDTO();
        dtoOnlyWithCode.setCode(DictionaryTestEnum.GREEN.getItemId());
        // 无需 set type，Shoulder 自动判断
        // dtoOnlyWithCode.setDictionaryType("DictionaryTestEnum");
        DictionaryTestEnum enumFromGreenDto = conversionService.convert(dtoOnlyWithCode, DictionaryTestEnum.class);
        AppLoggers.APP_SERVICE.info("dto '{}' convertToEnumWithoutDetail = {}", dtoOnlyWithCode, enumFromGreenDto);

        return "check your console log";
    }

    /**
     * <a href="http://localhost:8080/dictionaryEnum/2?color=RED"/> DictionaryEnumItem 还可以配合 Java JSR303 标准，轻松支持校验入参是否合法
     * <a href="http://localhost:8080/dictionaryEnum/2?color=R1ED">传入不合法值就会失败</a>
     */
    @Validated
    @RequestMapping("2")
    public String case2(@DictionaryEnumItem(DictionaryTestEnum.class) String color) {
        return color;
    }

    /**
     * 进阶能力 即使复杂类型，也可以轻松实现校验
     * <p>
     * 甚至用 DTO 接收 String 入参，非常方便，妈妈再也不用担心和我对接的前端不会传值了
     */
    @Validated
    @PostMapping("3")
    public UserForDictionary case3(@RequestBody UserForDictionary u) {
        return u;
    }

    /**
     * 自定义类型，无论是 String 或是 int 或是 DictionaryItemDTO 都可以正确校验，也能正确接收
     */
    @Data
    public static class UserForDictionary {
        private String id;

        @DictionaryEnumItem(DictionaryTestEnum.class)
        private String favoriteColor1;

        @Valid
        @JsonDeserialize(using = DictionaryTestEnumDeserializer.class)
        @DictionaryEnumItem(DictionaryTestEnum.class)
        private DictionaryItemDTO favoriteColor2;

        @Valid
        @JsonDeserialize(using = DictionaryTestEnumDeserializer2.class)
        @DictionaryEnumItem(DictionaryTestEnum.class)
        private DictionaryItemDTO favoriteColor3;
    }

    /**
     * 这两种 str -> dto 定义效果相同
     */
    public static class DictionaryTestEnumDeserializer extends AdaptiveDictionaryItemDTODeserializer<DictionaryTestEnum> {
    }

    /**
     * 这两种 str -> dto 定义效果相同
     */
    public static class DictionaryTestEnumDeserializer2 extends DictionaryItemDTODeserializer {
        public DictionaryTestEnumDeserializer2() {
            super(DictionaryTestEnum.class);
        }
    }
}
