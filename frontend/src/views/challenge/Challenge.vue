<template>

  <div>
    <sequential-entrance fromBottom> 
    <div class="search">
      <div class="my-search-bar">
          <v-text-field
                label="검색"
                append-icon="fas fa-search"
                clearable
                dense
                outlined
                v-on:keyup.enter="search"
                v-model="searchItem"
            ></v-text-field>
      </div>
    </div>
    </sequential-entrance> 
    <div v-if="!searchItem">
      <hot-challenge-list />
      <new-challenge-list />
    </div>
    <div v-else>
      <span class="search-result">'{{searchItem}}' 검색 결과</span>
      <search-challenge-list v-if="isSearch" :searchWord="searchItem"/>
    </div>

  </div>

</template>

<script>
import HotChallengeList from './HotChallengeList.vue'
import NewChallengeList from './NewChallengeList.vue'
import SearchChallengeList from './SearchChallengeList.vue'
export default {
  components: { HotChallengeList, NewChallengeList, SearchChallengeList },
  data() {
    return {
      isSearch: false,
      searchItem: '',
      showPopup: false
    }
  },
  methods: {
    search: function() {
      if(this.searchItem !== ''){   // 무언가 입력되었을때
        // console.log(this.searchItem);
        this.isSearch = true
      }
    },
  },
  watch: {
    searchItem: function () {
      if (this.searchItem === '') {
        this.isSearch = false
      }
    }
  }
}
</script>

<style scoped>
.my-search-bar {
  width: 85%;
}

.searchBtn {
  color: gray;
  vertical-align: middle;
  /* float: right; */
}

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

.search-result {
  font-size: 1.4rem;
  font-weight: 600;
  margin-left: 1.3rem;
}
</style>