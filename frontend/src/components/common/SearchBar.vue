<template>
  <!-- <v-form> -->
    <div class="my-search-bar">
      <v-container>
        <v-text-field
              solo-inverted
              label="검색"
              append-icon="fas fa-search"
              v-on:keyup.enter="search"
              v-model="searchItem"
          ></v-text-field>
      </v-container>
      <PopUp v-if="showPopup" @close="showPopup = false" popupTitle="경고" popupContent="1글자 이상 입력하세요."></PopUp>
  </div>
  <!-- </v-form> -->
</template>

<script>
import PopUp from './PopUp.vue'

export default {
  data: function() {
    return {
      searchItem: '',
      showPopup: false
    }
  },
  methods: {
    search: function() {
      if(this.searchItem !== ''){   // 무언가 입력되었을때
        this.$store.commit('SEARCH',this.searchItem);
        this.clearInput();
      } else {    // 아무것도 입력 안되었을때
        this.showPopup = !this.showPopup;   // 팝업창출력
        console.log('아무것도 입력되지 않았습니다.');
      }
    },
    clearInput: function() {
      this.searchItem = '';
    }
  },
  components: {
    PopUp,
  }
}
</script>

<style scoped>
.my-search-bar {
  width: 100%;
}

.searchBtn {
  color: gray;
  vertical-align: middle;
  /* float: right; */
}
</style>