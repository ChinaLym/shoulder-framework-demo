package com.example.demo6.controlloer;

import com.example.demo6.model.MyColorEnum;
import com.example.demo6.model.Owner;
import com.example.demo6.model.Shop;
import org.shoulder.core.context.AppContext;
import org.shoulder.core.context.AppInfo;
import org.shoulder.core.util.ContextUtils;
import org.shoulder.core.util.StringUtils;
import org.shoulder.log.operation.enums.OperationResult;
import org.shoulder.log.operation.enums.TerminalType;
import org.shoulder.log.operation.logger.OperationLogger;
import org.shoulder.log.operation.model.OperationLogDTO;
import org.shoulder.log.operation.model.SystemOperator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;


@RestController
@RequestMapping("oplog")
public class OperationLogDemoController2 {

    private static final String TIP = "log is in your console.";


    /**
     * <a href="http://localhost:8080/oplog/0" />http://localhost:8080/oplog/0</a>
     * <p></p>
     * 不推荐的做法，常规做法：1. 新建OperationLog，2.然后set填充Log，3.手动打印 Log
     */
    @GetMapping("0")
    public String notRecommended() {

        // ... 业务代码 ...

        // 假设这是本次业务被操作的对象(一个商店信息)
        Shop operableBo = createRandomBO();

        // ... 业务代码 ...

        // 操作日志：创建一条日志
        OperationLogDTO opLog = new OperationLogDTO()
                // 填充本次业务修改的对象信息
                .setObjectId(operableBo.getId())
                .setObjectName(operableBo.getName())
                // 为了让目标操作对象类型可以翻译，这里填充多语言 key
                .setObjectType("op.objType.shop.display")

                // 描述是什么操作，补充详情
                .setOperation("op.operation.shop.update.display")
                .setDetailI18nKey("log.actionMessageId.foobar.displayName")
                // 由于详情可以翻译，填充详情中的占位符
                .addDetailItem(operableBo.getBoss().getName())
                .addDetailItem(operableBo.getColor().name())

                // ------------- 下面这些内容重复性较高，框架自动帮你填写！-------------

                // 从request中获取客户端类型
                .setTerminalType(TerminalType.BROWSER)
                // 根据请求取出当前用户、并填充用户信息
                .setUserId(StringUtils.isNotEmpty(AppContext.getUserId())? AppContext.getUserId() : SystemOperator.getInstance().getUserId())
                .setUserName(StringUtils.isNotEmpty(AppContext.getUserName())? AppContext.getUserName() : SystemOperator.getInstance().getUserName())
                .setUserOrgId("UserOrgId-xxx")
                .setUserOrgName("UserOrgName-xxx")

                // app / 租户信息
                .setTenantCode(AppContext.getTenantCode())
                .setAppId(AppInfo.appId())
                .setInstanceId(AppInfo.instanceId())
                .setTenantCode(AppContext.getTenantCode())

                // 记录本次操作结果
                .setResult(OperationResult.SUCCESS)
                .setOperationTime(Instant.now())
                .setEndTime(Instant.now())
                // 可能从调用链中取
                .setTraceId(AppContext.getTraceId());

        // 记录这条日志，关于记录日志的方式也封装了
        // 如记录到 xxx.operation.log 文件（也封装了格式、并支持扩展） / 发送 http 请求到日志中心 / 向 MQ 中发送消息 / 保存到数据库
        ContextUtils.getBean(OperationLogger.class)
                .log(opLog);

        return "ok";
    }


    /**
     * 假设有次业务操作了这么一个 BO
     */
    private Shop createRandomBO() {
        Shop shop = new Shop();
        shop.setId(StringUtils.uuid32());
        shop.setName("shoulder 杂货铺");
        shop.setColor(MyColorEnum.BLUE);
        shop.setAddr("Beijing");
        Owner owner = new Owner("shoulder", 20);
        shop.setBoss(owner);
        shop.setCreateTime(new Date(System.currentTimeMillis()));
        return shop;
    }

}
