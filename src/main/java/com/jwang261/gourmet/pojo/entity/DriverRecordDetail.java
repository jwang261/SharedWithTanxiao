package com.jwang261.gourmet.pojo.entity;

public class DriverRecordDetail {

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private String restaurantName;
    private String restaurantPhone;
    private String productName;
    private int count;

    public DriverRecordDetail(String restaurantName, String restaurantPhone, String productName, int count){
        this.restaurantName = restaurantName;
        this.restaurantPhone = restaurantPhone;
        this.count = count;
        this.productName = productName;
    }

}
