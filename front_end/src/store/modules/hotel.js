import { message } from 'ant-design-vue'
import store from '@/store'
import {
    getHotelsAPI,
    getHotelByIdAPI,
    getCommentsByHotelIdAPI,
    addCommentAPI,
    setHotelidRateAPI,
} from '@/api/hotel'
import {
    reserveHotelAPI
} from '@/api/order'
import {
    orderMatchCouponsAPI,
    orderMatchWebCouponsAPI,
} from '@/api/coupon'

const hotel = {
    state: {
        hotelList: [

        ],
        hotelListParams: {
            pageNo: 0,
            pageSize: 12
        },
        hotelListLoading: true,
        currentHotelId: '',
        currentHotelInfo: {

        },
        orderModalVisible: false,
        currentOrderRoom: {

        },
        orderMatchCouponList: [

        ],
        orderMatchWebCouponList:[

        ],
        commentList:[

        ],
        addCommentVisible:false,
        addrateRaramVisible: false,
        setHotelrateParams: {
            id: 0,
            rate: 0.0
        }
    },
    mutations: {
        set_hotelList: function(state, data) {
            state.hotelList = data
        },
        set_hotelListParams: function(state, data) {
            state.hotelListParams = {
                ...state.hotelListParams,
                ...data,
            }
        },
        set_hotelListLoading: function(state, data) {
            state.hotelListLoading = data
        },
        set_currentHotelId: function(state, data) {
            state.currentHotelId = data
        },
        set_commentList:function(state, data){
            state.commentList = data
        },
        set_currentHotelInfo: function(state, data) {
            state.currentHotelInfo = {
                ...state.currentHotelInfo,
                ...data,
            }
        },
        set_orderModalVisible: function(state, data) {
            state.orderModalVisible = data
        },
        set_currentOrderRoom: function(state, data) {
            state.currentOrderRoom = {
                ...state.currentOrderRoom,
                ...data,
            }
        },
        set_orderMatchCouponList: function(state, data) {
            state.orderMatchCouponList = data
        },
        set_orderMatchWebCouponList:function(state,data){
            state.orderMatchWebCouponList = data;
        },
        set_addCommentVisible:function (state,data) {
            state.addCommentVisible = data
        },
        set_hotelRate: function (state, data) {
            state.setHotelrateParams = {
                ...state.setHotelrateParams,
                ...data,
            }
        },
    },

    actions: {
        getHotelList: async({commit, dispatch}) => {
            const res = await getHotelsAPI()
            if(res){
                commit('set_hotelList', res)
                commit('set_hotelListLoading', false)
            }
        },
        getHotelById: async({commit, state}) => {
            const res = await getHotelByIdAPI(state.currentHotelId)
            if(res){
                commit('set_currentHotelInfo', res)
            }
        },
        addComment:async ({commit, state}, data)=>{
            const res = addCommentAPI(data)
            if(res){
                commit('set_addCommentVisible',false)
                message.success('添加成功')
            }
            else{
                message.error('添加失败')
            }
        },
        getCommentsByHotelId:async ({commit,state},data)=>{
            const res = await getCommentsByHotelIdAPI(data)
            if(res){
                commit('set_commentList',res)
            }
        },
        addOrder: async({ state, commit }, data) => {
            const res = await reserveHotelAPI(data)
            console.log(res)
            if(res){
                message.success('预定成功')
                commit('set_orderModalVisible', false)
            }
        },
        getOrderMatchCoupons: async({ state, commit }, data) => {
            const res = await orderMatchCouponsAPI(data)
            if(res){
                commit('set_orderMatchCouponList', res)
            }
        },
        getOrderMatchWebCoupons:async ({state,commit},data)=>{
            const res = await orderMatchWebCouponsAPI(data)
            if(res){
                commit('set_orderMatchWebCouponList',res)
            }
        },
        sethotelRate: async ({state, commit}) => {
            const res= await setHotelidRateAPI(state.setHotelrateParams)
            if(res){
                commit('set_hotelRate',{
                    id: 0,
                    rate: 0.0
                })
                commit('set_addrateRaramVisible', false)
                message.success('修改成功')
                //dispatch('getHotelList')
            }
            else{
                message.error('修改失败')
            }
        }

    }
}

export default hotel