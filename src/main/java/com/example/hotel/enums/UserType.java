package com.example.hotel.enums;

/**
 * @Author: chenyizong
 * @Date: 2020-02-29
 */
public enum UserType {
    //用户
    Client("1"),
    //酒店管理人员
    HotelManager("2"),
    //网站管理人员
    Admin("3"),
    //网站营销人员
    Marketer("4");
    private String value;

    UserType(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return value;
    }

}
