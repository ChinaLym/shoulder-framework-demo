/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.example.demo2.batch;

import org.shoulder.batch.enums.ProcessStatusEnum;
import org.shoulder.batch.model.BatchRecordDetail;
import org.shoulder.batch.repository.BatchRecordDetailPersistentService;
import org.shoulder.batch.spi.BaseImportHandler;
import org.shoulder.log.operation.annotation.OperationLog.Operations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 导入导出功能 学习&测试
 * <p>
 * 处理导入前的数据校验
 *
 * @author lym
 */
@Component
public class TestDataImportHandler extends BaseImportHandler {

    public TestDataImportHandler(BatchRecordDetailPersistentService batchRecordDetailPersistentService) {
        super(DemoBatchConstants.DATA_TYPE_TEST, Operations.IMPORT, batchRecordDetailPersistentService);
    }

    @Override protected List<BatchRecordDetail> updateData(List<BatchRecordDetail> toUpdateList) {
        return toUpdateList.stream()
                .peek(data -> System.out.println("mock UPDATE data: " + data))
                // select for update，check data version，update or fail
                .map(data -> BatchRecordDetail.builder()
                        .index(data.getIndex())
                        .status(ProcessStatusEnum.UPDATE_REPEAT.getCode())
                        .source(data.getSource())
                        .build())
                .collect(Collectors.toList());
    }

    @Override protected List<BatchRecordDetail> saveData(List<BatchRecordDetail> toImportList) {
        return toImportList.stream()
                .peek(data -> System.out.println("mock SAVE data: " + data.getSource()))
                // insert into...
                .map(data -> BatchRecordDetail.builder()
                        .index(data.getIndex())
                        .status(ProcessStatusEnum.SUCCESS.getCode())
                        .source(data.getSource())
                        .build())
                .collect(Collectors.toList());
    }

}