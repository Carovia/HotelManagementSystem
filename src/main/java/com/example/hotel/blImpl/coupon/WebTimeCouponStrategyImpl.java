package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Service
public class WebTimeCouponStrategyImpl implements CouponMatchStrategy {
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        if (coupon.getCouponType() == 5) {
            LocalDateTime orderTime = LocalDateTime.parse(orderVO.getCreateDate() + "T00:00:00");
            return (orderTime.isAfter(coupon.getStartTime()) && orderTime.isBefore(coupon.getEndTime())) ? true : false;

        }

        return false;
    }
}

