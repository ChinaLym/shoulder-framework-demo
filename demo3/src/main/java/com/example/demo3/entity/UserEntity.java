package com.example.demo3.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shoulder.data.mybatis.template.entity.BaseEntity;

/**
 * 用户实体，举例：主键为 uuid
 * 自动生成主键、创建者，创建时间，修改者，修改时间
 *
 * @author lym
 * @see VariableRecordEntity 提供了一些基础的字段：id、createTime、creator、updateTime、modifier
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString(callSuper = true)
@TableName("user_info")
public class UserEntity extends BaseEntity<Long> {

    @TableField(value = "username", fill = FieldFill.INSERT)
    private String username;

    @TableField(value = "phone_num", fill = FieldFill.INSERT)
    private String phoneNum;

    @TableField(value = "password", fill = FieldFill.INSERT)
    private String password;

    @TableField(value = "name", fill = FieldFill.INSERT)
    private String name;

    @TableField(value = "age", fill = FieldFill.INSERT)
    private Integer age;

    @TableField(value = "email", fill = FieldFill.INSERT)
    private String email;

}
