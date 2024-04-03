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
     * http://localhost:8080/id-generator/sequence/user
     */
    @RequestMapping("sequence/{biz}")
    public Long sequence(@PathVariable("biz") String biz) throws Exception {
        return sequenceDAO.getNextSequence(biz).genNextValue();
    }

}
