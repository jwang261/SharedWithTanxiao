package com.jwang261.gourmet.service;


import com.jwang261.gourmet.pojo.entity.*;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface RestaurantService {

    Restaurant login(Restaurant restaurant);

    /**
     *  add new restaurant
     * @return true or false
     */
    boolean insert(Restaurant restaurant);


    /**
     * check if this customer can be created
     * @param restaurant
     * @return true if can be created
     */
    boolean canCreate(Restaurant restaurant);

    Restaurant getRestaurantById(int restaurantId);


    /**
     * get all the order by restaurant phone number.
     * @param PhoneNum
     * @return all the orderdetails as a list
     */
    Map<RestaurantOrder.State, List<RestaurantOrder>> getAllOrders(String PhoneNum);

    List<OrderDetail> getNewOrders();

    List<Comment> getComment(String restaurantPhone);
}
