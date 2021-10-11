<template>
    <a-modal
            :visible="setLevelRulesModalVisible"
            title="会员等级设置"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="1级晋2级目标" v-bind="formItemLayout">
                <a-input
                        placehoder="请填写目标信用值"
                        v-decorator="['target1', {rules: [{required:true, message:'请输入信用值'}]}]"
                />
            </a-form-item>
            <a-form-item label="2级晋3级目标" v-bind="formItemLayout">
                <a-input
                        placehoder="请填写目标信用值"
                        v-decorator="['target2', {rules:[{required:true, message:'请输入信用值'}]}]"
                />
            </a-form-item>
            <a-form-item label="3级晋4级目标" v-bind="formItemLayout">
                <a-input
                        placehoder="请填写目标信用值"
                        v-decorator="['target3', {rules:[{required:true, message:'请输入信用值'}]}]"
                />
            </a-form-item>
            <a-form-item label="4级晋5级目标" v-bind="formItemLayout">
                <a-input
                        placehoder="请填写目标信用值"
                        v-decorator="['target4', {rules:[{required:true, message:'请输入信用值'}]}]"
                />
            </a-form-item>
            <!--            <div style="width: 100%; text-align: center;margin:20px 0" >-->
            <!--                <a-button type="primary" @click="setLevelRules()">确认设置</a-button>-->
            <!--            </div>-->
        </a-form>
    </a-modal>
</template>

<script>
    import {mapGetters, mapActions, mapMutations} from 'vuex'
    export default {
        name: "setLevelRulesModal",
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
            }
        },
        computed:{
            ...mapGetters([
                // 'setLevelRulesModalVisible',
            ]),

        },
        beforeCreate() {
            this.form = this.$form.createForm(this, {name:'setLevelRulesModal'});
        },
        mounted(){

        },
        methods:{
            ...mapMutations([
                // 'set_setLevelRulesModalVisible'
            ]),
            ...mapActions([
                // 'setLevelRules'
            ]),
            cancel (){
                // this.set_setLevelRulesModalVisible(false)
            },
            changeType(v){

            },
            handleSubmit(e){
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values)=>{
                    if(!err){
                        const data = {
                            target1:Number(this.form.getFieldValue('target1')),
                            target2:Number(this.form.getFieldValue('target2')),
                            target3:Number(this.form.getFieldValue('target3')),
                            target4:Number(this.form.getFieldValue('target4')),
                        }
                        // this.setLevelRules(data)
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>