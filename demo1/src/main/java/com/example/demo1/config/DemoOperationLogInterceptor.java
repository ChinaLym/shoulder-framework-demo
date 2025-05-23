package com.example.demo1.config;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.shoulder.core.model.Operable;
import org.shoulder.log.operation.logger.OperationLoggerInterceptor;
import org.shoulder.log.operation.model.OperationLogDTO;
import org.shoulder.log.operation.model.sample.MultiOperableDecorator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作日志拦截器
 * <p>
 * 如果开启了异步记录，这里的所有方法都是异步的~ 放心查数据库吧
 *
 * @author lym
 */
@Slf4j
@Component
public class DemoOperationLogInterceptor implements OperationLoggerInterceptor {

    //@Autowired
    //JdbcTemplate jdbcTemplate;
    // *************************** 批量场景的拦截 ******************************

    /**
     * 在组装批量日志前，可以根据自己的业务调整组装方式
     */
    @Override
    public List<? extends Operable> beforeAssembleBatchLogs(OperationLogDTO template, List<? extends Operable> operableList) {

        // 批量导入业务中，被操作对象可能有多个，假设操作了 100 条，这100个对象记录在一条日志中，可能导致某些字段超出最大长度限制，而记录 100 条又不利于查看，这里可以对其进行自定义分隔条数和批次
        if ("batchImportXxx".equals(template.getOperation())) {
            // 5条操作日志合成一条操作日志，
            List<? extends List<? extends Operable>> partOperableList = CollectionUtil.split(operableList, 5);
            List<Operable> result = new ArrayList<>(partOperableList.size());
            partOperableList.forEach(operables -> result.add(new MultiOperableDecorator(operables)));
            return result;
        }
        return operableList;
    }


    // *************************** 可以拦截每条日志记录 ***************************

    @Override
    public boolean beforeLog(OperationLogDTO opLog) {
        log.info("记录操作日志前回调钩子： 可在这里进行校验格式 / 继续填充一些值。可在 " + getClass().getSimpleName() + "#beforeLog 关闭该输出~ ");

        // --------------- 这里可以做的事情举例 --------------------
        if (StringUtils.isEmpty(opLog.getObjectName())) {
            // 删除操作为例：删除用户接口的参数可能只有 userId 而没有 userName
            // 但希望在展示操作日志时，显示被删除的用户昵称、真实姓名，因此需要再查一次数据库补充

            //fillMoreInfoFromDB(opLog);

            // 若为false，则不打印日志
            return false;
        }
        return true;
    }

    @Override
    public void afterLog(OperationLogDTO opLog) {
        log.info("记录操作日志后回调钩子：可在 " + getClass().getSimpleName() + "#afterLog 关闭该输出~ ");

        // 这里可以做的事情举例：
        // 
        // 1. 清理一些由于 beforeValidate 引入的变量或者垃圾

        // 2. 统计各个业务操作的频次，看看哪些业务比较热门 / 受欢迎 / 重要

        // 3. 审计调用参数

        // 4. ...

    }

    // shoulder 支持操作日志异步处理，无需担心会阻塞主处理请求
    private void fillMoreInfoFromDB(OperationLogDTO opLog) {
        String tableName = null;
        String objectId = null;
        String objectName = null;
        switch (opLog.getObjectType()) {
            case "USER":
                tableName = "user";
                objectId = "user_id";
                objectName = "user_name";
                break;

            case "USER_GROUP":
                tableName = "user_group";
                objectId = "user_group_id";
                objectName = "user_group_name";

            default:
                break;
        }
        if (tableName == null) {
            return;
        }
        // 拼接sql
        String sql = "select " + objectId + " as objectId," + objectName + "as objectName" +
                " from " + tableName +
                " where " + objectId + "=" + opLog.getObjectId();
        // 从数据库里动态查出来，填充一下
        // OperableObject dbInfo = jdbcTemplate.queryForObject(sql, OperableObject.class);
        // opLog.setObjectName(dbInfo.getObjectName());

    }

}
