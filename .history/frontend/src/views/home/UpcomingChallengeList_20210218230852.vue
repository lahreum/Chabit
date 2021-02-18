<template>
  <v-container>
    <div class="hot-challenge font-title" style="margin-top:25px;">
      <span class="hot">HOT</span><span style="font-size: 1.3rem;">챌린지</span>
      <i class="fas fa-plus" @click="$router.push('/challenge')" ></i>
    </div>
    <v-carousel
      class="slide"
      hide-delimiters
      height="450px" 
      cycle 
      :show-arrows-on-hover="true"
    >
      <v-carousel-item
        v-for="(item,i) in items"
        :key="i"
      >
        <v-card
          class="mx-auto my-5 rounded-xl"
          max-width="370"
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
            height="230"
            :src="item.challengeThumbnail"
          ></v-img>

          <v-card-title style="color: black; font-size: 1.2rem;" class="font-size-title">{{item.challengeName}}</v-card-title>

          <v-card-text>
            <v-row
              align="center"
              class="mx-0"
              v-if="item.challengeOwner.userRole === 'ADMIN'"
            >
              <div style="color: #B71C1C;" class="font-size-subtitle">
                <i class="fas fa-gem" style="font-size: 1.0rem;"></i>
              </div>
              <div class="grey--text ml-1 font-size-subtitle" style="font-size: 1.0rem;">
                공식 챌린지
              </div>
            </v-row>
            <p></p>
            <div class="hash-tag-bundle" >
              <hash-tag :content="item.hashtags.hashtags[0].hashtagName"/>
              <hash-tag :content="item.hashtags.hashtags[1].hashtagName"/>
            </div>
          </v-card-text>
        </v-card>
      </v-carousel-item>
    </v-carousel>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
import HashTag from '../../components/common/HashTag.vue'

export default {
  computed: {
    ...mapGetters({ userEmail: "getUserEmail" }),
  },
  components: { HashTag },
  data () {
    return {
      items: [],
      loading: false,
    }
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
}
</script>

<style scoped>
.hot-challenge > .fa-plus {
  padding-left: 0.5rem;
  color: #B71C1C;
  
}

.hot-challenge > .hot {
  color: #B71C1C;
  padding-right: 0.5rem;
  font-size: 1.3rem;
  padding-left: 0.8rem;
}

.hash-tag-bundle {
  display: flex;
  align-items: flex-start;
  font-size: 1.0rem;
}
</style>