<template>
  <div class="container">
    <div class="top-toggle-alarm">
      <i class="fas fa-grip-lines"></i>
    </div>
    <div class="search-bar">
      <input type="search" placeholder="Search">
      <i class="fas fa-filter"></i>
    </div>
    <div class="challenge-hot">
      <HotChallengeList />
    </div>
    <div class="new-challenge">
      <div class="new-challenge-title">
        <span class="hot-popular">새로운</span><span>챌린지</span>
      </div>
      <div class="new-challenge-card-list">
        <NewChallengeList 
          v-for="(review, idx) in reviews"
          :key=idx
        />
      </div>
    </div>
    <div class="create-challenge-btn">
      <BtnCreateChallenge />
    </div>
  </div>
</template>

<script>
import './challenge.css'
import BtnCreateChallenge from '../../components/common/BtnCreateChallenge.vue'
import HotChallengeList from './HotChallengeList.vue'
import NewChallengeList from './NewChallengeList.vue'

export default {
  components: { HotChallengeList, NewChallengeList, BtnCreateChallenge },
  data() {
    return {
      reviews: [1,2,3,4,5,6,7,8]
    }
  }
}
</script>

<style>

</style>