<template>
    <div class="manageUser-wrapper">
        <a-tabs>
            <a-tab-pane tab="账户管理" key="1">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addManager"><a-icon type="plus" />添加账户</a-button>
                </div>
                <a-table
                        :columns="columns"
                        :dataSource="managerList"
                        bordered

                >

                    <span slot="action" slot-scope="record">
                        <a-popconfirm
                                title="确定想删除该用户吗？"
                                @confirm="deleteUser(record.id)"
                                okText="确定"
                                cancelText="取消"
                        >
                            <a-button type="danger" size="small">删除用户</a-button>
                        </a-popconfirm>
                        <a-divider type="vertical"></a-divider>

                        <a-button type="primary" size="small" @click="updateinfo(record)">更改信息</a-button>
                    </span>

                </a-table>
            </a-tab-pane>
        </a-tabs>
        <AddManagerModal></AddManagerModal>
        <UpdateinfoModal></UpdateinfoModal>
    </div>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    import AddManagerModal from './components/addManagerModal'
    import UpdateinfoModal from "./components/updateinfoModal";
    const columns = [
        {
            title:'用户id',
            dataIndex :'id',
        },
        {
            title: '用户邮箱',
            dataIndex: 'email',
        },
        {
            title: '用户名',
            dataIndex: 'userName',
        },
        {
            title: '用户密码',
            dataIndex: 'password',
        },
        {
            title: '用户手机号',
            dataIndex: 'phoneNumber',
        },
        {
            title: '信用值',
            dataIndex: 'credit',
        },
        {
            title:'用户类型',
            dataIndex: 'userType',
        },
        // {
        //     title:'关联酒店',
        //     dataIndex: 'userType'
        // },
        {
            title: '操作',
            key: 'action',
            scopedSlots: { customRender: 'action' },
        },
    ];
    export default {
        name: 'manageHotel',
        data(){
            return {
                formLayout: 'horizontal',
                pagination: {},
                columns,
                data: [],
                form: this.$form.createForm(this, { name: 'manageUser' }),
            }
        },
        components: {
            UpdateinfoModal,
            AddManagerModal
        },
        computed: {
            ...mapGetters([
                'addManagerModalVisible',
                'managerList',
                'updateinfoModalVisible',
                'addRoomModalVisible',
                'activeuserId'
            ])
        },
        mounted() {
            this.getManagerList()
        },
        methods: {
            ...mapActions([
                'getManagerList',
                'delete',
            ]),
            ...mapMutations([
                'set_addManagerModalVisible',
                'set_updateinfoModalVisible',
                'set_addRoomModalVisible',
                'set_activeuserId',
            ]),
            addManager(){
                this.set_addManagerModalVisible(true)
            },
            deleteUser(Id){
                this.delete(Id);
            },
            updateinfo(record) {
                this.set_activeuserId(record.id)
                this.set_updateinfoModalVisible(true)


            },
        }
    }
</script>
<style scoped lang="less">
    .manageUser-wrapper {
        padding: 50px;
        .chart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 20px
        }
    }
</style>
<style lang="less">
    .manageUser-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">

</style>