<template>
  <v-container>
    <div class="deadline-challenge font-title" style="margin-top:20px;">
      <span class="hot">시작 임박</span><span>챌린지</span>
    </div>
    <v-carousel 
      hide-delimiters 
      cycle 
      :show-arrows-on-hover="true"
      height="450px"
    >
      <v-carousel-item
        v-for="(item,i) in items"
        :key="i"
        reverse-transition="fade-transition"
        transition="fade-transition"
      >
        <v-card
          class="mx-auto my-12 rounded-xl"
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
      if(this.userEmail) {
        this.$store.commit("SELECTEDCHALLENGE", item.challengeID);
        this.$router.push("/challenge-detail");
      } else {
        alert("로그인 후 확인 가능합니다.");
        this.$router.push({ name: 'Login' });
      }
    }
  },
  created() {
    this.$Axios.get(`${this.$store.state.host}/v1/challenges`)
      .then(res => {
        // this.items = res.data.data
        const challenges = res.data.data
        const currentDay = new Date()
        let dates = ""
        let year = ""
        let month = ""
        let day = ""
        let date = ""
        for (let i=0; i < challenges.length; i++) {
          dates = String(challenges[i].challengeStartDate.substr(0,10));
          year = parseInt(dates.slice(0,4));
          month = parseInt(dates.slice(5,7));
          day = parseInt(dates.slice(8,10));
          date = new Date(year, month-1, day);
          const elapsedMSec = date.getTime() - currentDay.getTime();
          const elapsedDay = elapsedMSec / (1000 * 60 * 60 * 24);
          if (elapsedDay <= 3 && elapsedDay >= 1) {
            this.items.push(challenges[i])
          }
        }
      }).catch(err => {
        console.log(err)
      })
  },
}
</script>

<style scoped>
.deadline-challenge {
  padding-left: 0.5rem;
  /* margin-top: 2.5rem; */
  margin-bottom: -1.5rem;
  font-size: 1.3rem;
  font-weight: 600;
}

.deadline-challenge > .hot {
  color: #B71C1C;
  padding-right: 0.5rem;
}

.hash-tag-bundle {
  display: flex;
  align-items: flex-start;
}
</style>