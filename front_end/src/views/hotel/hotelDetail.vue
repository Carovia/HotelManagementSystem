<template>
    <a-layout>
        <a-layout-content>
            <div class="hotelDetailCard">
                <h1>
                    {{ currentHotelInfo.title }}
                </h1>
                <div class="hotel-info">
                    <a-card style="width: 240px">
                        <img
                                alt="example"
                                :src="currentHotelImg+currentHotelInfo.id"
                                slot="cover"
                                referrerPolicy="no-referrer"
                        />
                    </a-card>
                    <div class="info">
                        <div class="items" v-if="currentHotelInfo.name">
                            <span class="label">酒店名称：</span>
                            <span class="value">{{ currentHotelInfo.name }}</span>
                        </div>
                        <div class="items" v-if="currentHotelInfo.address">
                            <span class="label">地址</span>
                            <span class="value">{{ currentHotelInfo.address }}</span>
                        </div>
                        <div class="items" v-if="currentHotelInfo.rate">
                            <span class="label">评分:</span>
                            <span class="value">{{ currentHotelInfo.rate }}</span>
                        </div>
                        <div class="items" v-if="currentHotelInfo.hotelStar">
                            <span class="label">星级:</span>
                            <a-rate style="font-size: 15px" :value="currentHotelInfo.rate" disabled allowHalf/>
                        </div>
                        <div class="items" v-if="currentHotelInfo.description">
                            <span class="label">酒店简介:</span>
                            <span class="value">{{ currentHotelInfo.description }}</span>
                        </div>

                        <div class="items" v-if="currentHotelInfo.address">
                            <span class="label" font-size="18px">地图:</span>

                        </div>
                        <baidu-map class="map" center="南京市" :zoom="zoom" :scroll-wheel-zoom="true">
                            <bm-local-search :keyword='currentHotelInfo.address' :auto-viewport="true"
                                             style="display: none" :location="location"></bm-local-search>
                        </baidu-map>


                    </div>
                </div>
                <a-divider></a-divider>
                <a-tabs>
                    <a-tab-pane tab="房间信息" key="1">
                        <RoomList :rooms="currentHotelInfo.rooms"></RoomList>
                    </a-tab-pane>
                    <a-tab-pane tab="酒店详情" key="2">
                        <div class="info">
                            <div class="items" v-if="currentHotelInfo.name">
                                <span class="label">酒店名称：</span>
                                <span class="value">{{ currentHotelInfo.name }}</span>
                            </div>
                            <div class="items" v-if="currentHotelInfo.address">
                                <span class="label">地址</span>
                                <span class="value">{{ currentHotelInfo.address }}</span>
                            </div>
                            <div class="items" v-if="currentHotelInfo.rate">
                                <span class="label">评分:</span>
                                <span class="value">{{ currentHotelInfo.rate }}</span>
                            </div>
                            <div class="items" v-if="currentHotelInfo.hotelStar">
                                <span class="label">星级:</span>
                                <a-rate style="font-size: 15px" :value="currentHotelInfo.rate" disabled allowHalf/>
                            </div>
                            <div class="items" v-if="currentHotelInfo.description">
                                <span class="label">酒店简介:</span>
                                <span class="value">{{ currentHotelInfo.description }}</span>
                            </div>
                            <div>
                                <!-- 我是地图demo1 -->
                                <baidu-map class="map" center="南京市" :zoom="zoom" :scroll-wheel-zoom="true">
                                    <bm-local-search :keyword='currentHotelInfo.address' :auto-viewport="true"
                                                     :location="location"></bm-local-search>
                                </baidu-map>

                            </div>
                        </div>
                    </a-tab-pane>
                    <a-tab-pane tab="往期订单" key="3">
                        <a-table
                                :columns="columns"
                                :dataSource="userHotelOrderList"
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
                        <a-divider type="vertical" v-if="record.orderState == '已预订'"></a-divider>
                        <a-popconfirm
                                title="你确定撤销该笔订单吗？"
                                @confirm="confirmCancelOrder(record.id)"
                                @cancel="cancelCancelOrder"
                                okText="确定"
                                cancelText="取消"
                                v-if="record.orderState == '已预订'"
                        >
                            <a-button type="danger" size="small">撤销</a-button>
                        </a-popconfirm>

                    </span>
                        </a-table>
                    </a-tab-pane>
                    <a-tab-pane tab="评价" key="4">
                        <div style="width: 100%; text-align: right;margin:20px 0">
                            <a-button type="primary" @click="addCommentxxx()">
                                <a-icon type="edit"/>
                                我要评价
                            </a-button>
                        </div>
                        <a-table
                                :columns="columns1"
                                :dataSource="commentList"
                                bordered
                        ></a-table>
                    </a-tab-pane>
                </a-tabs>
                <AddComment></AddComment>
            </div>
        </a-layout-content>
    </a-layout>
</template>
<script>
    import {mapGetters, mapActions, mapMutations} from 'vuex'
    import RoomList from './components/roomList'
    import AddComment from '../marketer/components/addComment'
    import { message } from 'ant-design-vue'

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
            filters: [{text: '已预订', value: '已预订'}, {text: '已撤销', value: '已撤销'}, {text: '已入住', value: '已入住'}],
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
    const columns1 = [
        {
            title: '用户Id',
            dataIndex: 'userId',
        },
        {
            title: '评论内容',
            dataIndex: 'contents'
        }
    ]
    export default {
        name: 'hotelDetail',

        components: {
            RoomList,
            AddComment,
        },
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
                modify: false,
                formLayout: 'horizontal',
                pagination: {},
                columns1,
                columns,
                data: [],
                form: this.$form.createForm(this, {name: 'coordinated'}),
                location: '南京',
                keyword: '',
                currentHotelImg: 'https://hotel-management-system.oss-cn-beijing.aliyuncs.com/hotelImage_'

            }
        },

        computed: {
            ...mapGetters([
                'currentHotelInfo',
                'commentList',
                'userId',
                'userInfo',
                'userHotelOrderList'
            ])
        },
        async mounted() {
            this.set_currentHotelId(Number(this.$route.params.hotelId))
            this.getHotelById()
            this.getCommentsByHotelId(Number(this.$route.params.hotelId))
            await this.getUserInfo()
            await this.getUserOrders()
            //message.success(Number(this.$route.params.hotelId))
            this.set_userId(this.userId)
            this.set_hotelId(Number(this.$route.params.hotelId))
            this.getUserHotelOrders()
        },
        beforeRouteUpdate(to, from, next) {
            this.set_currentHotelId(Number(to.params.hotelId))
            this.getHotelById()
            next()
        },
        methods: {
            ...mapMutations([
                'set_currentHotelId',
                'set_addCommentVisible',
                'set_userId',
                'set_hotelId'
            ]),
            ...mapActions([
                'getHotelById',
                'getCommentsByHotelId',
                'getUserInfo',
                'getUserOrders',
                'getUserHotelOrders',
                'updateUserInfo',
                'cancelOrder'
            ]),
            addCommentxxx() {
                this.set_addCommentVisible(true)
            },
            confirmCancelOrder(orderId) {
                this.cancelOrder(orderId)
            },
            cancelCancelOrder() {

            }
        }
    }
</script>
<style scoped lang="less">
    .hotelDetailCard {
        padding: 50px 50px;
    }

    .hotel-info {
        display: flex;
        align-items: stretch;
        justify-content: flex-start;

        .info {
            padding: 10px 0;
            display: flex;
            flex-direction: column;
            margin-left: 20px;

            .items {
                display: flex;
                align-items: center;

                margin-bottom: 10px;

                .label {
                    margin-right: 10px;
                    font-size: 18px;
                }

                .value {
                    margin-right: 15px
                }
            }
        }

    }

    .map {
        width: 200px;
        height: 200px;
    }
</style>