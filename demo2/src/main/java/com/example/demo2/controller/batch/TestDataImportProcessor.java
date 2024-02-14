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
 * @author lym
 */
@Component
public class TestDataImportProcessor implements BatchTaskSliceHandler {

    @Override public boolean support(String dataType, String operationType) {
        return "testBatchDataType".equals(dataType) && Operations.UPLOAD_AND_VALIDATE.equals(operationType);
    }

    @Override public List<BatchRecordDetail> handle(BatchDataSlice task) {
        return task.getBatchList().stream().map(data -> {
            System.out.println("mock save data: " + data);
            return BatchRecordDetail.builder()
                    .index(data.getIndex())
                    .status(ProcessStatusEnum.VALIDATE_SUCCESS.getCode())
                    .build();
        }).collect(Collectors.toList());
    }

}