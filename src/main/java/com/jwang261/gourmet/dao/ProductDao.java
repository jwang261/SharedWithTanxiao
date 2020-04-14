package com.jwang261.gourmet.dao;

import com.jwang261.gourmet.pojo.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface ProductDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKeyWithBLOBs(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> listAll();

    List<Product> selectByProductName(String keyWord);

    int selectResIdByProductId(Integer productId);

    List<Product> selectProductByRestaurantId(Integer restaurantId);

    int changeSellById(Integer productId);

    void edit(Product product);
}