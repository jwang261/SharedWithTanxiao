package com.jwang261.gourmet.dao;

import com.jwang261.gourmet.pojo.entity.Customer;
import com.jwang261.gourmet.pojo.entity.CustomerOrder;
import com.jwang261.gourmet.pojo.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CustomerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    /**
     *
     * @return new increment id
     */
    int selectNewId();

    /**
     * check if any email and phone number has exist
     * @return all the customers with only emails and phones
     */
    List<Customer> checkRegister();

    Customer loginCheck(Customer customer);



}