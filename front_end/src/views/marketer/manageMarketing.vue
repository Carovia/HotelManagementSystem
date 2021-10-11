<template>
    <div class="manageMarketing-wrapper">
        <a-tabs>
            <a-tab-pane tab="网站促销策略管理" key="1">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addWebCouponxxx()">
                        <a-icon type="plus"/>添加优惠策略
                    </a-button>
                </div>
                <a-table
                        :columns="columns1"
                        :dataSource="webCouponList"
                        bordered
                >
                    <a-tag color="green" slot="couponName" slot-scope="text">
                        {{text}}
                    </a-tag>
                </a-table>

            </a-tab-pane>
            <a-tab-pane tab="信用管理" key="2">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="chargeCredit" style="margin-right: 30px;">
                        <a-icon type="plus"/>信用充值
                    </a-button>
                    <!--                    <a-button type="primary" @click="setLevelRules">-->
                    <!--                        <a-icon type="edit"/>会员等级制定-->
                    <!--                    </a-button>-->
                </div>
                <a-table
                        :columns="columns2"
                        :dataSource="exceptionalOrderList"
                        bordered
                >
                    <!--            栏目内操作选项-->
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                    <span slot="roomType" slot-scope="text">
                        <span v-if="text == 'BigBed'">大床房</span>
                        <span v-if="text == 'DoubleBed'">双床房</span>
                        <span v-if="text == 'Family'">家庭房</span>
                    </span>
                    <span slot="action" slot-scope="record">
                        <a-popconfirm
                                title="确定想恢复该订单一半的信用么"
                                @confirm="recoverCredit('0.5',record)"
                                okText="确定"
                                cancelText="取消"
                        >
                            <a-button type="primary" size="small" >恢复一半</a-button>
                        </a-popconfirm>
                        <a-divider type="vertical"></a-divider>
                        <a-popconfirm
                                title="确定想恢复该订单全部的信用么"
                                @confirm="recoverCredit('1',record)"
                                okText="确定"
                                cancelText="取消"
                        >
                            <a-button type="primary" size="small" >恢复全部</a-button>
                        </a-popconfirm>
                        <a-divider type="vertical"></a-divider>
                        <a-popconfirm
                                title="确定不恢复订单的信用么"
                                @confirm="recoverCredit('0',record)"
                                okText="确定"
                                cancelText="取消"
                        >
                            <a-button type="primary" size="small" >不恢复</a-button>
                        </a-popconfirm>
                    </span>
                </a-table>
            </a-tab-pane>
        </a-tabs>
        <AddWebCoupon></AddWebCoupon>
        <!--        <SetLevelRulesModal></SetLevelRulesModal>-->
        <ChargeCreditModal></ChargeCreditModal>
    </div>

</template>

<script>
    import {message} from 'ant-design-vue'
    import {mapGetters, mapMutations, mapActions}from 'vuex'
    import AddWebCoupon from './components/addWebCoupon'
    import SetLevelRulesModal from './components/setLevelRulesModal'
    import ChargeCreditModal from "./components/chargeCreditModal";
    const moment = require('moment')
    const columns1 = [
        {
            title: '优惠类型',
            dataIndex: 'couponName',
            scopedSlots: {customRender: 'couponName'},
        },
        {
            title: '折扣',
            dataIndex: 'discount',
        },
        {
            title: '优惠简介',
            dataIndex: 'description',
        },
    ]
    const columns2 = [
        {
            title: '用户',
            dataIndex: 'userId'
        },
        {
            title: '订单号',
            dataIndex: 'id',
        },
        {
            title: '酒店名',
            dataIndex: 'hotelName',
        },
        {
            title: '房型',
            dataIndex: 'roomType',
            scopedSlots: {customRender: 'roomType'}
        },
        {
            title: '入住时间',
            dataIndex: 'checkInDate',
            scopedSlots: {customRender: 'checkInDate'},
        },
        {
            title:'离店时间',
            dataIndex: 'checkOutDate',
            scopedSlots:{customRender:'checkOutDate'}
        },
        {
            title: '房价',
            dataIndex: 'price'
        },
        {
            title: '恢复信用额度',
            key: 'action',
            scopedSlots: {customRender: 'action'},
        }
    ]
    export default {
        name: "manageMarketing",
        data() {
            return {
                formLayout: 'horizontal',
                pagination: {},
                columns1,
                columns2,
                form:this.$form.createForm(this,{name: 'manageMarketing'}),
            }
        },
        components:{
            ChargeCreditModal,
            AddWebCoupon,
            // SetLevelRulesModal,
        },
        computed:{
            ...mapGetters([
                'addWebCouponVisible',
                'setLevelRulesModalVisible',
                'chargeCreditModalVisible',
                'exceptionalOrderList',
                'webCouponVisible',
                'webCouponList',
            ]),
        },
        async mounted(){
            await this.getExceptionalOrderList()
            await this.getWebCoupon()
        },
        methods:{
            ...mapMutations([
                'set_webCouponVisible',
                'set_addWebCouponVisible',
                // 'set_setLevelRulesModalVisible',
                'set_chargeCreditModalVisible'
            ]),
            ...mapActions([
                'getExceptionalOrderList',
                'getWebCoupon',
                'recoverCreditXxx',
                'markOrderAsHandled'
            ]),
            //各种方法
            showCoupon(){
                this.set_webCouponVisible(true)
                this.getAllWebCoupon()
            },
            addWebCouponxxx(){
                this.set_addWebCouponVisible(true)
            },
            setLevelRules(){
                // this.set_setLevelRulesModalVisible(true)
            },
            chargeCredit(){
                this.set_chargeCreditModalVisible(true)
            },
            recoverCredit(v,record){
                // message.success('fuck you at vue')
                const dataO = {

                    id:record.id

                }
                const dataU ={
                    id:record.userId,
                    credit:-1.0,
                }
                if(v === '0'){
                    dataU.credit = 0.0
                }
                if(v === '0.5'){
                    dataU.credit = 0.5 * record.price
                }
                if(v === '1'){
                    dataU.credit = 1 * record.price
                }
                this.recoverCreditXxx(dataU)
                this.markOrderAsHandled(dataO)
            }
        }
    }
</script>
<!--以下是风格设置-->
<!--<style scoped>-->
<!---->
<!--</style>-->
<style scoped lang="less">
    .manageMarketing-wrapper {
        padding: 50px;
        .chart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 20px
        }
    }
</style>
<style lang="less">
    .manageMarketing-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">

</style>