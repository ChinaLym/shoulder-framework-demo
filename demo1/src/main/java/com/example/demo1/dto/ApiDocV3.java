package com.example.demo1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;

/**
 * 测试 bean 转换（DTO、BO等之间转换）
 * <p>
 * 引入了 Lombok，get/set方法不再需要写
 *
 * @author lym
 */
@Data
@Schema(description = "接口实体")
public class ApiDocV3 {

    @NotEmpty(message = "name is notnull")
    @Schema(description = "主键", requiredMode = RequiredMode.REQUIRED, example = "d1a27abd-56b7-44c5-838c-05e1a50809f6")
    String id;

    @Schema(description = "名称", requiredMode = RequiredMode.REQUIRED, example = "小明", minLength = 1, maxLength = 10)
    String name;

    @Schema(description = "年龄", example = "20", minimum = "0", maximum = "200")
    Integer age;

    @Schema(description = "地址", example = "广州市", pattern = "^.*$")
    String address;

    @Schema(description = "头像", example = "http://xxx.com/abc.jpg", pattern = "https?://.*")
    String image;

    @Schema(description = "出生日期", requiredMode = RequiredMode.REQUIRED, example = "2020-1-11")
    Date birth;

    @Schema(description = "是否启用", requiredMode = RequiredMode.REQUIRED, example = "true")
    String enable;

}
