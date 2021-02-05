<template>
  <div class="container">
    <!-- <div class="search-bar">
      <input @keyup.enter="onSearch" type="search" placeholder=" Search">
      <i class="fas fa-filter"></i>
    </div> -->
    <!-- <SearchBar /> -->
    <div v-if="!isTrue">
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
    </div>
    <div v-else>
      <div class="search-result">
        <div class="search-result-title">
          <span>'{{title}}' 검색 결과</span>
        </div>
        <div class="search-result-list">
          <!-- <SearchChallengeList /> -->
        </div>
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
// import SearchBar from '../../components/common/SearchBar.vue'
// import SearchChallengeList from './SearchChallengeList.vue'

export default {
  components: { 
    HotChallengeList, 
    NewChallengeList, 
    BtnCreateChallenge,
    // SearchBar,
    // SearchChallengeList 
  },
  data() {
    return {
      reviews: [1,2,3,4,5,6,7,8],
      isTrue: false,
      title: ''
    }
  },
  methods: {
    onSearch(event) {
      this.isTrue = true;
      this.title = event.target.value;
    }
  }
}
</script>

<style>

</style>