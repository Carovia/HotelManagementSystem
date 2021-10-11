package com.example.hotel.Test;

import com.example.hotel.blImpl.coupon.TargetMoneyCouponStrategyImpl;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.junit.Test;

import static org.junit.Assert.*;

public class TargetMoneyCouponStrategyImplTest {
    Coupon coupon=new Coupon();
    OrderVO orderVO=new OrderVO();
    TargetMoneyCouponStrategyImpl test=new TargetMoneyCouponStrategyImpl();
    @Test
    public void isMatch() {
        coupon.setTargetMoney(100);
        coupon.setCouponType(3);
        orderVO.setPrice(120.00);
        assertEquals(true,test.isMatch(orderVO,coupon));
    }
}