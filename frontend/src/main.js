import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import vuetify from "./plugins/vuetify";
import Vuelidate from "vuelidate";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import axios from "axios";
import { store } from "./vuex/store"; // vuex 전역 세팅
import SequentialEntrance from 'vue-sequential-entrance'
import 'vue-sequential-entrance/vue-sequential-entrance.css'

Vue.prototype.$Axios = axios; // this.$Axios 로 전역으로 사용 가능
Vue.config.productionTip = false;
Vue.use(Vuelidate);
Vue.use(SequentialEntrance);
Vue.use(ElementUI);

new Vue({
  router,
  vuetify,
  store,
  render: (h) => h(App),
}).$mount("#app");
