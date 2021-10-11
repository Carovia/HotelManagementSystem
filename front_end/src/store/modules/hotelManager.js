import {
    addRoomAPI,
    addHotelAPI,
    modifyHotelAPI,
    deleteHotelAPI,
} from '@/api/hotelManager'
import {
    getAllOrdersAPI,
    deleteOrderAPI,
    checkInAPI,
    checkOutAPI,
} from '@/api/order'
import {
    hotelAllCouponsAPI,
    hotelCouponAPI,
} from '@/api/coupon'
import {
    updateUserCreditAPI,
} from "@/api/user"
import {
    getHotelByIdAPI,
} from "@/api/hotel";
import { message } from 'ant-design-vue'

const hotelManager = {
    state: {
        orderList: [],
        addHotelParams: {
            name: '',
            address: '',
            bizRegion:'XiDan',
            hotelStar:'',
            rate: 0,
            description:'',
            phoneNum:'',
            managerId:'',
        },
        modifyHotelParams: {
            id: '',
            name: '',
            address: '',
            hotelStar:'',
            description:'',
            phoneNum:'',
            managerId:'',
        },
        addHotelModalVisible: false,
        addRoomParams: {
            roomType: '',
            hotelId: '',
            price: '',
            total: 0,
            curNum: 0,
        },
        addRoomModalVisible: false,
        couponVisible: false,
        addCouponVisible: false,
        modifyHotelModalVisible: false,
        orderDetailVisible: false,
        activeHotelId: 0,
        activeOrderId: 0,
        couponList: [],
        activeHotel: [],
        activeOrder: [],
    },
    mutations: {
        set_orderList: function(state, data) {
            state.orderList = data
        },
        set_addHotelModalVisible: function(state, data) {
            state.addHotelModalVisible = data
        },
        set_addHotelParams: function(state, data) {
            state.addHotelParams = {
                ...state.addHotelParams,
                ...data,
            }
        },
        set_addRoomModalVisible: function(state, data) {
            state.addRoomModalVisible = data
        },
        set_addRoomParams: function(state, data) {
            state.addRoomParams = {
                ...state.addRoomParams,
                ...data,
            }
        },
        set_modifyHotelParams: function(state, data) {
            state.modifyHotelParams = {
                ...state.modifyHotelParams,
                ...data,
            }
        },
        set_couponVisible: function(state, data) {
            state.couponVisible = data
        },
        set_activeHotelId: function(state, data) {
            state.activeHotelId = data
        },
        get_activeHotelId: function(state) {
            return state.activeHotelId
        },
        set_couponList: function(state, data) {
            state.couponList = data
        },
        set_addCouponVisible: function(state, data) {
            state.addCouponVisible = data
        },
        set_activeHotel: function(state, data) {
            state.activeHotel = data
        },
        set_activeOrderId: function(state, data) {
            state.activeOrderId = data
        },
        set_modifyHotelModalVisible: function(state, data) {
            state.modifyHotelModalVisible = data
        },
        set_orderDetailVisible: function(state, data) {
            state.orderDetailVisible = data
        },
        set_activeOrder: function(state) {
            for (var i=0; i<state.orderList.length; i++) {
                if (state.orderList[i].id === state.activeOrderId) {
                    state.activeOrder = state.orderList[i];
                    break;
                }
            }
        },
    },
    actions: {
        getAllOrders: async({ state, commit }) => {
            const res = await getAllOrdersAPI()
            if(res){
                commit('set_orderList', res)
            }
        },
        addHotel: async({ state, dispatch, commit }) => {
            // message.success('here at modules/hotelManager1')
            const res = await addHotelAPI(state.addHotelParams)
            // message.success('here at modules/hotelManager2')
            if(res > 0){
                dispatch('getHotelList')
                commit('set_addHotelParams', {
                    name: '',
                    address: '',
                    bizRegion:'XiDan',
                    hotelStar:'',
                    rate: 0,
                    description:'',
                    phoneNum:'',
                    managerId:'',
                })
                commit('set_addHotelModalVisible', false)
                commit('set_activeHotelId', res)
                message.success('添加成功')
            }else{
                message.error('添加失败')
            }
        },
        addRoom: async({ state, dispatch, commit }) => {
            const res = await addRoomAPI(state.addRoomParams)
            // message.success('here at where after api')
            // message.success(res)
            if(res){
                commit('set_addRoomModalVisible', false)
                commit('set_addRoomParams', {
                    roomType: '',
                    hotelId: '',
                    price: '',
                    total: 0,
                    curNum: 0,
                })
                message.success('添加成功')
            }else{
                message.error('添加失败')
            }
        },
        getHotelCoupon: async({ state, commit }) => {
            const res = await hotelAllCouponsAPI(state.activeHotelId)
            if(res) {
                // 获取到酒店策略之后的操作（将获取到的数组赋值给couponList）
                commit('set_couponList', res)
            }
        },
        addHotelCoupon: async({ commit, dispatch }, data) => {
            const res = await hotelCouponAPI(data)
            if(res){
                // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）
                dispatch('getHotelCoupon')
                commit('set_addCouponVisible', false)
                commit('set_couponVisible', true)
                message.success('添加策略成功')
            }else{
                // 添加失败后的操作
                message.error('添加失败')
            }
        },
        getHotelDetail: async({ commit, state }) => {
            const res = await getHotelByIdAPI(state.activeHotelId)
            if (res) {
                commit('set_activeHotel', res)
            }
        },
        modifyHotel: async({ dispatch, state }) => {
            const res = await modifyHotelAPI(state.modifyHotelParams)
            if(res){
                dispatch('getHotelList')
                message.success('修改成功')
            }else{
                message.error('修改失败')
            }
        },
        deleteHotelRecord: async({ dispatch, state }) => {
            const res = await deleteHotelAPI(state.activeHotelId)
            if(res){
                dispatch('getHotelList')
                message.success('删除成功')
            }else{
                message.error('删除失败')
            }
        },
        checkIn: async({ state, dispatch }) => {
            const res1 = await checkInAPI(state.activeOrderId)
            const res2 = await updateUserCreditAPI(state.activeOrder.userId, state.activeOrder.price)
            if(res1 && res2) {
                dispatch('getAllOrders')
                message.success('入住成功')
            }else{
                message.error('入住失败')
            }
        },
        checkOut: async({ state, dispatch }) => {
            const res = await checkOutAPI(state.activeOrder)
            if(res) {
                dispatch('getAllOrders')
                message.success('退房成功')
            }else{
                message.error('退房失败')
            }
        },
        deleteOrderRecord: async({ state, dispatch }) => {
            const res = await deleteOrderAPI(state.activeOrder)
            if(res){
                dispatch('getAllOrders')
                message.success('删除成功')
            }else{
                message.error('删除失败')
            }
        },
    }
}
export default hotelManager