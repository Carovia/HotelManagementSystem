package com.example.hotel.vo;

public class WebMemberCreditCouponVO extends CouponVO{
    private double creditRequired;
    private double discount;
    private Integer hotelId;

    public Integer getHotelId(){
        return -1;
    }

    public double getCreditRequired(){
        return this.creditRequired;
    }

    public double getDiscount(){
        return this.discount;
    }

    public void setCreditRequired(double creditRequired){
        this.creditRequired = creditRequired;
    }

    public void setDiscount(double discount){
        this.discount = discount;
    }

}
