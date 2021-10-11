package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.Coupon;
import com.example.hotel.po.User;
import com.example.hotel.vo.OrderVO;
//import org.graalvm.compiler.serviceprovider.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebMemberCreditCouponImpl implements CouponMatchStrategy {
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        if (coupon.getCouponType() == 7){
            User thisUser = accountMapper.getAccountById(orderVO.getUserId());
            if (thisUser.getCredit()>=coupon.getCreditRequired()){
                return true;
            }
        }
        return false;
    }
}
