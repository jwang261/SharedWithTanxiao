package com.jwang261.gourmet.pojo.entity;

public class CustomerOrderDetail {

    private String productName;

    private double unitPrice;

    private int count;

    private double totalPrice;

    private String restaurantName;

    private String restaurantPhone;

    public CustomerOrderDetail(String productName, double unitPrice, int count, double totalPrice, String restaurantName, String restaurantPhone) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.count = count;
        this.totalPrice = totalPrice;
        this.restaurantName = restaurantName;
        this.restaurantPhone = restaurantPhone;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }
}
