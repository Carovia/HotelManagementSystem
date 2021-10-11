<template>
    <div class="mack" v-if="isShow">
        <div class="mackWeb" :style="text.mackStyle">
            <div class="title font_b" v-if="text.title" :style="text.titleStyle">{{text.title.trim()}}</div>
            <div class="mesg font_s" v-if="text.mesg" :style="text.mesgStyle">{{text.mesg.trim()}}</div>
            <div v-html="text.cntMsg"></div>
            <div class="footb font_s">
                <div
                        class="foot_l borderlf borderTop"
                        @click="cancel"
                        v-if="text.cancel"
                        :style="text.cancelValStyle"
                >{{text.btn.cancelVal}}</div>
                <div
                        class="foot_r borderTop"
                        @click="confirm"
                        v-if="text.confirm"
                        :style="text.confirmValStyle"
                >{{text.btn.confirmVal}}</div>
            </div>
        </div>
    </div>
</template>

//写js
<script>
    export default {
        data() {
            return {
                isShow: true,
                text: {
                    title: "",
                    mesg: "",
                    cnTmesg: "",
                    cancel: true,
                    confirm: true,
                    mackStyle: null,
                    titleStyle: null,
                    mesgStyle:null,
                    cancelValStyle: null,
                    confirmValStyle: null,
                    btn: {
                        confirmVal: "确定",
                        cancelVal: "取消"
                    }
                }
            };
        },
        methods: {
            cancel() {
                this.isShow = false;
            },
            confirm() {
                this.isShow = false;
            }
        }
    };
</script>

//css
<style scoped lang='less'>
    .mack {
        position: fixed;
        width: 100%;
        height: 100%;
        overflow: hidden;
        top: 0;
        left: 0;
        background: rgba(21, 21, 21, 0.7);
        .font_b {
            font-size: 14/50rem;
            color: #231a2f;
        }
        .font_s {
            font-size: 12/50rem;
            color: #655a72;
        }
        .borderTop {
            border-top: 1/50rem solid #e4e4e4;
        }
        .mackWeb {
            background: #ffffff;
            width: 300/50rem;
            min-width: 300/50rem;
            margin: auto;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            .title {
                text-align: center;
                padding: 15/50rem 15/50rem 0 15/50rem;
            }
            .mesg {
                padding: 15/50rem;
                text-align: center;
            }
            .footb {
                display: inline-table;
                width: 100%;
                .borderlf {
                    border-right: 1/50rem solid #e4e4e4;
                }
                div {
                    display: table-cell;
                    box-sizing: border-box;
                    text-align: center;
                    padding: 10/50rem 0;
                }
            }
        }
    }
</style>