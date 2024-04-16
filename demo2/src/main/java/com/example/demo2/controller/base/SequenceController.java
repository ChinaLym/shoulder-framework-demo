package com.example.demo2.controller.base;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.shoulder.data.sequence.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * id 生成器测试
 *
 * @author lym
 */
@RestController
@RequestMapping("id-generator")
@Tag(name = "[DEMO2-SequenceController]-id/sequence序列 使用演示", description = "id/sequence序列-使用演示")
public class SequenceController {

    @Autowired
    private SequenceGenerator sequenceGenerator;

    /**
     * 生成 user 业务下的一个递增id
     * <a href="http://localhost:8080/id-generator/sequence/user">http://localhost:8080/id-generator/sequence/user</a>
     */
    @GetMapping("sequence/{biz}")
    public Long sequence(@PathVariable("biz") String biz) {
        return sequenceGenerator.next(biz);
    }

    /**
     * 生成 user 业务下的100个递增id
     * <a href="http://localhost:8080/id-generator/sequence/multi/user">http://localhost:8080/id-generator/sequence/multi/user</a>
     */
    @GetMapping("sequence/multi/{biz}")
    public List<Long> sequenceMulti(@PathVariable("biz") String biz) {
        return sequenceGenerator.next(biz, 100);
    }
    /**
     * 生成 user 业务下的一个递增id，一次请求并发调用100次
     * <a href="http://localhost:8080/id-generator/sequence/concurrent/user">http://localhost:8080/id-generator/sequence/concurrent/user</a>
     */
    @GetMapping("sequence/concurrent/{biz}")
    public Long sequenceConcurrent(@PathVariable("biz") String biz) {
        new Thread(() -> get50Times(biz));
        return get50Times(biz);
    }

    public Long get50Times(String biz) {
        for (int i = 0; i < 49; i++) {
            sequenceGenerator.next(biz);
        }
        return sequenceGenerator.next(biz);
    }
}
