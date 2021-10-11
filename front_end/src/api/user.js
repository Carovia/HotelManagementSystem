import { axios } from '@/utils/request'
import {message} from "ant-design-vue";

const api = {
    userPre: '/api/user'
}
export function loginAPI(data){
    return axios({
        url:`${api.userPre}/login`,
        method: 'POST',
        data
    })
}
export function registerAPI(data){
    return axios({
        url: `${api.userPre}/register`,
        method: 'POST',
        data
    })
}
export function getUserInfoAPI(id){
    return axios({
        url: `${api.userPre}/${id}/getUserInfo`,
        method: 'GET'
    })
}
export function updateUserInfoAPI(data) {
    return axios({
        url: `${api.userPre}/${data.id}/userInfo/update`,
        method: 'POST',
        data
    })
}
export function updateUserCreditAPI(userId, addCredit) {
    return axios({
        url: `${api.userPre}/${userId}/userInfo/updateCredit`,
        method: 'POST',
        params: {addCredit: addCredit}
    })
}
export function chargeCreditAPI(data) {
    return axios({
        url: `${api.userPre}/chargeCredit`,
        method: 'POST',
        data,
    })
}
export function setfaceurlAPI(data) {
    //message.success(data.id)
    return axios({
        url: `${api.userPre}/portrait`,
        method: 'POST',
        data
    })
}
export function recoverCreditAPI(data){
    // message.success('fuck you at api')
    return axios({
        url: `${api.userPre}/recoverCredit`,
        method: 'POST',
        data,
    })
}
