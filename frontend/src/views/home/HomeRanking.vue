<template>
  <v-container>
    <div class="all-ranking font-title" style="margin-top:20px;">
      <span>전체 랭킹</span>
      <i class="fas fa-plus" @click="$router.push('/ranking')"></i>
    </div>
    <v-card
      class="mx-auto rounded-xl"
      max-width="370"
      flat
    >
      <v-list color="grey lighten-4">
        <v-list-item

          v-for="(item,idx) in ranking"
          :key="idx"
        >
          <v-list-item-subtitle class="text-left font-size-subtitle" >
            {{ idx + 1 }}위
          </v-list-item-subtitle>

          <v-list-item-subtitle class="text-middle font-size-subtitle">
            {{ item.userNickname }}
          </v-list-item-subtitle>

          <v-list-item-subtitle class="text-right font-size-subtitle" style="color: #B71C1C;">
            {{ item.userPoints }}
          </v-list-item-subtitle>
        </v-list-item>
      </v-list>

    </v-card>
  </v-container>
</template>

<script>
export default {
  data () {
    return {
      ranking: [],
    }
  },
  created() {
    this.$Axios.get(`${this.$store.state.host}/v1/users/ranking/jihae@ssafy.com?categoryId=0&monthlyRanking=false&onlyFollowing=false`)
      .then(res => {
        console.log(res.data.data)
        const allRankings = res.data.data.userRanking
        allRankings.sort(function(a, b) {
          return b.userPoints - a.userPoints
        })
        // console.log(allRankings)
        this.ranking = allRankings.slice(0,3)
        console.log(this.ranking)
      })
      .catch(err => {
        console.log(err)
      })
  }
}
</script>

<style scoped>
.all-ranking {
  padding-left: 0.5rem;
  padding-bottom: 1rem;
  font-size: 1.3rem;
  font-weight: 600;
}

.all-ranking > .fa-plus {
  padding-left: 0.5rem;
  color: #B71C1C;
}
.v-list-item{
  min-height: 33px;
  padding : 0px 20px; 
}
.v-list-item > .text-left{
  max-width: 15vw;
}
.v-list-item > .text-right{
  max-width: 25vw;
}

</style>