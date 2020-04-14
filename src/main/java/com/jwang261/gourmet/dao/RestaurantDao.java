package com.jwang261.gourmet.dao;

import com.jwang261.gourmet.pojo.entity.Restaurant;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RestaurantDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Restaurant record);

    int insertSelective(Restaurant record);

    Restaurant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Restaurant record);

    int updateByPrimaryKey(Restaurant record);

    Restaurant loginCheck(Restaurant restaurant);

    //Before sign up, check already existing phoneNums
    List<Restaurant> checkRegister();

}