import {
    getManagerListAPI,
    addManagerAPI,
    deleteUserAPI,
    updateinfoAPI,
} from '@/api/admin'
import { message } from 'ant-design-vue'

const admin = {
    state: {
        managerList: [

        ],
        marketerList:[],
        addManagerModalVisible: false,
        addMarketerModalVisible:false,
        updateinfoModalVisible: false,
        addManagerParams: {
            // userName: '',
            // email: '',
            // password: '',
            // userType: ''
        },
        addMarketerParams:{
            userName: '',
            email: '',
            password: '',
            userType: ''
        },
        updateParams:{
            username: '',
            phoneNumber: '',
            password: '',
            userType: '',
            userId: 0
        },
        activeuserId:0
    },
    mutations: {
        set_managerList: function(state, data) {
            state.managerList = data
        },
        set_addManagerModalVisible: function(state, data) {
            state.addManagerModalVisible = data
        },
        set_addManagerParams: function(state, data) {
            state.addManagerParams = {
                ...state.addManagerParams,
                ...data,
            }
        },
        set_activeuserId: function(state, data) {
            state.activeuserId= data
        },
        set_updateinfoModalVisible: function(state, data) {
            state.updateinfoModalVisible = data
        },
        set_updateParams: function(state, data) {
            state.updateParams = {
                ...state.updateParams,
                ...data,
            }
        },
    },
    actions: {
        delete:async({dispatch },Id) => {
            const res = await deleteUserAPI(Id)
            if(res) {
                dispatch('getManagerList')
                message.success('撤销成功')
            }else{
                message.error('撤销失败')
            }
        },
        getManagerList: async({ commit }) => {
            const res = await getManagerListAPI()
            if(res){
                commit('set_managerList', res)
            }
        },
        addManager: async({ state, commit, dispatch }) => {
            const res = await addManagerAPI(state.addManagerParams)
            // message.success(state.addManagerParams.userType)
            if(res) {
                commit('set_addManagerParams',{
                    email:'',
                    password:''
                })
                commit('set_addManagerModalVisible', false)
                message.success('添加成功')
                dispatch('getManagerList')
            }else{
                message.error('添加失败')
            }
        },
        // addMarketer:async ({state, commit, dispatch})=>{
        //     const res = await addMarketerAPI(state.addMarketerParams)
        //     if(res){
        //         commit('set_addMarketerParams',{
        //             email:',' ,
        //             password:''
        //         })
        //         commit('set_addManagerModalVisible',false)
        //         message.success
        //     }
        // },
        updateinfo: async({ state, commit ,dispatch}) => {
            console.log(state.updateParams)
            const res = await updateinfoAPI(state.updateParams)
            if(res){

                commit('set_updateParams', {
                    username: '',
                    phonenumber: '',
                    password: '',
                    email:''
                })
                commit('set_updateinfoModalVisible', false)
                message.success('更新成功')
                dispatch('getManagerList')
            }else{
                message.error('更新失败')
            }
        }
    }
}
export default admin