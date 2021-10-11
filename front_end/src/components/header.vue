<template>
    <div class="header">
        <div class="label">
            <img src="@/assets/logo.svg" class="logo" alt="logo" @click="jumpToHome">
            <span class="title">NJUSE 酒店管理系统</span>

        </div>
        <a-menu v-model="current" mode="horizontal" theme="light">
            <a-menu-item key="1" @click="selectMenu">
                <router-link to="/hotel/hotelList">
                    <a-icon type="home" />首页
                </router-link>
            </a-menu-item>
            <a-menu-item key="2" @click="jumpToUserInfo" v-if="userInfo.userType=='Client'">
                <router-link :to="{name:'userInfo'}">
                    <a-icon type="user" />个人中心
<!--                    <a-icon type="switcher" />个人中心-->
                </router-link>
            </a-menu-item>
            <a-menu-item key="3" @click="selectMenu" v-if="userInfo.userType=='HotelManager'">
                <router-link :to="{ name: 'manageHotel'}">
                    <a-icon type="switcher" />酒店管理
                </router-link>
            </a-menu-item>
            <a-menu-item key="4" @click="selectMenu" v-if="userInfo.userType=='Admin'">
                <router-link :to="{ name: 'manageUser'}">
                    <a-icon type="user" />账户管理
                </router-link>
            </a-menu-item>
            <a-menu-item key="5" @click="selectMenu" v-if="userInfo.userType=='Marketer'">
                <router-link :to="{ name: 'manageMarketing'}">
                    <a-icon type="switcher"/>网站营销管理
                </router-link>
            </a-menu-item>
        </a-menu>
        <div class="logout">
            <a-dropdown placement="bottomCenter">
                <div class="user">
                    <img v-bind:src="updd" style="width:50px; height:50px; border-radius:50%;">
                    <!--                    <img v-else src=updd style="width:50px; height:50px; border-radius:50%;">-->
                    <span style="font-size: 14px">{{ userInfo.userName }}</span>
                    <a-icon style="margin-left: 3px; font-size: 16px" type="down"></a-icon>
                </div>
                <a-menu slot="overlay">
                    <a-menu-item  @click="jumpToHome()">
                        <a-icon type="home"></a-icon>
                        首页
                    </a-menu-item>
                    <a-menu-item @click="jumpToUserInfo()">
                        <a-icon type="profile"></a-icon>
                        我的信息
                    </a-menu-item>
                    <a-menu-item @click="quit()">
                        <a-icon type="poweroff"></a-icon>
                        退出登录
                    </a-menu-item>
                </a-menu>
            </a-dropdown>
        </div>
        
    </div>
    
</template>
<script>
    import { mapGetters, mapActions, mapMutations } from 'vuex'
    import {message} from 'ant-design-vue'

    import {getUserInfoAPI} from '@/api/user';
    export default {
        name: '',
        data() {
            return {
                current: ['1'],
                updd: ""
            }
        },
        computed: {
            ...mapGetters([
                'userId',
                'userInfo'
            ])
        },
        mounted() {
            this.userInfo=getUserInfoAPI(this.userId)
            if(this.userInfo.portrait) this.updd=this.userInfo.portrait
            else this.updd='data:image/jpeg;base64,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'
            if (this.$route.name == 'hotelList' || this.$route.name == 'hotelDetail') {
                this.current = ['1']
            }else if(this.$route.name == 'userInfo') {
                this.current = ['2']
            }else if(this.$route.name == 'manageHotel') {
                this.current = ['3']
            }
            else if (this.$route.name == 'manageMarketing'){
                this.current = ['5']
            }
            else {
                this.current = ['4']
            }
        },
        methods: {
            ...mapMutations([

            ]),
            ...mapActions([
                'logout',
                // 'userInfo'
            ]),
            selectMenu(v){

            },
            async quit() {
                await this.$store.dispatch('logout')
                this.$router.push(`/login?redirect=${this.$route.fullPath}`)
            },
            jumpToUserInfo() {
                this.$router.push({ name: 'userInfo', params: { userId: this.userId } })
            },
            jumpToHome() {

            }
        }
    }
</script>
<style scoped lang="less">
    .header {
        display: flex;
        line-height: 44px;
        height: 44px;
        align-items: center;
        justify-content: space-between;
        min-width: 800px;
        .label{
            height: 44px;
            line-height: 44px;
            vertical-align: middle;
            min-width: 400px;

            .logo {
                height: 44px;
                vertical-align: top;
                margin-right: 16px;
                border-style: none;
                cursor: pointer;
            }

            .title {
                font-size: 33px;
                color: rgba(0, 0, 0, .85);
                font-family: Avenir, 'Helvetica Neue', Arial, Helvetica, sans-serif;
                font-weight: 600;
                position: relative;
                top: 2px;
            }

            .search {
                width: 300px;
                margin-left: 30px
            }
        }
        .logout {
            margin-right: 40px;
            .user {
                cursor: pointer;
                display:flex;
                align-items: center;
                span {
                    margin-left: 5px
                }
            }
        }

    }
</style>
<style lang="less">
    .header {
        .ant-menu {
            background: none
        }
    }
</style>