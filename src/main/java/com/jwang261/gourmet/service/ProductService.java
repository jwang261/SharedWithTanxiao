package com.jwang261.gourmet.service;


import com.jwang261.gourmet.dao.ProductDao;
import com.jwang261.gourmet.pojo.entity.Product;
import com.jwang261.gourmet.pojo.entity.Restaurant;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface ProductService {


    boolean addProduct(Product product, Restaurant restaurant);

    List<Product> listProduct();

    /**
     *
     * search the products
     * @param keyWord enter by customer
     * @return list of product that satisfy the keyword
     */
    List<Product> searchByKeyWord(String keyWord);

    Product selectByProductId(int productId);

    int getResIdByProductId(int productId);

    List<Product> getAllProducts(int restaurantId);

    void deleteProductById(int productId);

    void updateProduct(Product product);

}
