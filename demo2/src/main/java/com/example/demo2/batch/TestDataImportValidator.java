package com.example.demo2.batch;

import lombok.SneakyThrows;
import org.shoulder.batch.enums.BatchDetailResultStatusEnum;
import org.shoulder.batch.model.BatchDataSlice;
import org.shoulder.batch.model.BatchRecordDetail;
import org.shoulder.batch.spi.BatchTaskSliceHandler;
import org.shoulder.log.operation.annotation.OperationLog.Operations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 导入导出功能 学习&测试
 * <p>
 * 处理数据导入
 *
 * @author lym
 */
@Component
public class TestDataImportValidator implements BatchTaskSliceHandler {

    @Override
    public boolean support(String dataType, String operationType) {
        return DemoBatchConstants.DATA_TYPE_TEST.equals(dataType)
                && Operations.UPLOAD_AND_VALIDATE.equals(operationType);
    }

    @SneakyThrows
    @Override
    public List<BatchRecordDetail> handle(BatchDataSlice task) {
        System.out.println("mockValidate, dataType=" + task.getDataType()
                + ", operation=" + task.getOperationType()
                + ", total=" + task.getBatchList().size());
        // 模拟校验比较耗时，校验字段、业务校验、查数据库、调接口等...
        Thread.sleep(1000);

        return task.getBatchList().stream()
                .peek(data -> System.out.println("mock validate data: " + data))
                .map(data -> new BatchRecordDetail(data.getIndex(), data.serialize(), BatchDetailResultStatusEnum.SUCCESS.getCode()))
                .collect(Collectors.toList());
    }

}