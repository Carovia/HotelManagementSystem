import { axios } from '@/utils/request'
import{message} from 'ant-design-vue'
const api = {
    couponPre: '/api/coupon'
}

export function hotelCouponAPI(data) {
    return axios({
        url: `${api.couponPre}/hotelCoupon`,
        method: 'POST',
        data,
    })
}

export function webTimeAPI(data) {
    // message.success('web time coupon at api')
    // message.success(data.endTime)
    return axios({

        url: `${api.couponPre}/webTime`,
        method: 'POST',
        data,
    })
}

export function webVipRegAPI(data){
    // message.success('web vip region coupon at api')
    return axios({
        url: `${api.couponPre}/webVipReg`,
        method: 'POST',
        data,
    })
}

export function webMemberCreditAPI(data){
    // message.success('web member credit coupon at api')
    return axios({
        url: `${api.couponPre}/webMemberCredit`,
        method: 'POST',
        data,
    })
}

export function hotelAllCouponsAPI(hotelId) {
    return axios({
        url: `${api.couponPre}/hotelAllCoupons`,
        method: 'GET',
        params: {hotelId: hotelId},
    })
}

export function webAllCouponsAPI(){
    // message.success('web all coupon api')
    return axios({
        url:`${api.couponPre}/webAllCoupons`,
        method:'GET',
    })
}

export function orderMatchCouponsAPI(params) {
    return axios({
        url: `${api.couponPre}/orderMatchCoupons`,
        method: 'GET',
        params,
    })
}
export function orderMatchWebCouponsAPI(params) {
    return axios({
        url: `${api.couponPre}/orderMatchWebCoupons`,
        method: 'GET',
        params,
    })
}