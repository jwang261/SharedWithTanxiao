package com.jwang261.gourmet.service.impl;

import com.jwang261.gourmet.dao.RecordDao;
import com.jwang261.gourmet.pojo.entity.Record;
import com.jwang261.gourmet.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordDao recordDao;

    @Override
    public int insertNewRecord(Record record) {

        recordDao.insert(record);

        int newId = recordDao.selectNewId();

        return newId;
    }

    @Override
    public List<Record> listAll() {

        return new ArrayList<>(recordDao.listAll());
    }

    @Override
    public void changeState(int recordId, int state) {
        recordDao.changeStateById(recordId, state);
    }

    @Override
    public List<Record> getUnfinishedRecords(String driverPhone) {
        List<Record> records = recordDao.selectUnfinishedByDriverPhone(driverPhone);
        return records;

    }

    @Override
    public List<Record> getFinishedRecords(String driverPhone) {
        return new ArrayList<>(recordDao.selectFinishedByDriverPhone(driverPhone));
    }

    @Override
    public void updateDriverInfo(String driverName, String driverPhone, int state, int recordId) {
        recordDao.updateDriverInfo(driverName,driverPhone,state,recordId);
    }

    @Override
    public Record getRecordById(int recordId) {
        return recordDao.selectByPrimaryKey(recordId);
    }

    @Override
    public void writeComment(int recordId, String comment) {
        recordDao.updateComment(recordId,comment);
    }


}
