package com.example.demo2.batch;

import jakarta.validation.constraints.NotNull;
import org.shoulder.batch.spi.ExportDataQueryFactory;
import org.shoulder.core.dto.request.PageQuery;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 数据导出
 * 用于查询数据
 *
 * @author lym
 */
@Component
public class TestExportDataQueryFactory implements ExportDataQueryFactory {

    @Override public boolean support(@NotNull String businessType, PageQuery<Map> exportCondition) {
        return DemoBatchConstants.DATA_TYPE_TEST.equalsIgnoreCase(businessType);
    }

    @Override public List<Supplier<List<Map<String, String>>>> createQuerySuppliers(@NotNull String businessType,
                                                                                    PageQuery<Map> exportCondition) {
        int mockCountTotalFromDb = 100;
        int mockPageSize = 10;
        int slices = mockCountTotalFromDb / mockPageSize;

        List<Supplier<List<Map<String, String>>>> resultList = new ArrayList<>(slices);
        for (int i = 0; i < slices; i++) {
            resultList.add(() -> mockQueryFromDb(slices, mockPageSize));
        }
        return resultList;
    }

    List<Map<String, String>> mockQueryFromDb(int pageNo, int pageSize) {
        List<Map<String, String>> dataList = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            dataList.add(Map.of(
                    "First", "first-" + pageNo,
                    "Second", "second-" + pageSize
            ));
        }
        return dataList;
    }

}