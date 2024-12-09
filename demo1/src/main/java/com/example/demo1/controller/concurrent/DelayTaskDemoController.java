package com.example.demo1.controller.concurrent;

import org.shoulder.core.concurrent.PeriodicTask;
import org.shoulder.core.concurrent.Threads;
import org.shoulder.core.log.Logger;
import org.shoulder.core.log.LoggerFactory;
import org.shoulder.web.annotation.SkipResponseWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 延迟任务使用示例
 *
 * @author lym
 */
@SkipResponseWrap // 该类所有方法的返回值将不被包装
@RestController
@RequestMapping("delay")
public class DelayTaskDemoController {

    /**
     * 定义 shoulder 的 logger， 使用注解 {@link SLog} 时则可不写这行代码
     */
    private static final Logger log = LoggerFactory.getLogger(DelayTaskDemoController.class);

    private static final String TIP = "5秒中后，控制台将输出一条日志";

    /**
     * 不建议通过睡眠的方式达到延迟触发的目的，缺点：
     * 1. 资源浪费：在触发前会一直占用一个线程；2.代码冗长；3.线程缺少管理
     * <a href="http://localhost:8080/delay/0" />
     */
    @GetMapping("0")
    public String notRecommended() {
        Thread delay = new Thread(() -> {
            // 5s 后触发
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.warn("I'am a thread delayTask!");
        });
        delay.start();
        return "延迟任务测试（不推荐写法）：" + TIP;
    }

    /**
     * 建议使用 Shoulder 中的延迟任务方式，触发前不会占用线程 <a href="http://localhost:8080/delay/1" />
     * 可用于缓存定期刷新、数据过期、订单关闭等
     */
    @GetMapping("1")
    public String case1() {
        Threads.delay("demo-delayTest", () -> log.warn("I'am a shoulder delayTask"), Duration.ofSeconds(5));
        return "延迟任务测试（Shoulder 一行代码实现）：" + TIP;
    }

    /**
     * 周期性执行，执行5次，每次1s间隔 <a href="http://localhost:8080/delay/2" />
     * 可用于查询进度等，监控当前系统状态、数据上报等
     */
    @GetMapping("2")
    public String case2() {
        AtomicInteger count = new AtomicInteger(0);
        Threads.schedule("demo-scheduleTest",
                () -> log.warn("I'am a shoulder scheduleTask, run time=" + count.addAndGet(1) + "/5"),
                Instant.now(),
                (now, executionTimes) -> executionTimes == 5 ? PeriodicTask.NO_NEED_EXECUTE : now.plus(Duration.ofMillis(1000))
        );

        return "周期性任务测试（Shoulder功能）：每秒执行一次，共执行5次后停止";
    }

    /**
     * 更强大的重试规则：每次执行延迟都不一样  <a href="http://localhost:8080/delay/3" />
     * 可用于优化调用外部系统的重试间隔以降低整体业务耗时等
     */
    @GetMapping("3")
    public String case3() {
        // 个性化周期执行规则：每次执行延迟都不一样，
        Duration[] retryDurations = new Duration[]{
                Duration.ofMillis(0),
                Duration.ofMillis(1000),
                Duration.ofMillis(2000),
                Duration.ofMillis(2000),
                Duration.ofMillis(5000),
                Duration.ofMillis(5000),
                Duration.ofMillis(200),
                Duration.ofMillis(200),
                Duration.ofMillis(200),
                Duration.ofMillis(3000)
        };
        AtomicInteger count = new AtomicInteger(0);
        Threads.schedule("demo-scheduleTest",
                () -> log.warn("I'am a shoulder scheduleTask, run time=" + count.addAndGet(1) + "/10"),
                Instant.now().plus(retryDurations[0]),
                (now, executionTimes) -> executionTimes == retryDurations.length ? PeriodicTask.NO_NEED_EXECUTE : now.plus(retryDurations[executionTimes])
        );

        return "个性化周期性任务测试（Shoulder功能）：重试间隔先变长后变快再变长，共执行10次后停止";
    }

}
