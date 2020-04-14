package com.jwang261.gourmet.service;

import com.jwang261.gourmet.dao.CartDao;
import com.jwang261.gourmet.pojo.entity.Cart;
import com.jwang261.gourmet.pojo.entity.Customer;
import com.jwang261.gourmet.pojo.entity.Product;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public interface CartService {

    void addNewCart(Product product, int customerId);

    void addCart(int cartId);

    List<Cart> getCartByCustomerId(int customerId);

    int deleteCart(int productId);

    double checkOut(int customerId);

    int getProductIdByCartId(int cartId);


    void cleanCartByCustomerId(int customerId);
}
