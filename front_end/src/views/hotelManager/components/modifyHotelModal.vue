<template>
    <a-modal
            :visible="modifyHotelModalVisible"
            title="修改信息"
            okText="确认"
            cancelText="返回"
            @ok="handleSubmit"
            @cancel="cancel"
    >
        <a-form :form="form" v-bind="formItemLayout">
            <a-form-item label="酒店名称">
                <a-input v-decorator="['name', {initialValue: activeHotel.name}]"/>
            </a-form-item>
            <a-form-item label="酒店地址">
                <a-input v-decorator="['address', {initialValue: activeHotel.address}]"/>
            </a-form-item>
            <a-form-item label="所在商圈">
                <span v-if="activeHotel.bizRegion==='XiDan'">西单</span>
            </a-form-item>
            <a-form-item label="预览图片">
                <a-upload :file-list="fileList" :remove="handleRemoveImage" :before-upload="beforeUploadImage">
                    <a-button> <a-icon type="upload" /> 选择图片 </a-button>
                </a-upload>
            </a-form-item>
            <a-form-item label="酒店星级">
                <a-select v-decorator="['hotelStar', {initialValue: activeHotel.hotelStar}]">
                    <a-select-option value="Three">三星级</a-select-option>
                    <a-select-option value="Four">四星级</a-select-option>
                    <a-select-option value="Five">五星级</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="酒店简介">
                <a-input type="textarea" :rows="2" v-decorator="['description', {initialValue: activeHotel.description}]"/>
            </a-form-item>
            <a-form-item label="联系电话">
                <a-input type="number" v-decorator="['phoneNum', {initialValue: activeHotel.phoneNum}]"/>
            </a-form-item>
        </a-form>
    </a-modal>
</template>

<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    import OSS from "ali-oss";
    export default {
        name: "modifyHotelModal",
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
                'modifyHotelModalVisible',
                'activeHotel',
            ]),
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'modifyHotelModal' });
        },
        methods: {
            ...mapMutations([
                'set_modifyHotelParams',
                'set_modifyHotelModalVisible'
            ]),
            ...mapActions([
                'modifyHotel'
            ]),
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
            handleUploadImage() {
                this.client.multipartUpload('hotelImage_' + this.activeHotel.id, this.fileList[0])
            },
            cancel() {
                this.set_modifyHotelModalVisible(false)
            },
            handleSubmit(e) {
                e.preventDefault()
                this.handleUploadImage()
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        // message.success('here at handle submit')
                        const data = {
                            id: this.activeHotel.id,
                            name: this.form.getFieldValue('name'),
                            description: this.form.getFieldValue('description'),
                            address: this.form.getFieldValue('address'),
                            phoneNum: this.form.getFieldValue('phoneNum'),
                            hotelStar: this.form.getFieldValue('hotelStar'),
                            managerId: Number(this.userId)
                        }
                        this.set_modifyHotelParams(data)
                        this.modifyHotel()
                    }
                });
                this.set_modifyHotelModalVisible(false)
            }
        }
    }
</script>

<style scoped>

</style>