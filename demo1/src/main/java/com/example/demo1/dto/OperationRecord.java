package com.example.demo1.dto;

import org.shoulder.core.model.Operable;
import org.shoulder.log.operation.model.sample.OperateRecordDto;

/**
 * 业务操作记录
 *
 * @param <T> 可操作对象
 * @author lym
 */
public class OperationRecord<T extends Operable> extends OperateRecordDto {

    private T data;

    public OperationRecord(T data) {
        super(data);
        this.data = data;
    }
}