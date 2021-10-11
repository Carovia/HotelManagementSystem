<template>
    <a-modal
            :visible="addWebCouponVisible"
            title="添加网站优惠策略"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >

        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="优惠券类型" v-bind="formItemLayout">
                <a-select
                        v-decorator="[
                    'type',
                    { rules: [{ required: true, message: '请选择类型' }] }]"
                        @change="changeType"
                >
                    <a-select-option value="5">限时特惠</a-select-option>
                    <a-select-option value="6">VIP商圈特惠</a-select-option>
                    <a-select-option value="7">会员目标积分特惠</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="券名" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写券名"
                        v-decorator="['name',{rules:[{required: true,  message:'请输入券名'}]}]"
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
            <a-form-item label="商圈" v-bind="formItemLayout" v-if="couponType == 6">
                <a-select
                        v-decorator="[
                            'bizReg',
                            {rules:[{required:true, message:'请选择商圈'}]}]"
                        @change="changeBizReg"
                >
                    <a-select-option value="XiDan">西单</a-select-option>
                    <a-select-option value="XinJieKou">新街口</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="目标积分" v-bind="formItemLayout" v-if="couponType === '7'">
                <!--                <a-select-->
                <!--                        v-decorator="[-->
                <!--                            'level',-->
                <!--                            {ryles:[{required:true, message:'请选择目标积分'}]}-->
                <!--                        ]"-->
                <!--                        @change="changeLevel"-->
                <!--                >-->
                <!--                    <a-select-option value="1">1级</a-select-option>-->
                <!--                    <a-select-option value="2">2级</a-select-option>-->
                <!--                    <a-select-option value="3">3级</a-select-option>-->
                <!--                    <a-select-option value="4">4级</a-select-option>-->
                <!--                    <a-select-option value="5">5级</a-select-option>-->
                <!--                </a-select>-->
                <a-input
                        placeholder="请填写目标积分"
                        v-decorator="['target',{rules:[{required: true,  message:'请输目标积分'}]}]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="有效时间" v-if="couponType === '5'">
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
            <a-form-item label="折扣" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写折扣"
                        v-decorator="['discount', { rules: [{ message: '请输入折扣' }] }]"
                />
            </a-form-item>
        </a-form>
    </a-modal>
</template>

<script>
    import {mapGetters, mapMutations, mapActions} from 'vuex'
    import {message} from 'ant-design-vue'

    const moment = require('moment')
    export default {
        name: 'addWebCouponModal',
        data() {
            return {
                formItemLayout: {
                    labelCol: {
                        xs: {span: 12},
                        sm: {span: 6},
                    },
                    wrapperCol: {
                        xs: {span: 24},
                        sm: {span: 16},
                    },
                },
                couponType: -1,
                bizReg:'',
                level:-1,
            }
        },
        computed: {
            ...mapGetters([
                'addWebCouponVisible',
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, {name: 'addWebCouponModal'});
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_addWebCouponVisible'
            ]),
            ...mapActions([
                'addWebTimeCoupon',
                'addWebVipRegCoupon',
                'addWebCreditCoupon',
            ]),
            cancel() {
                this.set_addWebCouponVisible(false)
            },
            changeType(v) {
                this.couponType = v
            },
            changeBizReg(v){
                this.bizReg = v
            },
            // changeLevel(v){
            //     this.level = v
            // },
            handleSubmit(e) {
                e.preventDefault();
                // message.success('Fuck')
                this.form.validateFieldsAndScroll((err, values) => {
                    // message.success('Fuck')
                    if (!err) {
                        // message.success('Fuck')
                        const data = {

                            type: Number(this.form.getFieldValue('type')),
                            name: this.form.getFieldValue('name'),
                            description: this.form.getFieldValue('description'),
                            startTime: '2020-01-01T00:00:00',
                            endTime: '2100-01-01T23:59:59',
                            discount: 0,
                            bizReg: '',
                            creditRequired: null,
                            targetMoney: 0,
                            discountMoney: 0,
                            hotelId: -1,
                            status: 1,
                        }
                        // message.success('Fuck')
                        // message.success(data.type)
                        if (data.type === 5) {
                            data.startTime = moment(this.form.getFieldValue('date')[0]).format('YYYY-MM-DD') + 'T00:00:00'
                            data.endTime = moment(this.form.getFieldValue('date')[1]).format('YYYY-MM-DD') + 'T23:59:59'
                            data.discount = Number(this.form.getFieldValue('discount'))
                            // message.success('Fuck')
                            this.addWebTimeCoupon(data)
                        }
                        if (data.type === 6) {
                            data.bizReg = this.form.getFieldValue('bizReg')
                            data.discount = Number(this.form.getFieldValue('discount'))
                            // data.discount = Number(this.form.getFieldValue('discount'))
                            this.addWebVipRegCoupon(data)
                        }
                        if (data.type === 7) {
                            data.creditRequired = Number(this.form.getFieldValue('target'))
                            data.discount = Number(this.form.getFieldValue('discount'))
                            // message.success(data.creditRequired)
                            // message.success(this.form.getFieldValue('target'))
                            // data.discount = Number(this.form.getFieldValue('discount'))
                            this.addWebCreditCoupon(data)
                        }

                    }

                })
            }
        }
    }
</script>

<style scoped>

</style>