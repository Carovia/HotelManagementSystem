<template>
    <div class="hotelList">
        <a-layout>
            <a-layout-content style="min-width: 800px">
                <a-spin :spinning="hotelListLoading">
                    <div class="card-wrapper" v-if="unList">
                        <HotelCard :hotel="item" v-for="item in hotelList" :key="item.index" @click.native="jumpToDetails(item.id)"></HotelCard>
                        <div v-for="item in emptyBox" :key="item.name" class="emptyBox ant-col-xs-7 ant-col-lg-5 ant-col-xxl-3">
                        </div>
                        <a-pagination showQuickJumper :total="hotelList.totalElements" :defaultCurrent="1" @change="pageChange"></a-pagination>
                        <a-button type="primary" @click="changeL">切换显示方式</a-button>
                    </div>
                    <div class="card-wrapper" v-else>
                        <table id="customers" border="0.5" width="100%">
                            <tr>
                                <th style="text-align: center">酒店编号</th>
                                <th style="text-align: center">地址</th>
                                <th style="text-align: center">星级</th>
                                <th style="text-align: center">酒店名称</th>
                                <th style="text-align: center">服务热线</th>
                                <th style="text-align: center">查看详情</th>
                                <th style="text-align: center" v-if="!default_orderd">我要评分(双击有效)</th>
                                <th style="text-align: center" v-if="!default_orderd">提交</th>

                            </tr>
                            <tr v-for="item in sortHotel" :key="item.id" class="alt">
                                <td>{{item.id}}</td>
                                <td>{{item.address}}</td>
                                <td>{{item.rate}}</td>
                                <td>{{item.name}}</td>
                                <td>{{item.phoneNum}}</td>
                                <td>
                                    <a @click="jumpToDetails(item.id)">生成订单</a>
                                </td>
                                <td v-if="!default_orderd">
                                    <p class="all">
                                        <input type="radio" name="a" value="0" v-model="inputdata"/>
                                        <span>★</span>

                                        <input type="radio" name="a" value="1" v-model="inputdata"/>
                                        <span>★</span>

                                        <input type="radio" name="a" value="2" v-model="inputdata"/>
                                        <span>★</span>

                                        <input type="radio" name="a" value="3" v-model="inputdata"/>
                                        <span>★</span>

                                        <input type="radio" name="a" value="4" v-model="inputdata"/>
                                        <span>★</span>

                                        <input type="radio" name="a" value="5" v-model="inputdata"/>
                                        <span>★</span>
                                    </p>
                                </td>
                                <a-button type="primary" style="text-align: center" @click="show_submit_score(item.name, item.id)" v-if="!default_orderd">我要提交</a-button>
                            </tr>
                        </table>
                        <a-button type="primary" @click="changeL">{{default_show}}</a-button>
                        <a-button type="primary" @click="change_sort">{{sortText}}</a-button>
                        <a-button type="primary" @click="deleteaas">显示当前时间</a-button>
                        <a-button type="primary" @click="change_orderd">{{default_text}}</a-button>

                    </div>
                </a-spin>
            </a-layout-content>
        </a-layout>
    </div>
</template>
<script>
    import HotelCard from './components/hotelCard'
    import { mapGetters, mapActions, mapMutations } from 'vuex'
    import{message} from 'ant-design-vue'
    //import macksjs from './mackjs'

    export default {
        name: 'home',
        components: {
            HotelCard,
        },
        data(){
            return{
                active: '',
                default_orderd: true,
                default_text: '我预订过的',
                default_show: '切换显示方式',
                unList: true,
                sortrate: false,
                sortText: '按照星级排序',
                emptyBox: [{ name: 'box1' }, { name: 'box2'}, {name: 'box3'}],

                timer: "",
                inputdata: ''
            }
        },
        async mounted() {
            await this.getHotelList()
            this.set_userId(Number(this.userId))
            this.getuserorderdhotelList()
        },
        computed: {
            ...mapGetters([
                'userId',
                'hotelList',
                'hotelListLoading',
                'userorderdhotelList',
                'addrateRaramVisible'
            ]),
            sortHotel: function(){
                if(this.default_orderd){
                    if(this.sortrate)
                        return this.sort_rate(this.hotelList, 'rate');
                    else
                        return this.sort_rate(this.hotelList, 'id');
                }
                else{
                    if(this.sortrate)
                        return this.sort_rate(this.userorderdhotelList, 'rate');
                    else
                        return this.sort_rate(this.userorderdhotelList, 'id');
                }
            }
        },
        watch: {
            inputdata(value) {
                console.log(value)
            }
        },
        methods: {
            ...mapMutations([
                'set_hotelRate',
                'set_hotelListParams',
                'set_hotelListLoading',
                'set_userId'
            ]),
            ...mapActions([
                'sethotelRate',
                'getHotelList',
                'getuserorderdhotelList'
            ]),
            change_orderd: function(){
                this.default_orderd=!this.default_orderd;
                if(this.default_orderd){
                    this.default_text='我预订过的';
                }
                else{
                    this.default_text='所有酒店';
                }
            },
            pageChange(page, pageSize) {
                const data = {
                    pageNo: page - 1
                }
                this.set_hotelListParams(data)
                this.set_hotelListLoading(true)
                this.getHotelList()
            },
            jumpToDetails(id){
                this.$router.push({ name: 'hotelDetail', params: { hotelId: id }})
            },

            changeL(){
                if(this.unList)
                    this.default_show='恢复显示方式';
                else
                    this.default_show='切换显示方式';
                this.unList=!this.unList
            },
            mouseOver: function(){
                this.active = 'background-color: #cccccc';
            },
            mouseLeave: function () {
                this.active = '';
            },
            sort_rate: function(array, key){
                return array.sort(function(a,b){
                    var x=a[key];
                    var y=b[key];
                    return ((x<y)?-1:((x>y)?1:0));
                });
            },
            change_sort: function(){
                if(!this.sortrate){
                    this.sortText='恢复默认排序';
                }
                else{
                    this.sortText='按照星级排序';
                }
                this.sortrate=!this.sortrate;
            },
            timeNow: function() {
                var aData = new Date();
                this.timer = aData.getFullYear() + "-" + (aData.getMonth() + 1) + "-" + aData.getDate();
            },
            deleteaas: function() {
                this.timeNow();
                this.$confirm({
                    title: this.timer,
                    mesg: "",
                    cntMsg: '<div class="helAA">hello</div>',
                    cancelValStyle:{color:'red'},
                    btn: {
                        confirmVal: "确定",
                        cancelVal: "取消"
                    }
                })
                    .then(() => {
                        console.log("yes");
                        //点击确定之后的处理
                    })
                    .catch(() => {
                        console.log("no");
                    });
            },
            submit_score: function(name, id){
                const data={
                    id: Number(id),
                    rate: Number(this.inputdata)
                }
                this.set_hotelRate(data)
                this.sethotelRate()
            },
            show_submit_score: function(name, id){

                this.$confirm({

                    title: '您的评分为'+this.inputdata + '分，    现在成功提交给' + name,
                    mesg: "",
                    cntMsg: '<div class="helAA">hello</div>',
                    cancelValStyle:{color:'red'},
                    btn: {
                        confirmVal: "确定",
                        cancelVal: "取消"
                    }
                })

                this.submit_score(name, id)

            }
        }
    }
</script>
<style scoped lang="less">
    .hotelList {
        text-align: center;
        padding: 50px 0;
        .emptyBox {
            height: 0;
            margin: 10px 10px
        }
        .card-wrapper{
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
            flex-grow: 3;
            min-height: 800px
        }
        .card-wrapper .card-item {
            margin: 30px;
            position: relative;
            height: 188px;
        }
    }
</style>
<style type="text/css">
    #customers
    {
        text-align:center;
        font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
        width:100%;
        border-collapse:collapse;
    }

    #customers td, #customers th
    {
        text-align:center;
        font-size:1em;
        border:1px #98bf21;
        padding:3px 7px 2px 7px;
    }

    #customers th
    {
        text-align:center;
        font-size:1.1em;
        text-align:left;
        padding-top:5px;
        padding-bottom:4px;
        background-color: #4c98c9;
        color:#ffffff;
        height: 70px;
    }

    #customers tr.alt td
    {
        font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
        text-align:center;
        color:#000000;
        background-color: rgba(242, 240, 241, 0.98);
        height: 50px;
    }
</style>
<style>

    .all>input{opacity:0;position:absolute;width:2em;height:2em;margin:0;}

    .all>input:nth-of-type(1),

    .all>span:nth-of-type(1){display:none;}

    .all>span{font-size:2em;color:gold;

        -webkit-transition:color

        .2s;

        transition:color

        .2s;

    }

    .all>input:checked~span{color: #eeeeee;}

    .all>input:checked+span{color:gold;}
</style>
