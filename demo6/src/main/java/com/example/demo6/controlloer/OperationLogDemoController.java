package com.example.demo6.controlloer;

import com.example.demo6.model.MyColorEnum;
import com.example.demo6.model.Owner;
import com.example.demo6.model.Shop;
import org.shoulder.core.util.StringUtils;
import org.shoulder.log.operation.annotation.OperationLog;
import org.shoulder.log.operation.annotation.OperationLogParam;
import org.shoulder.log.operation.context.OpLogContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 操作日志示例
 */
@RestController
@RequestMapping("oplog")
public class OperationLogDemoController {

    private static final String TIP = "log is in your console.";

    /**
     * <a href="http://localhost:8080/oplog/1" />
     * 通过 @OperationLog 注解，快速生成一条操作日志
     * 只需加个注解、即可记录操作日志
     */
    @GetMapping("1")
    @OperationLog(operation = "testOpLogAnnotation")
    public String case1() {
        return "ok";
    }

    /**
     * 记录入参 <a href="http://localhost:8080/oplog/2" />http://localhost:8080/oplog/2</a>
     * 在 @OperationLog 注解上，设置 logAllParams = true
     */
    @OperationLog(operation = "testOpLogAnnotation2", logAllParams = true)
    @GetMapping("2")
    public String case5(
            @RequestParam(defaultValue = "test") @OperationLogParam String param0) {
        OpLogContextHolder.getLog().setOperableObject(createRandomBO());
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
