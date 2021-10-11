package com.example.hotel.Test;

import com.example.hotel.blImpl.user.AccountServiceImpl;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserVO;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountServiceImplTest {
AccountServiceImpl test=new AccountServiceImpl();
    @Test
    public void registerAccount() {
        UserVO userVO=new UserVO();
        userVO.setEmail("123@qq.com");
        userVO.setPassword("123456");
        userVO.setUserName("la");
        assertEquals("com.example.hotel.vo.ResponseVO@120d6fe6",test.registerAccount(userVO).toString());
    }



    @Test
    public void updateUserInfo() {
        assertEquals("com.example.hotel.vo.ResponseVO@25359ed8",test.updateUserInfo(1,"123456","la","13770471203").toString());
    }


}