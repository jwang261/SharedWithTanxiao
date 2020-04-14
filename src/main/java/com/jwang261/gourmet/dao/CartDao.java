package com.jwang261.gourmet.dao;

import com.jwang261.gourmet.pojo.entity.Cart;
import com.jwang261.gourmet.pojo.entity.Customer;
import com.jwang261.gourmet.pojo.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CartDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    List<Cart> selectCartByCustomerId(int customerId);

    void addSame(int cartId);

    int deleteByProductId(Integer productId);

    int deleteAllByCustomerId(Integer customerId);

    int selectProductIdByCartId(int cartId);
}