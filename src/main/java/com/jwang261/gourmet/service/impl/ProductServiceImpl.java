package com.jwang261.gourmet.service.impl;

import com.jwang261.gourmet.dao.ProductDao;
import com.jwang261.gourmet.pojo.entity.Product;
import com.jwang261.gourmet.pojo.entity.Restaurant;
import com.jwang261.gourmet.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    ProductDao productDao;


    @Override
    public boolean addProduct(Product product, Restaurant restaurant) {
        product.setRestaurantId(restaurant.getId());
        product.setRestaurantName(restaurant.getRestaurantName());
        product.setIsSelling(true);


        //查的网上 没有验证正确性
        int res = productDao.insert(product);


        if (res == 0) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public List<Product> listProduct() {
        List<Product> list = productDao.listAll();
        return list;

    }

    @Override
    public List<Product> searchByKeyWord(String keyWord) {

        List<Product> list = productDao.selectByProductName(keyWord);

        return list;
    }

    @Override
    public Product selectByProductId(int productId) {
        Product product = productDao.selectByPrimaryKey(productId);

        return product;
    }

    @Override
    public int getResIdByProductId(int productId) {
        return productDao.selectResIdByProductId(productId);
    }

    @Override
    public List<Product> getAllProducts(int restaurantId) {

        return new ArrayList<>(productDao.selectProductByRestaurantId(restaurantId));
    }

    @Override
    public void deleteProductById(int productId) {
        productDao.changeSellById(productId);
    }

    @Override
    public void updateProduct(Product product) {
//        if(product.getImg() == null){
//            product.setImg("");
//        }
        productDao.edit(product);


    }
}
