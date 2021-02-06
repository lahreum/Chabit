<template>
  <div>
    <div class="search">
      <search-bar />
      <i class="fas fa-filter"></i>
    </div>
    <hot-challenge-list />
    <new-challenge-list />
  </div>
</template>

<script>
import SearchBar from '../../components/common/SearchBar.vue'
import HotChallengeList from './HotChallengeList.vue'
import NewChallengeList from './NewChallengeList.vue'
export default {
  components: { SearchBar, HotChallengeList, NewChallengeList },

}
</script>

<style scoped>
.search {
  margin: 0 1rem 0 1rem;
  display: flex;
  justify-content: center;
}

.search > .fa-filter {
  font-size: 2rem;
  margin-top: 1.2rem;
  color: #B71C1C;
}
</style>