import { axios } from '@/utils/request'
import{message} from 'ant-design-vue'
const api = {
    hotelPre: '/api/hotel'
}
export function addRoomAPI(data) {
    return axios({
        url: `${api.hotelPre}/roomInfo`,
        method: 'POST',
        data,
    })
}
export function addHotelAPI(data) {
    // message.success('hereIAmxxx')
    return axios({
        url: `${api.hotelPre}/addHotel`,
        method: 'POST',
        data,
    })
}
export function modifyHotelAPI(data) {
    return axios({
        url: `${api.hotelPre}/modifyHotel`,
        method: 'POST',
        data,
    })
}
export function deleteHotelAPI(hotelId) {
    return axios({
        url: `${api.hotelPre}/deleteHotel`,
        method: 'POST',
        params: {hotelId: hotelId},
    })
}
