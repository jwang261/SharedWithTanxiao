package com.jwang261.gourmet.pojo.entity;

public class Comment {

    private String text;

    private String customerName;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Comment(String customerName, String text){
        this.customerName = customerName;
        this.text = text;
    }
}
