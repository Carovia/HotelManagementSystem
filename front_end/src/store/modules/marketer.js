import {
    setLevelRulesAPI,
} from '@/api/marketer'
import{
    chargeCreditAPI,
    recoverCreditAPI,
} from "../../api/user";
import {
    getExceptionalOrderAPI,
    markOrderAsHandledAPI,
} from '@/api/order'
import {
    webTimeAPI,
    webVipRegAPI,
    webMemberCreditAPI,
    webAllCouponsAPI,
} from '@/api/coupon'

import {message} from 'ant-design-vue'

const marketer = {
    state: {
        exceptionalOrderList: [],
        exceptionalOrderListParams: {
            pageNo: 0,
            pageSize: 12
        },
        exceptionalListLoading: true,
        webCouponVisible: false,
        addWebCouponVisible: false,
        setLevelRulesModalVisible: false,
        chargeCreditModalVisible:false,
        webCouponList: []
    },
    mutations: {
        set_exceptionalOrderList: function (state, data) {
            state.exceptionalOrderList = data
        },
        set_webCouponVisible: function (state, data) {
            state.webCouponVisible = data;
        },
        set_addWebCouponVisible: function (state, data) {
            state.addWebCouponVisible = data;
        },
        set_webCouponList: function (state, data) {
            state.webCouponList = data;
        },
        set_setLevelRulesModalVisible: function (state, data) {
            state.setLevelRulesModalVisible = data;
        },
        set_chargeCreditModalVisible: function (state, data) {
            state.chargeCreditModalVisible = data;
        }
    },
    actions: {
        getExceptionalOrderList: async ({commit, state}) => {
            const res = await getExceptionalOrderAPI()
            if (res) {
                commit('set_exceptionalOrderList', res)
            }
        },
        getWebCoupon: async ({state, commit}) => {
            const res = await webAllCouponsAPI()
            if (res) {
                commit('set_webCouponList', res)
                // commit('set_webCouponListLoading', false)
            }
        },
        recoverCreditXxx: async ({state, commit}, data) => {
            // message.success('fuck you at module')
            const res = await recoverCreditAPI(data)
            if(res){
                message.success('处理成功')
            }
            else{
                message.error('处理失败')
            }
        },
        markOrderAsHandled:async({state, commit}, data)=>{
            // message.success('mark方法的module：'+data.id)
            const res = await markOrderAsHandledAPI(data)

        },
        chargeCredit: async ({commit, state}, data) => {

            const res = await chargeCreditAPI(data)
            if(res){
                commit('set_chargeCreditModalVisible',false)
                message.success('充值成功')
            }
            else{
                message.error('充值失败')
            }
        },
        setLevelRules: async ({commit, state}, data) => {
            const res = await setLevelRulesAPI(data)
            if (res) {
                commit('set_setLevelRulesModalVisible', false)
                message.success('会员规则设置成功')
            } else {
                message.error('会员规则设置失败')
            }
        },
        addWebTimeCoupon: async ({commit, dispatch}, data) => {
            // message.success('fuck')
            const res = await webTimeAPI(data)
            // message.success('fuck you')
            if (res) {
                dispatch('getWebCoupon')
                commit('set_addWebCouponVisible', false)
                // commit('set_webCoupon', true)
                message.success('添加策略成功')
            } else {
                message.error('添加失败')
            }
        },
        addWebVipRegCoupon: async ({commit, dispatch}, data) => {
            // message.success('fuck you at module')
            const res = await webVipRegAPI(data)
            // message.success('fuck you at module too')

            if (res) {
                dispatch('getWebCoupon')
                commit('set_addWebCouponVisible', false)
                message.success('添加策略成功')
            } else {
                message.error('添加失败')
            }
        },
        addWebCreditCoupon: async ({commit, dispatch}, data) => {
            // message.success('fuck from module')
            // message.success('module ' + data.creditRequired)
            const res = await webMemberCreditAPI(data)
            // message.success('fuck from module too')
            if (res) {
                dispatch('getWebCoupon')
                commit('set_addWebCouponVisible', false)
                message.success('添加策略成功')
            } else {
                message.error('添加失败')
            }
        }
    }
}
export default marketer