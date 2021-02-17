import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

export const store = new Vuex.Store({
  // modules,
  plugins: [createPersistedState()],
  state: {
    // data
    host: 'https://i4b207.p.ssafy.io/api',
    /* 로그인한 회원의 정보 */
    user: {},
    /* 선택한 다른 유저의 정보 */
    you: {},
    currentChallengeDetailId: 0,
    proofChallengeId: 0,
    writeReviewChallengeId: 0,
    prooflistChallengeId: 0,
    reviewDetailId: 0,
    typeOfChallenge: '',
  },
  getters: {  // computed
    /* 로그인한 유저의 정보 */
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
    /* 선택한 다른 유저의 정보 */
    getYourEmail(state) {
      return state.you.userEmail;
    },
    getYourNickname(state) {
      return state.you.userNickname;
    },
    getYourName(state) {
      return state.you.userName;
    },
    getYourPhone(state) {
      return state.you.userPhone;
    },
    getYourPoints(state) {
      return state.you.userPoints;
    },
    getYourHashtags(state) {
      return state.you.hashtags;
    },
    getYourProfileMessage(state) {
      return state.you.userProfileMessage;
    },
    getYourLevel(state) {
      return state.you.userLevel.level;
    },
    getYourMaxPoint(state) {
      return state.you.userLevel.levelMaxPoint;
    },
    getYourLevelImage(state) {
      return state.you.userLevel.levelImage;
    },
    getYourRole(state) {
      return state.you.userRole;
    },
    getYourImage(state) {
      return state.you.userImage;
    },
  },
  mutations: {  // methods
    
    LOGIN(state, payload) {
      state.user = payload;
    },
    LOGOUT(state) {
      if (state.user) {
        state.user = {};
        alert('로그아웃되었습니다.');
      }
    },
    SETYOURINFO(state, payload) { // 다른 유저 정보 선택하면 저장
      state.you = payload;
      // window.location.reload();
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
    },
    MOVETOPERSONALCHALLENGE(state, type) {
      state.typeOfChallenge = type;
    },
    UPDATEUSERINFO(state, payload) {
      state.user.userNickname = payload.userNickname;
      state.user.userPassword = payload.userPassword;
      state.user.userPhone = payload.userPhone;
    },
    UPDATEUSERAVATAR(state, payload) {
      state.user.userImage = payload;
    },
  },
});
