package com.example.hotel.controller.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/hotelCoupon")
    public ResponseVO addHotelCoupon(@RequestBody CouponVO hotelCouponVO) {

        CouponVO couponVO = couponService.addHotelCoupon(hotelCouponVO);

        return ResponseVO.buildSuccess(couponVO);
    }

    @PostMapping("/webTime")
    public ResponseVO addWebTimeCoupon(@RequestBody WebTimeCouponVO webTimeCouponVO) {
        CouponVO couponVO = couponService.addWebTimeCoupon(webTimeCouponVO);
        return ResponseVO.buildSuccess(couponVO);
    }

    @PostMapping("/webVipReg")
    public ResponseVO addWebVipRegCoupon(@RequestBody WebVipRegCouponVO webVipRegCouponVO) {
        CouponVO couponVO = couponService.addWebVipRegCoupon(webVipRegCouponVO);
        return ResponseVO.buildSuccess(couponVO);
    }

    @PostMapping("/webMemberCredit")
    public ResponseVO addWebMemberCreditCoupon(@RequestBody WebMemberCreditCouponVO webMemberCreditCouponVO) {
        CouponVO couponVO = couponService.addWebMemberCreditCoupon(webMemberCreditCouponVO);
        return ResponseVO.buildSuccess(couponVO);
    }

    @GetMapping("/hotelAllCoupons")
    public ResponseVO getHotelAllCoupons(@RequestParam Integer hotelId) {
        return ResponseVO.buildSuccess(couponService.getHotelAllCoupon(hotelId));
    }

    @GetMapping("/webAllCoupons")
    public ResponseVO getWebAllCoupons() {
        return ResponseVO.buildSuccess(couponService.getWebAllCoupon());
    }

    @GetMapping("/orderMatchCoupons")
    public ResponseVO getOrderMatchCoupons(@RequestParam Integer userId,
                                           @RequestParam Integer hotelId,
                                           @RequestParam Double orderPrice,
                                           @RequestParam Integer roomNum,
                                           @RequestParam String checkIn,
                                           @RequestParam String checkOut,
                                           @RequestParam String createDate) {
        OrderVO requestOrderVO = new OrderVO();
        requestOrderVO.setUserId(userId);
        requestOrderVO.setHotelId(hotelId);
        requestOrderVO.setPrice(orderPrice);
        requestOrderVO.setRoomNum(roomNum);
        requestOrderVO.setCheckInDate(checkIn);
        requestOrderVO.setCheckOutDate(checkOut);
        requestOrderVO.setCreateDate(createDate);
        return ResponseVO.buildSuccess(couponService.getMatchOrderCoupon(requestOrderVO));
    }

    @GetMapping("/orderMatchWebCoupons")
    public ResponseVO getOrderMatchWebCoupons(@RequestParam Integer userId,
                                              @RequestParam Double orderPrice,
                                              @RequestParam Integer roomNum,
                                              @RequestParam String checkIn,
                                              @RequestParam String checkOut,
                                              @RequestParam String createDate,
                                              @RequestParam Integer bizRegKnownByHotelId){
        OrderVO requestOrderVO = new OrderVO();
        requestOrderVO.setUserId(userId);
        requestOrderVO.setHotelId(bizRegKnownByHotelId);
        requestOrderVO.setPrice(orderPrice);
        requestOrderVO.setRoomNum(roomNum);
        requestOrderVO.setCheckInDate(checkIn);
        requestOrderVO.setCheckOutDate(checkOut);
        requestOrderVO.setCreateDate(createDate);
        return ResponseVO.buildSuccess(couponService.getMatchOrderWebCoupon(requestOrderVO));
    }

}
