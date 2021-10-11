package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.enums.BizRegion;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CouponServiceImpl implements CouponService {


    private final TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy;
    private final MultipleRoomCouponStrategyIpml multipleRoomCouponStrategy;
    private final TimeCouponStrategyImpl timeCouponStrategy;
    private final WebVipRegCouponImpl webVipRegCouponStrategy;
    private final WebMemberCreditCouponImpl webMemberCreditCouponStrategy;
    private final WebTimeCouponStrategyImpl webTimeCouponStrategy;
    private final CouponMapper couponMapper;

    private static List<CouponMatchStrategy> strategyList = new ArrayList<>();

    @Autowired
    public CouponServiceImpl(TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy,
                             TimeCouponStrategyImpl timeCouponStrategy,
                             WebTimeCouponStrategyImpl webTimeCouponStrategy,
                             MultipleRoomCouponStrategyIpml multipleRoomCouponStrategy,
                             WebMemberCreditCouponImpl webMemberCreditCouponStrategy,
                             WebVipRegCouponImpl webVipRegCouponStrategy,
                             CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
        this.targetMoneyCouponStrategy = targetMoneyCouponStrategy;
        this.timeCouponStrategy = timeCouponStrategy;
        this.webMemberCreditCouponStrategy  = webMemberCreditCouponStrategy;
        this.multipleRoomCouponStrategy = multipleRoomCouponStrategy;
        this.webTimeCouponStrategy = webTimeCouponStrategy;
        this.webVipRegCouponStrategy = webVipRegCouponStrategy;
        strategyList.add(targetMoneyCouponStrategy);
        strategyList.add(timeCouponStrategy);
        strategyList.add(webMemberCreditCouponStrategy);
        strategyList.add(webTimeCouponStrategy);
        strategyList.add(webVipRegCouponStrategy);
        strategyList.add(multipleRoomCouponStrategy);
    }


    @Override
    public List<Coupon> getMatchOrderCoupon(OrderVO orderVO) {

        List<Coupon> hotelCoupons = getHotelAllCoupon(orderVO.getHotelId());

        List<Coupon> availAbleCoupons = new ArrayList<>();

        for (int i = 0; i < hotelCoupons.size(); i++) {
            for (CouponMatchStrategy strategy : strategyList) {
                if (strategy.isMatch(orderVO, hotelCoupons.get(i))) {
                    availAbleCoupons.add(hotelCoupons.get(i));
                }
            }
        }

        return availAbleCoupons;
    }

    @Override
    public List<Coupon> getHotelAllCoupon(Integer hotelId) {
        List<Coupon> hotelCoupons = couponMapper.selectByHotelId(hotelId);
        return hotelCoupons;
    }

    @Override
    public List<Coupon> getWebAllCoupon() {
        //woo
        List<Coupon> webCoupons = couponMapper.selectByHotelId(-1);
        return webCoupons;
    }

    @Override
    public CouponVO addHotelCoupon(CouponVO couponVO) {
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setTargetMoney(couponVO.getTargetMoney());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setDiscountMoney(couponVO.getDiscountMoney());
        coupon.setStartTime(couponVO.getStartTime());
        coupon.setEndTime(couponVO.getEndTime());
        coupon.setDiscount(couponVO.getDiscount());
        coupon.setStatus(1);
        int result = couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    public List<Coupon> getMatchOrderWebCoupon(OrderVO orderVO) {
        List<Coupon> webCoupons = getWebAllCoupon();

        List<Coupon> availAbleCoupons = new ArrayList<>();

        for (int i = 0; i < webCoupons.size(); i++) {
            for (CouponMatchStrategy strategy : strategyList) {
                if (strategy.isMatch(orderVO, webCoupons.get(i))) {
                    availAbleCoupons.add(webCoupons.get(i));
                }
            }
        }

        return availAbleCoupons;
    }

    @Override
    public void deleteCouponByHotelId(Integer hotelId) {
        couponMapper.deleteByHotelId(hotelId);
    }

    @Override
    public CouponVO addWebTimeCoupon(WebTimeCouponVO couponVO) {
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setStartTime(couponVO.getStartTime());
        coupon.setEndTime(couponVO.getEndTime());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setDiscount((couponVO.getDiscount()));
        coupon.setStatus(1);
        int result = couponMapper.insertCoupon((coupon));
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    public CouponVO addWebVipRegCoupon(WebVipRegCouponVO couponVO) {
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setBizReg(BizRegion.valueOf(couponVO.getBizReg()));
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setDiscount(couponVO.getDiscount());
        coupon.setStatus(1);
        int result = couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    public CouponVO addWebMemberCreditCoupon(WebMemberCreditCouponVO couponVO) {
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setCreditRequired(couponVO.getCreditRequired());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setDiscount(couponVO.getDiscount());
        coupon.setStatus(1);
        int result = couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }
}
