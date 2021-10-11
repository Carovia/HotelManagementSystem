import {axios} from '@/utils/request'
import hotel from "../store/modules/hotel";
import {message}  from "ant-design-vue";

const api = {
    hotelPre: '/api/hotel'
}
export function getHotelsAPI() {
    return axios({
        url: `${api.hotelPre}/all`,
        method: 'get',
    })
}

export function getHotelByIdAPI(param) {
    return axios({
        url: `${api.hotelPre}/${param}/detail`,
        method: 'GET',
    })
}

export function getCommentsByHotelIdAPI(data) {
    return axios({
        url: `${api.hotelPre}/${data}/getCommentsByHotelId`,
        method: 'GET',
    })
}
export function addCommentAPI(data) {
    return axios({
        url: `${api.hotelPre}/addComment`,
        method: 'POST',
        data,
    })
}
export function getuserorderdhotelListAPI(data) {
    return axios({
        url: `${api.hotelPre}/${data.userId}/userorderdhotelList`,
        method: 'GET',
    })
}
export function setHotelidRateAPI(data) {
    // message.success("fuck")
    return axios({
        url: `${api.hotelPre}/setHotelidRate`,
        method: 'POST',
        data
    })
}