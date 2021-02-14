<template>
  <div>
    <div class="challenge-type">
      <span style="color: #B71C1C">{{type}}</span>
      <span> 챌린지</span>
    </div>
    <p style="margin: 1.5rem 0;"></p>
    <personal-challenge-list :type="type"/>
  </div>
</template>

<script>
import PersonalChallengeList from './PersonalChallengeList.vue'

export default {
  components: {
    PersonalChallengeList
  },
  data() {
    return {
      type: ""
    }
  },
  created() {
    this.type = this.$store.state.typeOfChallenge
  }
}
</script>

<style scoped>
.challenge-type {
  /* position: fixed; */
  z-index: 1;
  top: 3rem;
  width: 100%;
  background-color: white;
  text-align: center; 
  font-size: 1.7rem; 
  font-weight: bold;
}
</style>