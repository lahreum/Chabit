import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/home/Home.vue'
import HomeRanking from '../views/home/HomeRanking.vue'
import HotReviewList from '../views/home/HotReviewList.vue'
import UpcomingChallengeList from '../views/home/UpcomingChallengeList.vue'
import Feed from '../views/feed/Feed.vue'
import MyChallenge from '../views/feed/MyChallenge.vue'
import TodayChallengeList from '../views/feed/TodayChallengeList.vue'
import MyReview from '../views/feed/MyReview.vue'
import ReviewList from '../views/feed/ReviewList.vue'
import Reward from '../views/feed/Reward.vue'
import ReviewDetail from '../views/feed/ReviewDetail.vue'
import ReviewComment from '../views/feed/ReviewComment.vue'
import Login from '../views/user/Login.vue'
import Join from '../views/user/Join.vue'
import Find from '../views/user/Find.vue'
import FindPw from '../views/user/FindPw.vue'
import FindEmail from '../views/user/FindEmail.vue'
import FindEmailFinish from '../views/user/FindEmailFinish.vue'
import ChangePw from '../views/user/ChangePw.vue'
import Setting from '../views/setting/Setting.vue'
import AlarmSetting from '../views/setting/AlarmSetting.vue'
import UserSetting from '../views/setting/UserSetting.vue'
import ModifyUser from '../views/setting/ModifyUser.vue'
import ManageMember from '../views/setting/ManageMember.vue'
import MemberList from '../views/setting/MemberList.vue'
import Challenge from '../views/challenge/Challenge.vue'
import NewChallengeList from '../views/challenge/NewChallengeList.vue'
import HotChallengeList from '../views/challenge/HotChallengeList.vue'
import SearchChallengeList from '../views/challenge/SearchChallengeList.vue'
import CreateChallenge from '../views/challenge/CreateChallenge.vue'
import ChallengeDetail from '../views/challenge/ChallengeDetail.vue'
import Proof from '../views/challenge/Proof.vue'
import FinishProof from '../views/challenge/FinishProof.vue'
import ProofList from '../views/challenge/ProofList.vue'
import WriteChallengeReview from '../views/challenge/WriteChallengeReview.vue'
import Ranking from '../views/ranking/Ranking.vue'
import RankingList from '../views/ranking/RankingList.vue'
import Follow from '../views/follow/Follow.vue'
import FollowList from '../views/follow/FollowList.vue'
import FollowSearchList from '../views/follow/FollowSearchList.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    children: [
      {
        path: 'home-ranking',
        name: 'HomeRanking',
        component: HomeRanking
      },
      {
        path: 'hot-review-list',
        name: 'HotReviewList',
        component: HotReviewList
      },
      {
        path: 'upcoming-challenge-list',
        name: 'UpcomingChallengeList',
        component: UpcomingChallengeList
      },
    ]
  },
  {
    path: '/feed',
    name: 'Feed',
    component: Feed,
    children: [
      {
        path: 'my-challenge',
        name: 'MyChallenge',
        component: MyChallenge,
      },
      {
        path: 'my-challenge/today-challenge-list',
        name: 'TodayChallengeList',
        component: TodayChallengeList
      },
      {
        path: 'my-review',
        name: 'MyReview',
        component: MyReview
      },
      {
        path: 'my-review/review-list',
        name: 'ReviewList',
        component: ReviewList
      },
      {
        path: 'my-review/review-detail',
        name: 'ReviewDetail',
        component: ReviewDetail
      },
      {
        path: 'my-review/review-detail/review-comment',
        name: 'ReviewComment',
        component: ReviewComment
      },
      {
        path: 'reward',
        name: 'Reward',
        component: Reward
      },
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/join',
    name: 'Join',
    component: Join
  },
  {
    path: '/find',
    name: 'Find',
    component: Find,
    children: [
      {
        path: 'find-pw',
        name: 'FindPw',
        component: FindPw
      },
      {
        path: 'find-pw/change-pw',
        name: 'ChangePw',
        component: ChangePw
      },
      {
        path: 'find-email',
        name: 'FindEmail',
        component: FindEmail
      },
      {
        path: 'find-email/find-email-finish',
        name: 'FindEmailFinish',
        component: FindEmailFinish
      },
    ]
  },
  {
    path: '/setting',
    name: 'Setting',
    component: Setting,
    children: [
      {
        path: 'alarm-setting',
        name: 'AlarmSetting',
        component: AlarmSetting
      },
      {
        path: 'user-setting',
        name: 'UserSetting',
        component: UserSetting
      },
      {
        path: 'user-setting/modify-user',
        name: 'ModifyUser',
        component: ModifyUser
      },
      {
        path: 'manage-member',
        name: 'ManageMember',
        component: ManageMember
      },
      {
        path: 'manage-member/member-list',
        name: 'MemberList',
        component: MemberList
      },
    ]
  },
  {
    path: '/challenge',
    name: 'Challenge',
    component: Challenge,
    children: [
      { 
        path: 'new-challenge-list',
        name: 'NewChallengeList',
        component: NewChallengeList
      },
      { 
        path: 'hot-challenge-list',
        name: 'HotChallengeList',
        component: HotChallengeList
      },
      { 
        path: 'search-challenge-list',
        name: 'SearchChallengeList',
        component: SearchChallengeList
      },
      { 
        path: 'create-challenge',
        name: 'CreateChallenge',
        component: CreateChallenge
      },
      { 
        path: 'challenge-detail',
        name: 'ChallengeDetail',
        component: ChallengeDetail
      },
      { 
        path: 'challenge-detail/write-challenge-review',
        name: 'WriteChallengeReview',
        component: WriteChallengeReview
      },
      { 
        path: 'proof',
        name: 'Proof',
        component: Proof
      },
      { 
        path: 'proof/finish-proof',
        name: 'FinishProof',
        component: FinishProof
      },
      { 
        path: 'proof/finish-proof/proof-list',
        name: 'ProofList',
        component: ProofList
      },
    ]
  },
  {
    path: '/ranking',
    name: 'Ranking',
    component: Ranking,
    children: [
      {
        path: 'ranking-list',
        name: 'RankingList',
        component: RankingList
      }
    ]
  },
  {
    path: '/follow',
    name: 'Follow',
    component: Follow,
    children: [
      {
        path: 'follow-list',
        name: 'FollowList',
        component: FollowList
      },
      {
        path: 'follow-search-list',
        name: 'FollowSearchList',
        component: FollowSearchList
      },
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
