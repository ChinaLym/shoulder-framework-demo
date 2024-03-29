package com.example.demo2.repository;

import com.example.demo2.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.shoulder.data.mybatis.template.dao.BaseMapper;

import java.io.Serializable;

/**
 * 持久层
 *
 * @author lym
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    int omitById(Serializable id);
}
