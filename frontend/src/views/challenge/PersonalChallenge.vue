<template>
  <div>
    <div class="challenge-type">
      <span style="color: #B71C1C">참가중인</span>
      <span> 챌린지</span>
    </div>
    <personal-challenge-list />
  </div>
</template>

<script>
import PersonalChallengeList from './PersonalChallengeList.vue'

export default {
  components: {
    PersonalChallengeList
  }
}
</script>

<style scoped>
.challenge-type {
  position: fixed;
  z-index: 1;
  top: 3rem;
  width: 100%;
  background-color: white;
  text-align: center; 
  font-size: 1.7rem; 
  font-weight: bold;
}
</style>