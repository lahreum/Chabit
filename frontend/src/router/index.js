import Vue from "vue";
import Router from "vue-router";
import challenge from "./challenge";
import feed from "./feed";
import follow from "./follow";
import home from "./home";
import ranking from "./ranking";
import setting from "./setting";
import user from "./user";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [...challenge, ...feed, ...follow, ...home, ...ranking, ...setting, ...user],
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    } else {
      return { x: 0, y: 0 };
    }
  },
});

// const router = new VueRouter({
//   mode: 'history',
//   base: process.env.BASE_URL,
//   routes
// })
