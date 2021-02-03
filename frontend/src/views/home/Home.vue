<template>
  <div class="container">
    <div class="top-toggle-alarm">
      <i class="fas fa-grip-lines"></i>
      <i class="far fa-bell"></i>
      <i class="fas fa-circle"></i>
    </div>
    <div class="all-ranking">
      <span>전체 랭킹</span>
      <i class="fas fa-plus"></i>
      <div class="all-ranking-list">
        <HomeRanking />
      </div>
    </div>
    <div class="hot-challenge">
      <span class="hot-popular">HOT</span><span>챌린지</span>
      <i class="fas fa-plus"></i>
      <div class="hot-challenge-list">
        <UpcomingChallengeList />
      </div>
    </div>
    <div class="hot-review">
      <span class="hot-popular">인기</span><span>리뷰</span>
      <div class="hot-review-list">
        <HotReviewList />
      </div>
    </div>
    <div class="deadline-challenge">
      <span class="hot-popular">마감임박</span><span>챌린지</span>
      <div class="deadline-challenge-list">
        <DeadlineChallengeList />
      </div>
    </div>
  </div>
</template>

<script>
import "./home.css"
import HomeRanking from "./HomeRanking.vue"
import HotReviewList from './HotReviewList.vue'
import UpcomingChallengeList from "./UpcomingChallengeList.vue"
import DeadlineChallengeList from "./DeadlineChallengeList.vue"

export default {
  components: {
    HomeRanking,
    UpcomingChallengeList,
    HotReviewList,
    DeadlineChallengeList
  }
}
</script>

<style>

</style>