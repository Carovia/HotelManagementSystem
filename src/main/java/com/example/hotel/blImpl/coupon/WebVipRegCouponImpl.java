package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.enums.BizRegion;
import com.example.hotel.po.Coupon;
import com.example.hotel.po.Hotel;
import com.example.hotel.po.User;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebVipRegCouponImpl implements CouponMatchStrategy {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private HotelMapper hotelMapper;
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        if(coupon.getCouponType() == 6){
            User thisUser = accountMapper.getAccountById(orderVO.getUserId());
            if(thisUser.getVIP() == 1){
                HotelVO thisHotel = hotelMapper.selectById(orderVO.getHotelId());
                if(BizRegion.valueOf(thisHotel.getBizRegion()).equals(coupon.getBizReg())){
                    return true;
                }
            }
        }
        return false;
    }
}
