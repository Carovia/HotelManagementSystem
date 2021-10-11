import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import store from './store'
import BaiduMap from 'vue-baidu-map'
import '@/permission'
Vue.config.productionTip = false
Vue.use(BaiduMap, {
  /* 需要注册百度地图开发者来获取你的ak */
  ak: 'q9jzcHrG6a1RiZ6XeY9wED5i9jvfliHo'
})
const options = {
  namespace: 'pro__',
  name: 'ls',
  storage: 'local'
}
Vue.use(Antd)

Vue.directive('title', {
  inserted: function (el, binding) {
    document.title = el.dataset.title
  }
})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
