package com.jwang261.gourmet.dao;

import com.jwang261.gourmet.pojo.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);

    int selectNewId();

    List<Record> listAll();

    List<Record> selectByCustomerPhoneNum(String phoneNum);

    void changeStateById(@Param("id") Integer recordId,@Param("state") Integer state);

    List<Record> selectRecordByDriverPhone(String driverPhone);

    void updateDriverInfo(@Param("driverName") String driverName, @Param("driverPhone") String driverPhone, @Param("state") Integer state, @Param("recordId") Integer recordId);

    List<Record> selectFinishedByDriverPhone(String driverPhone);

    List<Record> selectUnfinishedByDriverPhone(String driverPhone);

    void updateComment(@Param("recordId") int recordId, @Param("comment") String comment);
}
