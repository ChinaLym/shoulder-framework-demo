package com.example.demo2.controller.batch;

import com.example.demo2.config.MySimpleTaskEnum;
import com.example.demo2.entity.UserEntity;
import com.example.demo2.service.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PreDestroy;
import org.shoulder.core.concurrent.Threads;
import org.shoulder.core.converter.ShoulderConversionService;
import org.shoulder.core.exception.CommonErrorCodeEnum;
import org.shoulder.core.lock.LockInfo;
import org.shoulder.core.lock.ReentrantServerLock;
import org.shoulder.core.lock.ServerLock;
import org.shoulder.core.lock.ServerLockAcquireProxy;
import org.shoulder.core.lock.impl.JdbcLock;
import org.shoulder.core.lock.impl.JdkLock;
import org.shoulder.core.util.AssertUtils;
import org.shoulder.web.annotation.SkipResponseWrap;
import org.shoulder.web.template.crud.BaseControllerImpl;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Shoulder-Batch Dynamic Activity Task
 *
 * @author lym
 */
@SkipResponseWrap // 跳过包装方便演示
@Controller
@RequestMapping("batch")
@Tag(name = "[DEMO2-BatchActivityController]-BatchActivity 使用演示", description = "BatchActivity 使用演示")
public class BatchActivityController {


    /**
     * <a href="http://localhost:8080/ui/activities/page.html?progressId=_shoulderMockAndTest&activityId=MySimpleTaskEnum"></a>
     */
    @GetMapping("show_ui")
    public String show_ui() {
        return "redirect:/ui/activities/page.html?progressId=_shoulderMockAndTest&activityId=MySimpleTaskEnum";
    }


    /**
     * 最简单示例
     * <a href="http://localhost:8080/batch/demo1"></a>
     */
    @GetMapping("demo1")
    public String demo1() {
        String progressId = UUID.randomUUID().toString();
        MySimpleTaskEnum.TASK1.start(progressId);
        Threads.delay("batch_demo1", () -> {
            MySimpleTaskEnum.TASK1.finish(progressId);
            try {

                MySimpleTaskEnum.TASK2.start(progressId);
                Thread.sleep(2000);
                MySimpleTaskEnum.TASK2.finish(progressId);

                MySimpleTaskEnum.TASK3.start(progressId);
                Thread.sleep(1000);
                MySimpleTaskEnum.TASK3.finish(progressId);

                MySimpleTaskEnum.TASK4.start(progressId);
                Thread.sleep(1500);
                MySimpleTaskEnum.TASK4.finish(progressId);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, Duration.ofSeconds(3));

        return "redirect:/ui/activities/page.html?progressId=" + progressId + "&activityId=MySimpleTaskEnum";
    }


    /**
     * 引入进度条
     * <a href="http://localhost:8080/batch/demo2"></a>
     */
    @GetMapping("demo2")
    public String demo2() {
        String progressId = UUID.randomUUID().toString();
        MySimpleTaskEnum.TASK1.startOneStageTask(progressId);
        Threads.delay("batch_demo2", () -> {
            MySimpleTaskEnum.TASK1.addSuccess(progressId);
            try {

                MySimpleTaskEnum.TASK2.setTotalAndStart(progressId, 2);
                Thread.sleep(500);
                MySimpleTaskEnum.TASK2.addSuccess(progressId);
                Thread.sleep(500);
                MySimpleTaskEnum.TASK2.addSuccess(progressId);

                MySimpleTaskEnum.TASK3.setTotalAndStart(progressId, 3);
                Thread.sleep(300);
                MySimpleTaskEnum.TASK3.addSuccess(progressId);
                Thread.sleep(300);
                MySimpleTaskEnum.TASK3.addSuccess(progressId);
                Thread.sleep(300);
                MySimpleTaskEnum.TASK3.addSuccess(progressId);

                MySimpleTaskEnum.TASK4.startOneStageTask(progressId);
                Thread.sleep(500);
                MySimpleTaskEnum.TASK4.addSuccess(progressId);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, Duration.ofSeconds(3));

        return "redirect:/ui/activities/page.html?progressId=" + progressId + "&activityId=MySimpleTaskEnum";
    }


    /**
     * <a href="http://localhost:8080/ui/activities/page.html?progressId=_shoulderMockAndTest&activityId=MyTaskEnum">复杂任务一样处理，示例</a>
     */
    @GetMapping("show_ui2")
    public String show_ui2() {
        return "redirect:/ui/activities/page.html?progressId=_shoulderMockAndTest&activityId=MyTaskEnum";
    }
}
