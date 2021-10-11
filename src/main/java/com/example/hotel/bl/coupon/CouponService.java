package com.example.hotel.bl.coupon;

import com.example.hotel.po.Coupon;
import com.example.hotel.vo.*;

import java.util.List;

public interface CouponService {
    /**
     * 返回某一订单可用的优惠策略列表
     * @param orderVO
     * @return
     */
    List<Coupon> getMatchOrderCoupon(OrderVO orderVO);

    /**
     * 查看某个酒店提供的所有优惠策略（包括失效的）
     * @param hotelId
     * @return
     */
    List<Coupon> getHotelAllCoupon(Integer hotelId);

    List<Coupon> getWebAllCoupon();


    List<Coupon> getMatchOrderWebCoupon(OrderVO orderVO);

    /**
     * 添加酒店优惠策略
     * @param couponVO
     * @return
     */
    CouponVO addHotelCoupon(CouponVO couponVO);

    /**
     * 添加网站特殊时间段优惠策略
     * @param couponVO
     * @return
     * */
    CouponVO addWebTimeCoupon(WebTimeCouponVO couponVO);

    /**
     * 添加网站VIP用户在特定商圈优惠策略
     * @param couponVO
     * @return
     * */
    CouponVO addWebVipRegCoupon(WebVipRegCouponVO couponVO);

    /**
     * 添加网站会员等级优惠策略
     * @param couponVO
     * @return
     * */
    CouponVO addWebMemberCreditCoupon(WebMemberCreditCouponVO couponVO);

    /**
     * 删除酒店所有优惠策略
     * @param hotelId
     */
    void deleteCouponByHotelId(Integer hotelId);
}
