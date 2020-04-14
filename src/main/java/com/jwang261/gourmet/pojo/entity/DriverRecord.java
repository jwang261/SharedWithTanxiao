package com.jwang261.gourmet.pojo.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class DriverRecord {

    private String customerPhoneNum;

    private String customerAddress;

    private int recordId;

    private Map<String, String> resNamePhones;

    public String getCustomerPhoneNum() {
        return customerPhoneNum;
    }

    public void setCustomerPhoneNum(String customerPhoneNum) {
        this.customerPhoneNum = customerPhoneNum;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    private Date orderTime;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    private double totalPrice;

    private String restaurantAddress;


    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Map<String, String> getResNamePhones() {
        return resNamePhones;
    }

    public void setResNamePhones(Map<String, String> resNamePhones) {
        this.resNamePhones = resNamePhones;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public DriverRecord(int recordId, Date orderTime, String customerPhoneNum,
            String customerAddress, double totalPrice){
            this.recordId = recordId;
            this.orderTime = orderTime;
            this.customerPhoneNum = customerPhoneNum;
            this.customerAddress = customerAddress;
            this.totalPrice = totalPrice;
    }
}
