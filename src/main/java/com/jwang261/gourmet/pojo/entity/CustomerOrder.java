package com.jwang261.gourmet.pojo.entity;


public class CustomerOrder {

    private String orderDate;

    private double totalPrice;

    private String driverPhoneNum;

    private String driverName;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    private int recordId;

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDriverPhoneNum() {
        return driverPhoneNum;
    }

    public void setDriverPhoneNum(String driverPhoneNum) {
        this.driverPhoneNum = driverPhoneNum;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    private State state;

    private String stateStr;

    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
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
