package com.example.hotel.vo;

/**
 * @Author: chenyizong
 * @Date: 2020-03-03
 */
public class UserInfoVO {
    private String password;
    private String userName;
    private String phoneNumber;
    private String userBDay;
    private Integer userId;
    private String email;
    private int isVip;

    public int getIsVip(){
        return this.isVip;
    }

    public void setIsVip(int isVip){
        this.isVip = isVip;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUserBDay(String userBDay){
        this.userBDay = userBDay;
    }

    public String getUserBDay(){
        return userBDay;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }
    public Integer getuserId() {
        return userId;
    }

    public void setuserId(String password) {
        this.userId = userId;
    }

}
