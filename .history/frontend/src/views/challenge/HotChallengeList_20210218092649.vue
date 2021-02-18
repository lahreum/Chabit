<template>
<sequential-entrance fromBottom> 
  <v-container>
      <div class="hot-challenge font-title">
        <span class="hot">HOT</span><span>챌린지</span>
    </div>
    <v-carousel
      class="slide"
      hide-delimiters 
      cycle 
      :show-arrows-on-hover="true"
      height="450"
    >
      <v-carousel-item
        v-for="(item,i) in items"
        :key="i"
      >
        <v-card
          class="mx-auto my-5 rounded-xl"
          max-width="380"
          :loading="loading"
          @click="reverse, moveToChallengeDetail(item)"
          style="background-color: white;"
        >
          <template slot="progress">
            <v-progress-linear
              color="red darken-4"
              height="10"
              indeterminate
            ></v-progress-linear>
          </template>

          <v-img
            height="250"
            :src="item.challengeThumbnail"
          ></v-img>

          <v-card-title style="color: black;" class="font-size-title">{{item.challengeName}}</v-card-title>

          <v-card-text>
            <v-row
              align="center"
              class="mx-0"
              v-if="item.challengeOwner.userRole === 'ADMIN'"
            >
              <div style="color: #B71C1C;" class="font-size-subtitle">
                <i class="fas fa-gem"></i>
              </div>
              <div class="grey--text ml-1 font-size-subtitle">
                공식 챌린지
              </div>
            </v-row>
            <p></p>
            <div class="hash-tag-bundle">
              <hash-tag :content="item.hashtags.hashtags[0].hashtagName"/>
              <hash-tag :content="item.hashtags.hashtags[1].hashtagName"/>
            </div>
          </v-card-text>
        </v-card>
      </v-carousel-item>
    </v-carousel>
  </v-container>
  </sequential-entrance> 
</template>

<script>
import HashTag from '../../components/common/HashTag.vue'
export default {
  components: { HashTag },
  data () {
    return {
      items: [],
      loading: false,
    }
  },
  created() {
    this.$Axios.get(`${this.$store.state.host}/v1/challenges/hot`)
      .then(res => {
        // console.log(res.data)
        this.items = res.data.data
      }).catch(err => {
        console.log(err)
      })
  },
  methods: {
    reverse () {
      this.loading = true

      setTimeout(() => (this.loading = false), 2000)
    },
    moveToChallengeDetail(item) {
      this.$store.commit("SELECTEDCHALLENGE", item.challengeID);
      this.$router.push("/challenge-detail");
    }
  }
}
</script>

<style scoped>
.hash-tag-bundle {
  display: flex;
  align-items: flex-start;
}
.hot-challenge > .hot {
  color: #B71C1C;
  padding-right: 0.5rem;
}
</style>