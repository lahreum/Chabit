<template>
  <div>
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
  </div>
</template>

<script>
import "./home.css"
import HomeRanking from "./HomeRanking.vue"
import UpcomingChallengeList from "./UpcomingChallengeList.vue"

export default {
  components: {
    HomeRanking,
    UpcomingChallengeList
  }
}
</script>

<style>

</style>