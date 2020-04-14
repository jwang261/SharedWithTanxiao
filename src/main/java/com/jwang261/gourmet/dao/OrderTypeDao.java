package com.jwang261.gourmet.dao;

import com.jwang261.gourmet.pojo.entity.OrderType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OrderTypeDao {
    int deleteByPrimaryKey(Integer customerId);

    int insert(OrderType record);

    int insertSelective(OrderType record);

    OrderType selectByPrimaryKey(Integer customerId);

    int updateByPrimaryKeySelective(OrderType record);

    int updateByPrimaryKey(OrderType record);
}