package com.jwang261.gourmet.pojo.entity;

import java.util.Date;

public class RestaurantOrder {

    private State state;

    private String stateStr;

    private double unitPrice;

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    private int orderDetailId;

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    private double totalPrice;

    private int count;

    private String productName;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    private Date orderTime;

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public enum State{

        // state = 1
        PlACED,
        // state = 2
        PREPARING,
        // state = 3
        ONTHEWAY,
        // state = 4
        CONFIRMED,
        // state = 5
        COMMENT,
        // state = 0
        CANCELED,
        // state = -1
        REQUESTREFUND,
        // state = -2
        REQUESTOK

    }
}
