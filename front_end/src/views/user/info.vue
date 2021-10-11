<style scoped lang="scss">
    .p-user-data {
        margin: 50px;
        padding: 60px;
        text-align: center;
        background-color: white;

        img {
            display: block;
            margin: 20px auto;
            width: 200px;
            height: 200px;
            background-image: url(./components/avatar-cutter/imgs/empty.png);
            background-repeat: round;
        }

        .btnd {
            display: block;
            margin: 0 auto;
            width: 200px;
            height: 40px;
            line-height: 40px;
            font-size: 24px;
            color: white;
            border-radius: 4px;
            text-align: center;
            box-sizing: border-box;
            background-color: #3D8AC7;
        }
    }
</style>
<template>
    <div class="info-wrapper">
        <a-tabs>
            <a-tab-pane tab="我的信息" key="1">
                <a-form :form="form" style="margin-top: 30px">

                    <a-form-item label="用户名" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }">
                        <a-input
                                placeholder="请填写用户名"
                                v-decorator="['userName', { rules: [{ required: true, message: '请输入用户名' }] }]"
                                v-if="modify"
                        />
                        <span v-else>{{ userInfo.userName }}</span>
                    </a-form-item>
                    <a-form-item label="邮箱" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <span>{{ userInfo.email }}</span>
                    </a-form-item>

                    <a-form-item label="手机号" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <a-input
                                placeholder="请填写手机号"
                                v-decorator="['phoneNumber', { rules: [{ required: true, message: '请输入手机号' }] }]"
                                v-if="modify"
                        />
                        <span v-else>{{ userInfo.phoneNumber}}</span>
                    </a-form-item>
                    <a-form-item label="信用值" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <span>{{ userInfo.credit }}</span>
                    </a-form-item>
                    <a-form-item label="密码" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }"
                                 v-if="modify">
                        <a-input
                                placeholder="请输入新密码"
                                v-decorator="['password', { rules: [{ required: true, message: '请输入新密码' }] }]"
                        />
                    </a-form-item>
                    <a-form-item label="更改头像" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }"
                                 v-if="modify">
                        <img :src="imgSrc">
                        <div class="btnd" style="cursor:pointer" @click="showCutter=true">点击上传图片</div>
                        <avatar-cutter v-if="showCutter" @cancel="showCutter = false" return-type="url"
                                       @enter="uploadAvatar"></avatar-cutter>
                    </a-form-item>
                    <a-form-item :wrapper-col="{ span: 12, offset: 5 }" v-if="modify">
                        <a-button type="primary" @click="saveModify">
                            保存
                        </a-button>
                        <a-button type="default" style="margin-left: 30px" @click="cancelModify">
                            取消
                        </a-button>
                    </a-form-item>
                    <a-form-item :wrapper-col="{ span: 8, offset: 4 }" v-else>
                        <a-button type="primary" @click="modifyInfo">
                            修改信息
                        </a-button>
                    </a-form-item>
                </a-form>
            </a-tab-pane>
            <a-tab-pane tab="我的订单" key="2">
                <a-table
                        :columns="columns"
                        :dataSource="userOrderList"
                        bordered
                >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                    <span slot="roomType" slot-scope="text">
                        <span v-if="text == 'BigBed'">大床房</span>
                        <span v-if="text == 'DoubleBed'">双床房</span>
                        <span v-if="text == 'Family'">家庭房</span>
                    </span>
                    <a-tag slot="orderState" color="blue" slot-scope="text">
                        {{ text }}
                    </a-tag>
                    <span slot="action" slot-scope="record">
<!--                        <a-button type="primary" size="small">查看</a-button>-->
                        <a-divider type="vertical" v-if="record.orderState == '已预订'"></a-divider>
                        <a-popconfirm
                                title="你确定撤销该笔订单吗？"
                                @confirm="confirmingCancelOrder(record.id)"
                                @cancel="cancelingCancelOrder"
                                okText="确定"
                                cancelText="取消"
                                v-if="record.orderState == '已预订'"
                        >
                            <a-button type="danger" size="small">撤销</a-button>
                        </a-popconfirm>
                    </span>
                </a-table>
            </a-tab-pane>
        </a-tabs>
    </div>
</template>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
    import AvatarCutter from './components/avatar-cutter/avatar-cutter'
    import macksjs from './mackjs'
    import {mapGetters, mapMutations, mapActions} from 'vuex'
    import {message} from 'ant-design-vue'
    // message.success('here at info.vue')
    const columns = [
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
            scopedSlots: {customRender: 'checkInDate'}
        },
        {
            title: '离店时间',
            dataIndex: 'checkOutDate',
            scopedSlots: {customRender: 'checkOutDate'}
        },
        {
            title: '入住人数',
            dataIndex: 'peopleNum',
        },
        {
            title: '房价',
            dataIndex: 'price',
        },
        {
            title: '状态',
            filters: [{text: '已退房', value: '已退房'}, {text: '已处理', value: '已处理'}, {text: '异常', value: '异常'},
                {text: '已预订', value: '已预订'}, {text: '已撤销', value: '已撤销'}, {text: '已入住', value: '已入住'}],
            onFilter: (value, record) => record.orderState.includes(value),
            dataIndex: 'orderState',
            scopedSlots: {customRender: 'orderState'}
        },
        {
            title: '操作',
            key: 'action',
            scopedSlots: {customRender: 'action'},
        },

    ];
    export default {
        name: 'info',
        data() {
            return {
                modify: false,
                formLayout: 'horizontal',
                pagination: {},
                columns,
                data: [],
                form: this.$form.createForm(this, {name: 'coordinated'}),

                timer: "",//定义一个定时器的变量

                info: '',
                imgSrc: 'http://www.luckly-mjw.cn/baseSource/picture-avatar-03.png',
                showCutter: false, // 是否显示头像裁剪组件
            }
        },
        components: {
            AvatarCutter,
        },
        computed: {
            ...mapGetters([
                'userId',
                'userInfo',
                'userOrderList'
            ])
        },
        async mounted() {
            await this.getUserInfo()
            await this.getUserOrders()
        },
        methods: {
            ...mapActions([
                'getUserInfo',
                'getUserOrders',
                'updateUserInfo',
                'cancelOrder',
                'setfaceurl',
            ]),
            ...mapMutations([
                'set_faceurl',
            ]),
            saveModify() {
                this.form.validateFields((err, values) => {
                    if (!err) {
                        const data = {
                            userName: this.form.getFieldValue('userName'),
                            phoneNumber: this.form.getFieldValue('phoneNumber'),
                            password: this.form.getFieldValue('password')
                        }
                        this.updateUserInfo(data).then(() => {
                            this.modify = false
                        })
                    }
                });
            },
            modifyInfo() {
                setTimeout(() => {
                    this.form.setFieldsValue({
                        'userName': this.userInfo.userName,
                        'phoneNumber': this.userInfo.phoneNumber,
                    })
                }, 0)
                this.modify = true
            },
            cancelModify() {
                this.modify = false
            },
            confirmingCancelOrder(orderId) {
                // message.success('fuck you at vue')
                this.deleteaas();
                this.cancelOrder(orderId)
            },
            cancelingCancelOrder() {

            },
            timeNow: function () {
                var aData = new Date();
                this.timer = aData.getFullYear() + "-" + (aData.getMonth() + 1) + "-" + aData.getDate();
            },
            deleteaas: function () {
                this.timeNow();
                // message.success(this.timer)
                this.$confirm({
                    title: this.timer,
                    mesg: "",
                    cntMsg: '<div class="helAA">hello</div>',
                    cancelValStyle: {color: 'red'},
                    btn: {
                        confirmVal: "确定",
                        cancelVal: "取消"
                    }
                })
                    // this.pause()
                    message.success('撤销时间为' + this.timer)
                    .then(() => {
                        console.log("yes");
                        //点击确定之后的处理

                    })

                    .catch(() => {
                        console.log("no");
                    });


            },
            // pause(){},

            fileChange(e) {
                var that = this;
                var file = e.target.files[0];
                var reader = new FileReader();
                reader.onload = function (e) {
                    that.info = e.target.result;
                }
                //message.success(that.info);
                reader.readAsDataURL(file);
            },
            // submit() {
            //     const data={
            //         portrait: this.info
            //     }
            //     //message.success(this.info)
            //     this.set_faceurl(this.info)
            //     this.setfaceurl()
            // }

            uploadAvatar(src) {
                this.imgSrc = src
                this.showCutter = false
                const data = {
                    id: this.userInfo.id,
                    portrait: this.imgSrc
                }
                //message.success("lbh")
                //message.success(this.imgSrc)
                this.set_faceurl(data)
                this.setfaceurl()
            },

        }
    }
</script>
<style scoped lang="less">
    .info-wrapper {
        padding: 50px;

        .chart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 20px
        }
    }

    .info {
        position: relative;
        width: 168px;
        height: 168px;

        img {
            object-fit: cover;
            object-position: center;
            width: 100%;
            height: 100%;
            border-radius: 50%;
        }

        input {
            position: absolute;
            top: 0;
            right: 0;
            width: 100%;
            height: 100%;
            border-radius: 50%;
            outline: none;
            opacity: 0;
            cursor: pointer;

            &:focus {
                box-shadow: none;
            }
        }

        button {
            width: 100%;
            height: 30px;
            margin-top: 20px;
            text-align: center;
            border: 1px solid #ccc;
            color: #fff;
            font-size: 14px;
            background: pink;
        }
    }
</style>
<style lang="less">
    .info-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">

</style>