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
 * @see DelayTaskDispatcher 阻塞式实现类
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
     * 不建议通过睡眠的方式达到延迟触发的目的，该方式创建出来，在触发前会一直占用一个线程
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
        return TIP;
    }

    /**
     * 建议使用 Shoulder 中的方式，触发前不会占用线程 <a href="http://localhost:8080/delay/1" />
     */
    @GetMapping("1")
    public String case1() {
        Threads.delay("demo-delayTest", () -> log.warn("I'am a shoulder delayTask"), Duration.ofSeconds(5));
        return TIP;
    }

    /**
     * 周期性执行，执行5次，每次1s间隔 <a href="http://localhost:8080/delay/2" />
     */
    @GetMapping("2")
    public String case2() {
        AtomicInteger count = new AtomicInteger(0);
        Threads.schedule("demo-scheduleTest",
                () -> log.warn("I'am a shoulder scheduleTask, run time=" + count.addAndGet(1) + "/5"),
                Instant.now(),
                (now, executionTimes) -> executionTimes == 5 ? PeriodicTask.NO_NEED_EXECUTE : now.plus(Duration.ofMillis(1000))
        );

        return TIP;
    }

}
