import {axios} from '@/utils/request'
import {message} from 'ant-design-vue'
const api = {
    marketerPre: '/api/marketer',
}

//初步
export function setLevelRulesAPI(data) {
    //制定会员等级规则
    // message.success('fuck you at api')
    return axios({
        url: `${api.marketerPre}/setLevelRules`,
        method: 'POST',
        data,
    })
}