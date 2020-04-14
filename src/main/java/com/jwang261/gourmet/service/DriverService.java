package com.jwang261.gourmet.service;

import com.jwang261.gourmet.pojo.entity.Driver;
import com.jwang261.gourmet.pojo.entity.DriverRecord;

public interface DriverService {

    /**
     * Customer登录的方法，返回密码和用户名所匹配的用户
     * @param driver
     * @return 正确返回用户对象 错误null
     */
    Driver login(Driver driver);


    /**
     *  add new driver
     * @return true or false
     */
    boolean insert(Driver driver);


    /**
     * check if this driver can be created
     * @param driver
     * @return true if can be created
     */
    boolean canCreate(Driver driver);

}
