<template>
  <div>
    <div class="alarm-bell">
      <i class="far fa-bell"></i>
    </div>
    <div class="alarm-circle">
      <i class="fas fa-circle"></i>
    </div>
    <home-ranking />
    <upcoming-challenge-list />
    <hot-review-list />
    <deadline-challenge-list />
  </div>
</template>

<script>
import DeadlineChallengeList from './DeadlineChallengeList.vue'
import HomeRanking from './HomeRanking.vue'
import HotReviewList from './HotReviewList.vue'
import UpcomingChallengeList from './UpcomingChallengeList.vue'
export default {
  components: { HomeRanking, HotReviewList, UpcomingChallengeList, DeadlineChallengeList },
}
</script>

<style scoped>
.alarm-bell {
  position: fixed;
  top: 0.5rem;
  right: 1.5rem;
  font-size: 2rem;
  color: #B71C1C;
  z-index: 2;
}

.alarm-circle {
  position: fixed;
  top: 1rem;
  right: 1.5rem;
  font-size: 0.85rem;
  color: #B71C1C;
  z-index: 2;
}
</style>