package com.example.hotel.Test;

import com.example.hotel.blImpl.order.OrderServiceImpl;
import com.example.hotel.po.User;
import com.example.hotel.vo.OrderVO;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderServiceImplTest {
    OrderServiceImpl test=new OrderServiceImpl();

    @Test
    public void addOrder() {
        OrderVO orderVO=new OrderVO();
        User user=new User();
        user.setId(4);
        orderVO.setRoomNum(1);
        orderVO.setHotelId(1);
        orderVO.setUserId(1);
        orderVO.setRoomType("BigBed");
        assertEquals("com.example.hotel.vo.ResponseVO@32e6e9c3",test.addOrder(orderVO));
    }

    @Test
    public void getAllOrders() {
    }

    @Test
    public void getExceptionalOrders() {
    }

    @Test
    public void getUserOrders() {
    }

    @Test
    public void getUserHotelOrders() {
    }

    @Test
    public void annulOrder() {
    }

    @Test
    public void getHotelOrders() {
    }

    @Test
    public void deleteOrderByHotelId() {
    }

    @Test
    public void checkIn() {
    }

    @Test
    public void checkOut() {
    }

    @Test
    public void deleteOrderById() {
    }

    @Test
    public void markOrderAsHandled() {
    }

    @Test
    public void checkExceptionOrder() {
    }
}