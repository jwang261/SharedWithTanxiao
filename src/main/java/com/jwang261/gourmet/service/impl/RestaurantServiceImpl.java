package com.jwang261.gourmet.service.impl;

import com.jwang261.gourmet.controller.pages.back.ProductController;
import com.jwang261.gourmet.dao.OrderDetailDao;
import com.jwang261.gourmet.dao.ProductDao;
import com.jwang261.gourmet.dao.RecordDao;
import com.jwang261.gourmet.dao.RestaurantDao;
import com.jwang261.gourmet.pojo.entity.*;
import com.jwang261.gourmet.service.RestaurantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Resource
    RestaurantDao restaurantDao;

    @Resource
    ProductDao productDao;

    @Resource
    RecordDao recordDao;

    @Resource
    OrderDetailDao orderDetailDao;

    @Override
    public Restaurant login(Restaurant restaurant) {
        Restaurant realRestaurant = restaurantDao.loginCheck(restaurant);

        return realRestaurant;

    }

    @Override
    public boolean insert(Restaurant restaurant) {
        int res = restaurantDao.insert(restaurant);
        if(res == 1){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean canCreate(Restaurant restaurant) {
        List<Restaurant> list = restaurantDao.checkRegister();
        for(Restaurant r : list){
            if(r.getPhoneNum().equals(restaurant.getPhoneNum())){
                return false;
            }
        }

        return true;

    }

    @Override
    public Restaurant getRestaurantById(int restaurantId){

        return restaurantDao.selectByPrimaryKey(restaurantId);
    }

    @Override
    public Map<RestaurantOrder.State, List<RestaurantOrder>> getAllOrders(String PhoneNum) {
        Map<RestaurantOrder.State, List<RestaurantOrder>> map = new HashMap<>();

        List<OrderDetail> orderDetails = orderDetailDao.selectByRestaurantPhone(PhoneNum);

        for(OrderDetail o : orderDetails){
            Record r = recordDao.selectByPrimaryKey(o.getRecordId());
            Product p = productDao.selectByPrimaryKey(o.getProductId());

            insertRestaurantOrder(map, o, r, p);

        }


        return map;

    }

    //未用
    @Override
    public List<OrderDetail> getNewOrders() {
        return null;
    }

    @Override
    public List<Comment> getComment(String restaurantPhone) {
        Set<Integer> set = new HashSet<>();

        List<OrderDetail> list = orderDetailDao.selectByRestaurantPhone(restaurantPhone);
        List<Comment> comments = new ArrayList<>();

        for(OrderDetail od : list){

            if(set.contains(recordDao.selectByPrimaryKey(od.getRecordId()).getId())){

                continue;

            }else{
                Record record = recordDao.selectByPrimaryKey(od.getRecordId());
                set.add(record.getId());
                if(record.getComment() == null || "\\s+".equals(record.getComment())){
                    continue;
                }else{
                    comments.add(new Comment(record.getCustomerName(), record.getComment()));

                }

            }


        }


        return comments;
    }


    public void insertRestaurantOrder(Map<RestaurantOrder.State,List<RestaurantOrder>> map, OrderDetail od, Record r, Product p){

        RestaurantOrder ro = new RestaurantOrder();

        ro.setCount(od.getCount());
        ro.setUnitPrice(od.getPrice());
        ro.setTotalPrice(od.getPrice() * od.getCount());
        ro.setProductName(p.getProductName());
        ro.setOrderDetailId(od.getId());
        ro.setOrderTime(r.getOrderDate());
        RestaurantOrder.State rs = this.generateState(r.getState(), ro);

        ro.setState(rs);

        if(map.containsKey(rs)){
            map.get(rs).add(ro);
        }else{
            List<RestaurantOrder> list = new ArrayList<>();
            list.add(ro);

            map.put(rs,list);
        }

    }

    public RestaurantOrder.State generateState(int state, RestaurantOrder ro){
        switch (state){
            case 0:
                ro.setStateStr("Canceled");
                return RestaurantOrder.State.CANCELED;

            case 1:
                ro.setStateStr("Wait for confirm");
                return RestaurantOrder.State.PlACED;

            case 2:
                ro.setStateStr("Preparing");
                return RestaurantOrder.State.PREPARING;

            case 3:
                ro.setStateStr("On the way");
                return RestaurantOrder.State.ONTHEWAY;

            case 4:
                ro.setStateStr("Finished");
                return RestaurantOrder.State.CONFIRMED;

            case 5:
                ro.setStateStr("Commented by the customer");
                return RestaurantOrder.State.COMMENT;

            case -1:
                ro.setStateStr("Customer is requesting for refund");
                return RestaurantOrder.State.REQUESTREFUND;

            case -2:
                ro.setStateStr("Agree to refund");
                return RestaurantOrder.State.REQUESTOK;
               


        }
        return RestaurantOrder.State.CONFIRMED;

    }
}
