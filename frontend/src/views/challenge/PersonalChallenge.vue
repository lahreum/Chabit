<template>
  <div>
    <div class="top-personal-challenge-type">
      <i class="fas fa-chevron-left"></i>
      <span>Opened</span>
    </div>
    <div class="challenge-card">
      <div class="challenge-card-list">
        <PersonalChallengeList />
      </div>
    </div>
  </div>
</template>

<script>
import PersonalChallengeList from "./PersonalChallengeList.vue"

export default {
  components: {
    PersonalChallengeList
  }
}
</script>

<style>

</style>