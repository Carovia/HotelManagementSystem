package com.example.hotel.vo;

import java.time.LocalDateTime;

public class CouponVO {
    private Integer id;
    private String description;
    private Integer status;
    private String name;
    private Integer type;

    private String bizReg;
    private double creditRequired;

    private boolean isVIP;

    private Integer hotelId;
    private Integer targetMoney;
    private Integer discountMoney;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double discount;

    private void setIsVIP(boolean isVIP){
        this.isVIP = isVIP;
    }

    private boolean getIsVIP(){
        return this.isVIP;
    }

    public double getCreditRequired() {
        return creditRequired;
    }

    public void setCreditRequired(double targetCredit) {
        this.creditRequired = targetCredit;
    }

    public void setBizReg(String bizReg) {
        this.bizReg = bizReg;
    }

    public String getBizReg() {
        return bizReg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(Integer targetMoney) {
        this.targetMoney = targetMoney;
    }

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

}
