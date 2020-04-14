package com.jwang261.gourmet.service;

import com.jwang261.gourmet.pojo.entity.Record;

import java.util.List;

public interface RecordService {

    int insertNewRecord(Record record);

    List<Record> listAll();

    void changeState(int recordId, int state);

    List<Record> getUnfinishedRecords(String driverPhone);

    List<Record> getFinishedRecords(String driverPhone);

    void updateDriverInfo(String driverName, String driverPhone, int state, int recordId);

    Record getRecordById(int recordId);

    void writeComment(int recordId, String comment);
}
