<template>
  <div>
  <sequential-entrance fromBottom>
    <home-ranking />
    <upcoming-challenge-list />
    <hot-review-list />
    <deadline-challenge-list />
    </sequential-entrance>
  </div>
</template>

<script>
import DeadlineChallengeList from "./DeadlineChallengeList.vue";
import HomeRanking from "./HomeRanking.vue";
import HotReviewList from "./HotReviewList.vue";
import UpcomingChallengeList from "./UpcomingChallengeList.vue";
export default {
  components: { HomeRanking, HotReviewList, UpcomingChallengeList, DeadlineChallengeList },
};
</script>
