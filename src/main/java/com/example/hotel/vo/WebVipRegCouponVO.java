package com.example.hotel.vo;

public class WebVipRegCouponVO extends CouponVO{
    private String bizReg;
    private double discount;
    private Integer hotelId;

    public Integer getHotelId(){
        return -1;
    }

    public void setDiscount(double discount){
        this.discount = discount;
    }

    public void setBizReg(String bizReg){
        this.bizReg = bizReg;
    }

    public String getBizReg(){
        return this.bizReg;
    }

    public double getDiscount(){
        return this.discount;
    }
}
