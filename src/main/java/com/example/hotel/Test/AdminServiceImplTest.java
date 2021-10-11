package com.example.hotel.Test;

import com.example.hotel.blImpl.admin.AdminServiceImpl;
import com.example.hotel.data.admin.AdminMapper;
import com.example.hotel.vo.UserForm;

import static org.junit.Assert.*;

public class AdminServiceImplTest {
    com.example.hotel.blImpl.admin.AdminServiceImpl test=new AdminServiceImpl();
    @org.junit.Test
    public void addManager() {
        AdminMapper adminMapper = null;
        UserForm userForm =new UserForm();
        userForm.setEmail("2358005722@qq.com");
        userForm.setPassword("123456");
        userForm.setUserName("la");
        userForm.setUserType(2);
        System.out.println(test.addManager(userForm));
        assertEquals("com.example.hotel.vo.ResponseVO@32e6e9c3",test.addManager(userForm).toString());
    }


    @org.junit.Test
    public void deleteUser() {
        assertEquals("com.example.hotel.vo.ResponseVO@5afa04c",test.deleteUser(1).toString());
    }

    @org.junit.Test
    public void updateUserInfo() {
        assertEquals("com.example.hotel.vo.ResponseVO@36d4b5c",test.updateUserInfo(1,"123456","la","13770471203","2358005722@qq.com").toString());
    }
}