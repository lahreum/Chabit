import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {        // data
    host: 'http://i4b207.p.ssafy.io/api',
    /*로그인한 회원의 정보*/
    user: {},
  },
  getters: {    // computed
    getUserEmail(state) {
      return state.user.userEmail;
    },
    getUserNickname(state) {
      return state.user.userNickname;
    },
    getUserName(state) {
      return state.user.userName;
    },
    getUserPhone(state) {
      return state.user.userPhone;
    },
    getUserPoints(state) {
      return state.user.userPoints;
    },
    getUserHashtags(state) {
      return state.user.hashtags;
    },
    getUserLevel(state) {
      return state.user.userLevel;
    },
    getUserRole(state) {
      return state.user.userRole;
    },
    getUserImage(state) {
      return state.user.userImage;
    },
  },
  mutations: {    // methods
    LOGIN(state, payload) {
      state.user = payload;
    },
    LOGOUT(state) {
      if (state.user) {
        state.user = {};
        alert('로그아웃되었습니다.');
      }
    },
  },
  
});