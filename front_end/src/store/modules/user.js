import Vue from 'vue'
import router from '@/router'
import {getToken, setToken, removeToken} from '@/utils/auth'
import {resetRouter} from '@/router'
import {message} from 'ant-design-vue'
import {
    loginAPI,
    registerAPI,
    getUserInfoAPI,
    updateUserInfoAPI,
    setfaceurlAPI,
} from '@/api/user'

import {
    getUserOrdersAPI,
    cancelOrderAPI,
    getUserHotelOrdersAPI,
} from '@/api/order'

import {
    getuserorderdhotelListAPI,
} from '@/api/hotel'

const getDefaultState = () => {
    return {
        userId: '',
        hotelId: '',
        userInfo: {},
        userOrderList: [],
        userHotelOrderList: [],
        userorderdhotelList: [],
        creditList: [],

        faceurlVisible: false,
        faceurl: {
            id: 0,
            portrait: ''
        },

    }
}

const user = {
    state: getDefaultState(),

    mutations: {
        reset_state: function (state) {
            state.token = '',
                state.userId = '',
                state.hotelId = '',
                state.userInfo = {},
                state.userOrderList = [],
                state.userHotelOrderList = [],
                state.userorderdhotelList = [],
                state.creditList = []
        },
        set_token: function (state, token) {
            state.token = token
        },
        set_email: (state, data) => {
            state.email = data
        },
        set_userId: (state, data) => {
            state.userId = data
        },
        set_hotelId: (state, data) => {
            state.hotelId = data
        },
        set_userInfo: (state, data) => {
            state.userInfo = {
                ...state.userInfo,
                ...data
            }
        },
        set_userOrderList: (state, data) => {
            state.userOrderList = data
        },
        set_userHotelOrderList: (state, data) => {
            state.userHotelOrderList = data
        },
        set_userorderdhotelList: (state, data) => {
            state.userorderdhotelList = data;
        },
        set_creditList: (state, data) => {
            state.creditList = data
        },
        set_faceurl: (state, data) => {
            state.faceurl.portrait = data.portrait
            state.faceurl.id = data.id
            //message.success(state.faceurl.portrait)
        },
        set_faceurlVisible: (state, data) => {
            state.faceurlVisible = data
        }
    },

    actions: {
        login: async ({ dispatch, commit }, userData) => {
            const res = await loginAPI(userData)
            if(res){
                setToken(res.id)
                commit('set_userId', res.id)
                dispatch('getUserInfo')
                router.push('/hotel/hotelList')
            }
        },
        register: async({ commit }, data) => {
            const res = await registerAPI(data)
            if(res){
                message.success('注册成功')
            }
        },
        getUserInfo({ state, commit }) {
            return new Promise((resolve, reject) => {
              getUserInfoAPI(state.userId).then(response => {
                const data = response
                if (!data) {
                  reject('登录已过期，请重新登录')
                }
                commit('set_userInfo', data)
                commit('set_userId', data.id)
                resolve(data)
              }).catch(error => {
                reject(error)
              })
            })
        },
        updateUserInfo: async({ state, dispatch }, data) => {
            const params = {
                id: state.userId,
                ...data,
            }
            const res = await updateUserInfoAPI(params)
            if(res){
                message.success('修改成功')
                dispatch('getUserInfo')
            }
        },
        getUserOrders: async({ state, commit }) => {
            const data = {
                userId: Number(state.userId)
            }
            const res = await getUserOrdersAPI(data)
            if(res){
                commit('set_userOrderList', res)
                console.log(state.userOrderList)
            }
        },
        getuserorderdhotelList: async ({state, commit}) => {
            const data = {
                userId: Number(state.userId)
            }
            const res = await getuserorderdhotelListAPI(data)
            if (res) {
                commit('set_userorderdhotelList', res)
                console.log(state.userorderdhotelList)
            }
        },
        getUserHotelOrders: async ({state, commit}) => {
            const data = {
                userId: Number(state.userId),
                hotelId: Number(state.hotelId)
            }
            const res = await getUserHotelOrdersAPI(data)
            if (res) {
                commit('set_userHotelOrderList', res)
                console.log(state.userHotelOrderList)
            }
        },
        addUserCredit: async ({state, commit}) => {
            const data = {
                userId: Number(state.userId)
            }
            const res = await getUserOrdersAPI(data)
            if (res) {
                commit('set_creditList', res)
                console.log(state.creditList)
            }
        },
        cancelOrder: async ({state, dispatch}, orderId) => {
            // message.success('fuck you at user.js')
            const res = await cancelOrderAPI(orderId)
            if(res) {
                dispatch('getUserOrders')
                dispatch('addUserCredit')
                message.success('撤销成功')
            }else{
                message.error('撤销失败')
            }
        },
        logout: async({ commit }) => {
            removeToken()
            resetRouter()
            commit('reset_state')
        },
          // remove token
        resetToken({ commit }) {
            return new Promise(resolve => {
                removeToken() // must remove  token  first
                commit('reset_state')
                resolve()
            })
        },
        setfaceurl: async ({state, commit}) => {
            //message.success(state.faceurl.portrait)
            const res = await setfaceurlAPI(state.faceurl)
            if (res) {
                commit('set_faceurl', {
                    portrait: ''
                })
                commit('set_faceurlVisible', false)
                message.success('修改成功，重新登录后可正常显示头像哦')
                //dispatch('getHotelList')
            } else {
                message.error('修改失败')
            }
        }
    }
}

export default user