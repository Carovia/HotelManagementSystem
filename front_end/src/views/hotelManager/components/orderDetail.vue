<template>
    <a-modal
        :visible="orderDetailVisible"
        title="订单详情"
        okText="确认"
        cancelText="返回"
        @ok="goBack"
        @cancel="goBack"
    >
        <a-form :form="form" v-bind="formItemLayout">
            <a-form-item label="订单编号">
                <span>{{activeOrder.id}}</span>
            </a-form-item>
            <a-form-item label="下单时间">
                <span>{{activeOrder.createDate}}</span>
            </a-form-item>
            <a-form-item label="酒店名称">
                <span>{{activeOrder.hotelName}}</span>
            </a-form-item>
            <a-form-item label="客户名称">
                <span>{{activeOrder.clientName}}</span>
            </a-form-item>
            <a-form-item label="入住时间">
                <span>{{activeOrder.checkInDate}}</span>
            </a-form-item>
            <a-form-item label="离店时间">
                <span>{{activeOrder.checkOutDate}}</span>
            </a-form-item>
            <a-form-item label="房间类型">
                <span>{{activeOrder.roomType==="BigBed" ? "大床房" : activeOrder.roomType==="DoubleBed" ? "双床房" : "家庭房"}}</span>
            </a-form-item>
            <a-form-item label="房间数量">
                <span>{{activeOrder.roomNum}}</span>
            </a-form-item>
            <a-form-item label="入住人数">
                <span>{{activeOrder.peopleNum}}</span>
            </a-form-item>
            <a-form-item label="有无儿童">
                <span>{{activeOrder.haveChild===true ? "有" : "无"}}</span>
            </a-form-item>
            <a-form-item label="实付价格">
                <span>{{activeOrder.price}}</span>
            </a-form-item>
        </a-form>
    </a-modal>
</template>

<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    const moment = require('moment')
    export default {
        name: "orderDetail",
        data() {
            return {
                formItemLayout: {
                    labelCol: {
                        xs: { span: 12 },
                        sm: { span: 6 },
                    },
                    wrapperCol: {
                        xs: { span: 24 },
                        sm: { span: 16 },
                    },
                },
            }
        },
        computed: {
            ...mapGetters([
                'orderDetailVisible',
                'activeOrder',
            ]),
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'orderDetail' });
        },
        methods: {
            ...mapMutations([
               'set_orderDetailVisible'
            ]),
            goBack() {
                this.set_orderDetailVisible(false)
            }
        }
    }
</script>
