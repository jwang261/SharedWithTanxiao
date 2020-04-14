package com.jwang261.gourmet.service;

import com.jwang261.gourmet.pojo.entity.Customer;
import com.jwang261.gourmet.pojo.entity.CustomerOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Customer Service level
 */
public interface CustomerService {

    /**
     * Customer登录的方法，返回密码和用户名所匹配的用户
     * @param customer
     * @return 正确返回用户对象 错误null
     */
    Customer login(Customer customer);

    /**
     *  add new customer
     * @return new auto-increment id
     */
    int insert(Customer customer);


    /**
     * check if this customer can be created
     * @param customer
     * @return true if can be created
     */
    boolean canCreate(Customer customer);


    /**
     * select all the customer order by customer id
     * @param phoneNum
     * @return CustomerOrder
     */
    Map<CustomerOrder.State,List<CustomerOrder>> getCustomerOrders(String phoneNum);
}
