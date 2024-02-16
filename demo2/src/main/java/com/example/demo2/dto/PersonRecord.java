package com.example.demo2.dto;

import lombok.Data;
import lombok.ToString;
import org.shoulder.batch.spi.DataItem;
import org.shoulder.core.util.JsonUtils;

import java.io.Serializable;

/**
 * 员工
 *
 * @author lym
 */
@Data
@ToString
public class PersonRecord implements DataItem, Serializable {

    private String name;

    private String sex;

    private Integer age;

    private int index;

    @Override public String serialize() {
        return JsonUtils.toJson(this);
    }
}