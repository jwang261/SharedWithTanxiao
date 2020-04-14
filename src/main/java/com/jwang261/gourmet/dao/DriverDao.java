package com.jwang261.gourmet.dao;

import com.jwang261.gourmet.pojo.entity.Driver;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface DriverDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Driver record);

    int insertSelective(Driver record);

    Driver selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Driver record);

    int updateByPrimaryKey(Driver record);

    Driver loginCheck(Driver driver);

    List<Driver> checkRegister();
}