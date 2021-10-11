import { axios } from '@/utils/request'
import {message} from 'ant-design-vue'
const api = {
    orderPre: '/api/order'
}
export function reserveHotelAPI(data) {
    return axios({
        url: `${api.orderPre}/addOrder`,
        method: 'POST',
        data,
    })
}
export function getAllOrdersAPI() {
    return axios({
        url: `${api.orderPre}/getAllOrders`,
        method: 'GET',
    })
}
export function getUserOrdersAPI(data) {
    return axios({
        url: `${api.orderPre}/${data.userId}/getUserOrders`,
        method: 'GET',
    })
}
export function getUserHotelOrdersAPI(data) {
    // message.success(data.userId)
    // message.success(data.hotelId)
    return axios({
        url: `${api.orderPre}/${data.userId}, ${data.hotelId}/getUserHotelOrders`,
        method: `GET`,
    })
}
export function cancelOrderAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/annulOrder`,
        method: 'GET',
    })
}
export function checkInAPI(orderId) {
    return axios({
        url: `${api.orderPre}/checkIn`,
        method: 'POST',
        params: {orderId: orderId},
    })
}
export function checkOutAPI(data) {
    return axios({
        url: `${api.orderPre}/checkOut`,
        method: 'POST',
        data,
    })
}
export function deleteOrderAPI(data) {
    return axios({
        url: `${api.orderPre}/deleteOrder`,
        method: 'POST',
        data,
    })
}
export function getExceptionalOrderAPI(){
    return axios({
        url: `${api.orderPre}/getExceptionalOrder`,
        method: 'GET',
    })
}
export function markOrderAsHandledAPI(data) {
    return axios({
        url:`${api.orderPre}/markOrderAsHandled`,
        method:'POST',
        data,
    })
}