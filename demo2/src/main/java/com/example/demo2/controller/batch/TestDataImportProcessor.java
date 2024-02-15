/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.example.demo2.controller.batch;

import org.shoulder.batch.enums.ProcessStatusEnum;
import org.shoulder.batch.model.BatchDataSlice;
import org.shoulder.batch.model.BatchRecordDetail;
import org.shoulder.batch.spi.BatchTaskSliceHandler;
import org.shoulder.log.operation.annotation.OperationLog.Operations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 导入导出功能 学习&测试
 *
 * 如果只需要导入导出，不需要异步处理，只看该类即可
 *
 * @author lym
 */
@Component
public class TestDataImportProcessor implements BatchTaskSliceHandler {

    @Override public boolean support(String dataType, String operationType) {
        return "testBatchDataType".equals(dataType) && Operations.UPLOAD_AND_VALIDATE.equals(operationType);
    }

    @Override public List<BatchRecordDetail> handle(BatchDataSlice task) {
        System.out.println("mockValidate, dataType=" + task.getDataType()
                           + ", operation=" + task.getOperationType()
                           + ", total=" + task.getBatchList().size());
        return task.getBatchList().stream()
                .peek(data -> System.out.println("mock save data: " + data))
                .map(data -> new BatchRecordDetail(data.getIndex(), ProcessStatusEnum.SUCCESS.getCode()))
                .collect(Collectors.toList());
    }

}