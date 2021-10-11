<template>
    <a-modal
            :visible="addHotelModalVisible"
            title="添加酒店"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="酒店名">
                <a-input
                        placeholder="请填写酒店名称"
                        v-decorator="['hotelName', { rules: [{ required: true, message: '请填写酒店名称' }] }]"
                />
            </a-form-item>
            <a-form-item label="酒店地址" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写酒店地址"
                        v-decorator="['address', { rules: [{ required: true, message: '请填写酒店地址' }] }]"
                />
            </a-form-item>
            <a-form-item label="预览图片">
                <a-upload :file-list="fileList" :multiple="false" :remove="handleRemoveImage" :before-upload="beforeUploadImage"
                          v-decorator="['Image', { rules: [{ required: true, message: '请选择预览图片' }] }]">
                    <a-button> <a-icon type="upload" /> 选择图片 </a-button>
                </a-upload>
                <a-upload :file-list="initialFileList"></a-upload>
            </a-form-item>
            <a-form-item label="酒店星级" v-bind="formItemLayout">
                <a-select
                        v-decorator="[
                    'hotelStar',
                    { rules: [{ required: true, message: '请选择酒店星级' }] }]"
                        @change="changeStar"
                >
                    <a-select-option value="Three">三星级</a-select-option>
                    <a-select-option value="Four">四星级</a-select-option>
                    <a-select-option value="Five">五星级</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="手机号" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写手机号"
                        v-decorator="['phoneNumber', { rules: [{ required: true, message: '请输入手机号' }] }]"
                />
            </a-form-item>
            <a-form-item label="酒店简介" v-bind="formItemLayout">
                <a-input
                        type="textarea"
                        :rows="4"
                        placeholder="请填写酒店简介"
                        v-decorator="['description', { rules: [{ required: true, message: '请填写酒店简介' }] }]"
                />
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
    import {mapGetters, mapMutations, mapActions} from 'vuex'
    import OSS from "ali-oss";
    import {message} from 'ant-design-vue'
    export default {
        name: 'addHotelModal',
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
                initialFileList: [
                    {
                        uid: '-1',
                        name: '默认图片下载',
                        status: 'done',
                        url: 'https://hotel-management-system.oss-cn-beijing.aliyuncs.com/cover.jpeg'
                    },
                ],
                fileList: [],
                client: new OSS({
                    region: 'oss-cn-beijing',
                    accessKeyId: 'LTAI4GEPgp3EqRVWkYdF7scN',
                    accessKeySecret: 'zHPU25vNL1I5jMqaLpPpebP4qQd3Xx',
                    bucket: 'hotel-management-system'
                })
            }
        },
        computed: {
            ...mapGetters([
                'userId',
                'addHotelParams',
                'addHotelModalVisible'
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, {name: 'addHotelModal'});
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_addHotelParams',
                'set_addHotelModalVisible',
                'get_activeHotelId',
            ]),
            ...mapActions([
                'addHotel'
            ]),
            cancel() {
                this.set_addHotelModalVisible(false)
            },
            changeStar(v) {

            },
            handleRemoveImage(file) {
                const index = this.fileList.indexOf(file);
                const newFileList = this.fileList.slice();
                newFileList.splice(index, 1);
                this.fileList = newFileList;
            },
            beforeUploadImage(file) {
                this.fileList = [...this.fileList, file];
                return false;
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        // message.success('here at handle submit')
                        const data = {
                            name: this.form.getFieldValue('hotelName'),
                            description: this.form.getFieldValue('description'),
                            address: this.form.getFieldValue('address'),
                            phoneNum: this.form.getFieldValue('phoneNumber'),
                            hotelStar: this.form.getFieldValue('hotelStar'),
                            managerId: Number(this.userId)
                        }
                        this.set_addHotelParams(data)
                        this.set_addHotelModalVisible(false)
                        this.addHotel()
                        const id = this.get_activeHotelId()
                        this.client.multipartUpload('hotelImage_' + this.id, this.fileList[0])
                    }
                });

            },
        }
    }
</script>