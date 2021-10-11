package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeCouponStrategyImpl implements CouponMatchStrategy {


    /**
     * 判断某个订单是否满足某种限时优惠策略
     * @param orderVO
     * @return
     */
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        if (coupon.getCouponType() == 4){
            LocalDateTime orderTime = LocalDateTime.parse(orderVO.getCreateDate() + "T00:00:00");
            return (orderTime.isAfter(coupon.getStartTime()) && orderTime.isBefore(coupon.getEndTime())) ? true : false;
        }
        return false;
    }
}
