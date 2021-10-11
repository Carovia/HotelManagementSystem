<template>
    <a-modal
            :visible="addCouponVisible"
            title="添加优惠策略"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <!-- 这里是添加策略模态框区域，请编写表单 -->
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="优惠券类型" v-bind="formItemLayout">
                <a-select
                        v-decorator="[
                    'type',
                    { rules: [{ required: true, message: '请选择类型' }] }]"
                        @change="changeType"
                >
<!--                    <a-select-option value="1">生日特惠</a-select-option>-->
                    <a-select-option value="2">多间特惠</a-select-option>
                    <a-select-option value="3">满减特惠</a-select-option>
                    <a-select-option value="4">限时特惠</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="券名" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写券名"
                        v-decorator="['name', { rules: [{ required: true, message: '请输入券名' }] }]"
                />
            </a-form-item>
            <a-form-item label="优惠简介" v-bind="formItemLayout">
                <a-input
                        type="textarea"
                        :rows="4"
                        placeholder="请填写优惠简介"
                        v-decorator="['description', { rules: [{ required: true, message: '请填写优惠简介' }] }]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="有效时间" v-if="couponType === '4'">
                <a-range-picker
                        format="YYYY-MM-DD"
                        v-decorator="[
                        'date',
                        {
                            rules: [{ required: true, message: '请选择有效时间' }]
                        }
                    ]"
                        :placeholder="['可用时间','失效时间']"
                />
            </a-form-item>
            <a-form-item label="折扣" v-bind="formItemLayout" v-if="couponType !== '3'">
                <a-input
                        placeholder="请填写折扣"
                        v-decorator="['discount', { rules: [{ message: '请输入折扣' }] }]"
                />
            </a-form-item>
            <a-form-item label="达标金额" v-bind="formItemLayout" v-if="couponType === '3'">
                <a-input
                        placeholder="请填写达标金额"
                        v-decorator="['targetMoney', { rules: [{ required: true, message: '请输入达标金额' }] }]"
                />
            </a-form-item>
            <a-form-item label="优惠金额" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写优惠金额"
                        v-decorator="['discountMoney', { rules: [{ message: '请输入优惠金额' }] }]"
                />
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    const moment = require('moment')
    export default {
        name: 'addCouponModal',
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
                couponType: -1,
            }
        },
        computed: {
            ...mapGetters([
                'activeHotelId',
                'addCouponVisible',
            ])
        },
        beforeCreate() {
            // 表单名默认为“form”
            this.form = this.$form.createForm(this, { name: 'addCouponModal' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_addCouponVisible'
            ]),
            ...mapActions([
                // addHotelCoupon：添加酒店策略接口
                'addHotelCoupon'
            ]),
            cancel() {
                this.set_addCouponVisible(false)
            },
            changeType(v){
                this.couponType = v
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        var data = {
                            // 这里添加接口参数
                            type: Number(this.form.getFieldValue('type')),
                            name: this.form.getFieldValue('name'),
                            description: this.form.getFieldValue('description'),
                            startTime: '2020-01-01T00:00:00',
                            endTime: '2100-01-01T23:59:59',
                            discount: 0,
                            targetMoney: 0,
                            discountMoney: Number(this.form.getFieldValue('discountMoney')),
                            hotelId: Number(this.activeHotelId),
                            status: 1,
                        }
                        if (data.type === 3) {
                            data.targetMoney = Number(this.form.getFieldValue('targetMoney'))
                        }
                        if (data.type === 4) {
                            data.startTime = moment(this.form.getFieldValue('date')[0]).format('YYYY-MM-DD') + 'T00:00:00'
                            data.endTime = moment(this.form.getFieldValue('date')[1]).format('YYYY-MM-DD') + 'T23:59:59'
                        }
                        if (data.type !== 3) {
                            data.discount = Number(this.form.getFieldValue('discount'))
                        }
                        this.addHotelCoupon(data)
                    }
                });
            },
        }
    }
</script>