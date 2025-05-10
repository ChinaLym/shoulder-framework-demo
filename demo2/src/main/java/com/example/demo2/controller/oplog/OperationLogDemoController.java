package com.example.demo2.controller.oplog;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.shoulder.log.operation.annotation.OperationLog;
import org.shoulder.web.annotation.SkipResponseWrap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志使用示例----写入DB版本，可以看到代码完全一样，只需要设置 log.operation.logger.type=jdbc 便自动将底层Logger改为写数据库【无感方便】
 * <p>
 * 通过 @OperationLog、OpLogContextHolder —— 两个类完成操作日志
 *
 * @author lym
 */
@SkipResponseWrap // 该类所有方法的返回值将不被包装
@RestController
@RequestMapping("oplog")
@Tag(name = "[DEMO2-OperationLogDemoController]-OperationLog 使用演示", description = "操作日志使用演示")
public class OperationLogDemoController {

    /**
     * 只需加个 @OperationLog 注解、即可记录操作日志，<a href="http://localhost:8080/oplog/1">点击这里测试</a>
     * <p>
     * 若想在 DB 查看日志，可以访问  <a href="http://localhost:8080/h2-console">H2 内置的 console 页面</a>
     * 用户名/密码: shoulder / shoulder
     * <p>
     * 在配置启用 shoulder.web.ext.oplog 后，还可通过 <a href="http://localhost:8080/ui/oplogs/page.html">Shoulder 内置的 可视化页面</a> 更方便的查看
     */
    @GetMapping("1")
    @Operation(summary = "日志测试")
    @OperationLog(operation = "testOpLogAnnotation")
    public String case1() {
        return "log has insertInTo your database.";
    }


}
