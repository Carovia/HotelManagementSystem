<template>
    <a-modal
            :visible="chargeCreditModalVisible"
            title="信用充值"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="用户邮箱号" v-bind="formItemLayout">
                <a-input
                        placehoder="请填写邮箱"
                        v-decorator="['emailAddr',{rules: [{required: true, message:'请输入邮箱'}]}]"
                />
            </a-form-item>
            <a-form-item label="要充值的信用值" v-bind="formItemLayout">
                <a-input
                        placehoder="请填写信用值"
                        v-decorator="['creditAmount',{rules: [{required: true, message:'请输入信用值'}]}]"
                />
            </a-form-item>
            <!--            <div style="width: 100%; text-align: center;margin:20px 0">-->
            <!--                <a-button type="primary" @click="chargeCredit">确认添加</a-button>-->
            <!--            </div>-->
        </a-form>
    </a-modal>
</template>

<script>
    import {mapMutations, mapActions, mapGetters} from 'vuex'
    export default {
        name: "chargeCreditModal",
        data(){
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
            }
        },
        computed:{
            ...mapGetters([
                'chargeCreditModalVisible'
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, {name:'chargeCreditModal'});
        },
        mounted(){

        },
        methods:{
            ...mapMutations([
                'set_chargeCreditModalVisible',
            ]),
            ...mapActions([
                'chargeCredit'
            ]),
            cancel(){
                this.set_chargeCreditModalVisible(false)
            },
            changeType(v){

            },
            handleSubmit(e){
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values)=>{
                    if(!err){
                        const data = {
                            email:this.form.getFieldValue('emailAddr'),
                            credit:this.form.getFieldValue('creditAmount')
                        }
                        this.chargeCredit(data)
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>