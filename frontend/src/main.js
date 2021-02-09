import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
import { store } from './vuex/store'

Vue.prototype.$Axios = axios;
Vue.config.productionTip = false

Vue.use(ElementUI);

new Vue({
  router,
  vuetify,
  store,
  render: h => h(App)
}).$mount('#app')
