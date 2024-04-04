package com.example.demo2.controller.base;

import org.shoulder.data.dal.sequence.dao.SequenceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * id 生成器测试
 *
 * @author lym
 */
@RestController
@RequestMapping("id-generator")
public class SequenceController {

    @Autowired
    private SequenceDao sequenceDAO;

    /**
     * 生成 user 业务下的一个递增id
     * <a href="http://localhost:8080/id-generator/sequence/user">http://localhost:8080/id-generator/sequence/user</a>
     */
    @RequestMapping("sequence/{biz}")
    public Long sequence(@PathVariable("biz") String biz) throws Exception {
        return sequenceDAO.getNextSequence(biz).genNextValue();
    }

    /**
     * 生成 user 业务下的一个递增id，一次请求并发调用100次
     * <a href="http://localhost:8080/id-generator/sequence/multi/user">http://localhost:8080/id-generator/sequence/multi/user</a>
     */
    @RequestMapping("sequence/multi/{biz}")
    public Long sequenceMulti(@PathVariable("biz") String biz) throws Exception {
        new Thread(() -> {
            try {
                get50Times(biz);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return get50Times(biz);
    }

    public Long get50Times(String biz) throws Exception {
        for (int i = 0; i < 49; i++) {
            sequenceDAO.getNextSequence(biz).genNextValue();
        }
        return sequenceDAO.getNextSequence(biz).genNextValue();
    }
}
