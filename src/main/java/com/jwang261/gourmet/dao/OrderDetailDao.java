package com.jwang261.gourmet.dao;

import com.jwang261.gourmet.pojo.entity.OrderDetail;
import com.jwang261.gourmet.pojo.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface OrderDetailDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);

    List<OrderDetail> selectByRecordId(int recordId);

    Record selectRecordByRecordId(int recordId);

    List<OrderDetail> selectByRestaurantPhone(String PhoneNum);

    int selectRecordIdById(Integer id);
}