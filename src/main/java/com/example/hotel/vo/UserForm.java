package com.example.hotel.vo;

import com.example.hotel.enums.UserType;

/**
 * @author huwen
 * @date 2019/3/23
 */
public class UserForm {
    /**
     * 用户邮箱，不可重复
     */
    private String email;
    /**
     * 用户密码
     */
    private String password;

    private String userBDay;

    private UserType userType;

    private String userName;

    private int isVip;

    public int getIsVip(){ return this.isVip; }

    public void setIsVip(int isVip){
        this.isVip = isVip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserBDay(String userBDay){
        this.userBDay = userBDay;
    }

    public String getUserBDay(){
        return userBDay;
    }

    public void setUserType(int s) {
        System.out.println(s);
        if(s==2){
            this.userType= UserType.HotelManager;
        }
        else{
            this.userType=UserType.Marketer;
        }
    }
    public UserType getUsertype() {
        return userType;
    }

    public String getUserName(){
        return this.userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }
}
