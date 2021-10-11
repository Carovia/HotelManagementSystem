<template>
    <a-modal
            :visible="addCommentVisible"
            title="添加评价"
            cancelText="取消"
            okText="提交"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="你的评价" v-bind="formItemLayout">
                <a-input
                        type="textarea"
                        :rows="4"
                        placeholder="请填写评价"
                        v-decorator="['comment', { rules: [{ required: true, message: '请填写评价' }] }]"
                />
            </a-form-item>
        </a-form>
    </a-modal>
</template>

<script>
    import {mapGetters, mapActions, mapMutations} from 'vuex'
    import {message} from 'ant-design-vue'
    import userInfo from "../../../store/getters";

    export default {
        name: "addComment",
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
        computed: {
            ...mapGetters([
                'addCommentVisible',
                'userId',
                'currentHotelId'
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, {name: 'addCommentModal'})
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_addCommentVisible',
            ]),
            ...mapActions([
                'addComment'
            ]),
            cancel() {
                this.set_addCommentVisible(false)
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            contents:this.form.getFieldValue('comment'),
                            userId:this.userId,
                            hotelId:this.$route.params.hotelId,
                        }
                        this.addComment(data)
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>