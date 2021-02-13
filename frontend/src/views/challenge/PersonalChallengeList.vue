<template>
  <v-container>
    <!-- <v-card-actions
      class="challenge-card"
      v-for="(item,i) in items"
      :key="i"
    >
      <v-card
        class="mx-auto my-12 rounded-xl"
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

        <v-card-title style="color: black; font-weight: 600;">{{item.challengeName}}</v-card-title>

        <v-card-text>
          <v-row
            align="center"
            class="mx-0"
            v-if="item.challengeOwner.userRole === 'ADMIN'"
          >
            <div style="color: #B71C1C; font-size: 1.1rem; font-weight: 600;" >
              <i class="fas fa-gem"></i>
            </div>
            <div class="grey--text ml-1" style="font-size: 1.1rem; font-weight: 600;">
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
    </v-card-actions> -->
    <v-container>
      <v-row
      >
        <v-col
          cols="6"
          sm
          v-for="(item, idx) in items"
          :key="idx"
        >
          <v-card
            max-width="12rem"
            @click="moveToChallengeDetail(item)"
          >
            <v-img
              :src="item.challengeThumbnail"
              height="8rem"
            >

            </v-img>
          </v-card>
          <p></p>
          <v-row
            align="center"
            class="mx-0"
            v-if="item.challengeOwner.userRole === 'ADMIN'"
          >
            <div style="color: #B71C1C; font-size: 0.8rem; font-weight: 600;" >
              <i class="fas fa-gem"></i>
            </div>
            <div class="grey--text ml-1" style="font-size: 0.8rem; font-weight: 600;">
              공식 챌린지
            </div>
          </v-row>
          <div class="new-challenge-info">
            <span>{{item.challengeName}}</span>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </v-container>
</template>

<script>
// import HashTag from '../../components/common/HashTag.vue'
export default {
  // components: { HashTag },
  props: ['type'],
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
    const currentType = this.type
    this.$Axios.get(`${this.$store.state.host}/v1/users/${this.$store.state.user.userEmail}/challenges`)
      .then(res => {
        console.log(res.data.data)
        if (currentType === "참가중인") {
          this.items = res.data.data.ongoingChallenge
        } else if (currentType === "완료한") {
          this.items = res.data.data.terminatedChallenge
        } else {
          this.items = res.data.data.ownChallenge
        }
      })
      .catch(err => {
        console.log(err)
      })
  }
}
</script>

<style scoped>
.challenge-card {
  padding: 0;
  margin-bottom: -3rem;
}

.hash-tag-bundle {
  display: flex;
  align-items: flex-start;
}

.new-challenge-info {
  margin: 0.5rem 0 -1rem 0;
  display: flex;
  justify-content: space-between;
  font-size: 1rem;
  font-weight: 600;
  max-width: 12rem;
}
</style>