<template>
    <a-modal
            :visible="updateinfoModalVisible"
            title="更改信息"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >

        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="用户名" v-bind="formItemLayout">
                <a-input
                        placeholder="请输入新的用户名"
                        v-decorator="['userName', { rules: [{ required: true, message: '请输入新的用户名' }] }]"
                />
            </a-form-item>
<!--            <a-form-item label="邮箱" v-bind="formItemLayout">-->
<!--                <a-input-->
<!--                        placeholder="请输入新的邮箱"-->
<!--                        v-decorator="['email', { rules: [{ required: true, message: '请输入新的邮箱' }] }]"-->
<!--                />-->
<!--            </a-form-item>-->
            <a-form-item label="手机号" v-bind="formItemLayout">
                <a-input
                        placeholder="请输入新的手机号"
                        v-decorator="['phoneNumber', { rules: [{ required: true, message: '请填写新的手机号' }] }]"
                />
            </a-form-item>
            <a-form-item label="密码" v-bind="formItemLayout">
                <a-input
                        placeholder="请输入新密码"
                        v-decorator="['password', { rules: [{ required: true, message: '请填写新密码' }] }]"
                />
            </a-form-item>
        </a-form>


    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'updateinfoModal',
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
                'updateinfoModalVisible',
                'activeuserId'
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'updateModal' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_updateinfoModalVisible',
                'set_updateParams'
            ]),
            ...mapActions([
                'updateinfo',
                'getManagerList',
            ]),
            cancel() {
                this.set_updateinfoModalVisible(false)
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            userName: this.form.getFieldValue('userName'),
                            email: this.form.getFieldValue('email'),
                            phoneNumber: this.form.getFieldValue('phoneNumber'),
                            password: this.form.getFieldValue('password'),
                            userId: this.activeuserId,
                        }
                        this.set_updateParams(data)
                        this.updateinfo()
                    }
                });
            },
        }
    }
</script>