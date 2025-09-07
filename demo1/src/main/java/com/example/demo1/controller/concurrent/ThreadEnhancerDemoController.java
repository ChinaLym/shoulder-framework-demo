package com.example.demo1.controller.concurrent;

import org.shoulder.core.concurrent.AsyncTask;
import org.shoulder.core.concurrent.Threads;
import org.shoulder.monitor.concurrent.MonitorableThreadPool;
import org.shoulder.web.annotation.SkipResponseWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/**
 * 线程增强器测试
 *
 * @author lym
 */
@SkipResponseWrap // 该类所有方法的返回值将不被包装
@RestController
@RequestMapping("threadEnhancer")
public class ThreadEnhancerDemoController {

    @Autowired
    private MonitorableThreadPool executorService;

    /**
     * 不建议通过 newThread 方式创建线程，不方便确认 JVM 线程状态以及排查问题。
     * <a href="http://localhost:8080/threadEnhancer/0" />
     */
    @GetMapping("0")
    public String notRecommended() {
        SomeBusinessOperation runnable = new SomeBusinessOperation("case0", 5000, false);
        Thread t = new Thread(runnable);
        t.start();
        return "ok";
    }

    /**
     * 建议使用 Shoulder 中的方式，通过线程池运行，方便确认线程状态，如果报错会兜底打印一层日志，并支持使用全局线程增强器 <a href="http://localhost:8080/threadEnhancer/1" />
     */
    @GetMapping("1")
    public String case1() {
        SomeBusinessOperation runnable = new SomeBusinessOperation("case1", 5000, true);
        Threads.execute("case1", runnable);
        return "ok";
    }


    /**
     * Shoulder 还支持回调 <a href="http://localhost:8080/threadEnhancer/2" />
     * 设置预期执行时间，达到预期执行时间后线程还没结束，或者抛出异常时，可以触发回调方法
     */
    @GetMapping("2")
    public String case2() {
        SomeBusinessOperation runnable = new SomeBusinessOperation("case2", 8000, false);
        Threads.execute(AsyncTask.create(
                "case2-timeout", runnable, myExceptionCallBack,
                // 超时时间 4s
                Instant.now().plus(Duration.ofSeconds(4)), executorService
        ));
        return "ok";
    }

    /**
     * Shoulder 还支持回调 <a href="http://localhost:8080/threadEnhancer/3" />
     * 抛出异常时，也会触发回调方法
     */
    @GetMapping("3")
    public String case3() {
        SomeBusinessOperation runnable = new SomeBusinessOperation("case3", 100, true);
        Threads.execute(AsyncTask.create(
                "case3-exception", runnable, myExceptionCallBack,
                // 超时时间 2s
                Instant.now().plus(Duration.ofSeconds(2)), executorService
        ));
        return "ok";
    }

    Consumer<Threads.TaskInfo> myExceptionCallBack = t -> {
        System.out.println(t.taskName() + " 执行不符合预期，提交任务时间：" + t.taskSubmitTime() + " 开始运行的时间：" + t.runStartTimeRef().get() + "检测时间：" + t.detectTime()
                + " 执行线程：" + Optional.ofNullable(t.threadRef().get()).map(Thread::getName).orElse("[任务未开始或已结束]") + " 执行异常=" + t.exceptionRef().get() + " 结束时间=" + t.runEndTimeRef().get());
        //  这里可以选择取消线程池队列中排队的任务，甚至可以中断超时没执行完的任务，且无需担心中断时任务结束等并发问题
//        t.cancelTask(true);

        // 这里可以获取对应的异常，再做统一处理
        // t.exceptionRef().get().xxx

        // 甚至分析任务提交和执行时间差异，以判断线程池orCPU是否不足；分析任务执行耗时等
    };

    public static class SomeBusinessOperation implements Runnable {
        String tipName;
        long sleepTime;
        boolean throwEx;

        public SomeBusinessOperation(String tipName, long sleepTime, boolean throwEx) {
            this.tipName = tipName;
            this.sleepTime = sleepTime;
            this.throwEx = throwEx;
        }

        @Override
        public void run() {
            System.out.println("这是 " + tipName + " 开始 ~~~~~~~~~");
            if (throwEx) {
                // 模拟抛出预期外未处理的异常
                throw new RuntimeException("mock throw noHandle Exception");
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                // 预期内业务异常
                throw new RuntimeException(e);
            }
            System.out.println("这是 " + tipName + " 结束 ~~~~~~~~~");
        }
    }

}
