<template>
    <div>
        <a-model
                :visible="webCouponVisible"
                title="网站优惠策略"
                width="900px"
                :footer="null"
                @cancel="cancel"
        >
            <div style="width: 100%; text-align: right; margin:20px 0">
                <a-button type="primary" @click="addWebCouponxxx">
                    <a-icon type="plus"/>
                    添加优惠策略
                </a-button>
            </div>
            <a-table
                    :columns="columns"
                    :dataSource="couponList"
                    bordered
            >
                <a-tag color="red" slot="couponName" slot-scope="text">
                    {{text}}
                </a-tag>
            </a-table>
        </a-model>
    </div>
</template>

<script>
    import {mapGetters, mapMutations, mapActions} from 'vuex'
    import AddWebCoupon from'./addWebCoupon'
    export default {
        name: "webCoupon",
        data(){

        },
        components:{
            // eslint-disable-next-line vue/no-unused-components
            AddWebCoupon,
        },
        computed:{
            ...mapGetters([
                'webCouponVisible',
                'webCouponList',
            ]),
        },
        methods:{
            ...mapMutations([
                'set_addWebCouponVisible',
                'set_webCouponVisible',
            ]),
            ...mapActions([
                'getWebCoupon',
            ]),
            cancel(){
                this.set_webCouponVisible(false)
            },
            addWebCouponxxx(){
                this.set_addWebCouponVisible(true)
                // this.set_webCouponVisible(false)
            }
        }
    }
</script>

<style scoped>

</style>