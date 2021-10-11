package com.example.hotel.po;

public class Comments {
    private Integer id;
    private Integer userId;
    private String contents;
    private Integer hotelId;

    public void setId(Integer id){
        this.id = id;
    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }

    public void setContents(String contents){
        this.contents = contents;
    }

    public void setHotelId(Integer hotelId){
        this.hotelId = hotelId;
    }

    public Integer getId(){
        return this.id;
    }

    public Integer getUserId(){
        return this.userId;
    }

    public String getContents(){
        return this.contents;
    }

    public Integer getHotelId(){
        return this.hotelId;
    }
}
