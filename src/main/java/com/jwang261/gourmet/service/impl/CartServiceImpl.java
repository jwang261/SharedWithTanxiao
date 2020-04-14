package com.jwang261.gourmet.service.impl;

import com.jwang261.gourmet.dao.CartDao;
import com.jwang261.gourmet.dao.CustomerDao;
import com.jwang261.gourmet.pojo.entity.Cart;
import com.jwang261.gourmet.pojo.entity.Customer;
import com.jwang261.gourmet.pojo.entity.Product;
import com.jwang261.gourmet.service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;


@Service
public class CartServiceImpl implements CartService {

    @Resource
    CartDao cartDao;


    @Override
    public void addCart(int cartId){
        cartDao.addSame(cartId);

    }

    @Override
    public void addNewCart(Product product, int customerId) {
        Cart cart = new Cart();
        cart.setProductId(product.getId());
        cart.setProductName(product.getProductName());
        cart.setPrice(product.getPrice());
        cart.setCustomerId(customerId);
        cart.setCount(1);
        cartDao.insert(cart);
    }


    @Override
    public List<Cart> getCartByCustomerId(int customerId) {
        List<Cart> list = cartDao.selectCartByCustomerId(customerId);
        return list;
    }

    @Override
    public int deleteCart(int productId) {
        int res = cartDao.deleteByProductId(productId);

        if(res <= 0){
            try {
                throw new SQLException();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return res;



    }

    @Override
    public double checkOut(int customerId){
        List<Cart> carts = cartDao.selectCartByCustomerId(customerId);
        double totalPrice = 0;
        for(Cart c : carts){
            totalPrice += c.getCount() * c.getPrice();
        }



        return totalPrice;


    }

    @Override
    public int getProductIdByCartId(int cartId) {

        return cartDao.selectProductIdByCartId(cartId);
    }

    @Override
    public void cleanCartByCustomerId(int customerId) {
        int res = cartDao.deleteAllByCustomerId(customerId);
        if(res <= 0){
            try{
                throw new SQLException();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
