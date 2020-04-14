package com.jwang261.gourmet.pojo.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * order_type
 * @author  Jwang261
 */
public class OrderType implements Serializable {
    private Integer customerId;

    private Integer chinese;

    private Integer dessert;

    private Integer western;

    private Integer indianFood;

    private Integer japanese;

    private Integer drink;

    private Integer korean;

    private static final long serialVersionUID = 1L;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getChinese() {
        return chinese;
    }

    public void setChinese(Integer chinese) {
        this.chinese = chinese;
    }

    public Integer getDessert() {
        return dessert;
    }

    public void setDessert(Integer dessert) {
        this.dessert = dessert;
    }

    public Integer getWestern() {
        return western;
    }

    public void setWestern(Integer western) {
        this.western = western;
    }

    public Integer getIndianFood() {
        return indianFood;
    }

    public void setIndianFood(Integer indianFood) {
        this.indianFood = indianFood;
    }

    public Integer getJapanese() {
        return japanese;
    }

    public void setJapanese(Integer japanese) {
        this.japanese = japanese;
    }

    public Integer getDrink() {
        return drink;
    }

    public void setDrink(Integer drink) {
        this.drink = drink;
    }

    public Integer getKorean() {
        return korean;
    }

    public void setKorean(Integer korean) {
        this.korean = korean;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OrderType other = (OrderType) that;
        return (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getChinese() == null ? other.getChinese() == null : this.getChinese().equals(other.getChinese()))
            && (this.getDessert() == null ? other.getDessert() == null : this.getDessert().equals(other.getDessert()))
            && (this.getWestern() == null ? other.getWestern() == null : this.getWestern().equals(other.getWestern()))
            && (this.getIndianFood() == null ? other.getIndianFood() == null : this.getIndianFood().equals(other.getIndianFood()))
            && (this.getJapanese() == null ? other.getJapanese() == null : this.getJapanese().equals(other.getJapanese()))
            && (this.getDrink() == null ? other.getDrink() == null : this.getDrink().equals(other.getDrink()))
            && (this.getKorean() == null ? other.getKorean() == null : this.getKorean().equals(other.getKorean()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getChinese() == null) ? 0 : getChinese().hashCode());
        result = prime * result + ((getDessert() == null) ? 0 : getDessert().hashCode());
        result = prime * result + ((getWestern() == null) ? 0 : getWestern().hashCode());
        result = prime * result + ((getIndianFood() == null) ? 0 : getIndianFood().hashCode());
        result = prime * result + ((getJapanese() == null) ? 0 : getJapanese().hashCode());
        result = prime * result + ((getDrink() == null) ? 0 : getDrink().hashCode());
        result = prime * result + ((getKorean() == null) ? 0 : getKorean().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", customerId=").append(customerId);
        sb.append(", chinese=").append(chinese);
        sb.append(", dessert=").append(dessert);
        sb.append(", western=").append(western);
        sb.append(", indianFood=").append(indianFood);
        sb.append(", japanese=").append(japanese);
        sb.append(", drink=").append(drink);
        sb.append(", korean=").append(korean);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}