import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

export const store = new Vuex.Store({
  // modules,
  plugins: [
    createPersistedState(),
  ],
  state: {        // data
    host: 'http://i4b207.p.ssafy.io/api',
    /*로그인한 회원의 정보*/
    user: {},
    currentChallengeDetailId: 0,
    proofChallengeId: 0,
    writeReviewChallengeId: 0,
    prooflistChallengeId: 0,
    reviewDetailId: 0,
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
    getUserProfileMessage(state) {
      return state.user.userProfileMessage;
    },
    getUserLevel(state) {
      return state.user.userLevel.level;
    },
    getUserMaxPoint(state) {
      return state.user.userLevel.levelMaxPoint;
    },
    getUserLevelImage(state) {
      return state.user.userLevel.levelImage;
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
    SELECTEDCHALLENGE(state, id) {
      state.currentChallengeDetailId = id;
    },
    MOVETOPROOF(state, id) {
      state.proofChallengeId = id;
    },
    MOVETOWRITEREVIEW(state, id) {
      state.writeReviewChallengeId = id;
    },
    MOVETOPROOFLIST(state, id) {
      state.prooflistChallengeId = id;
    },
    MOVETOREVIEWDETAIL(state, id) {
      state.reviewDetailId = id;
    }
  },
  
});