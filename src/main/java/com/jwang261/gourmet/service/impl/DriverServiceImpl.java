package com.jwang261.gourmet.service.impl;

import com.jwang261.gourmet.dao.DriverDao;
import com.jwang261.gourmet.pojo.entity.Driver;
import com.jwang261.gourmet.pojo.entity.DriverRecord;
import com.jwang261.gourmet.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverDao driverDao;


    @Override
    public Driver login(Driver driver) {
        Driver realDriver = driverDao.loginCheck(driver);


        return realDriver;


    }

    @Override
    public boolean insert(Driver driver) {
        int res = driverDao.insert(driver);
        if(res == 1){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean canCreate(Driver driver) {
        List<Driver> list = driverDao.checkRegister();
        for(Driver d : list){
            if(d.getPhoneNum().equals(driver.getPhoneNum())){
                return false;
            }
        }

        return true;

    }
}
