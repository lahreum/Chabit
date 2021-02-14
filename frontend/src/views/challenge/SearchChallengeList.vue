<template>
  <v-container>
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
    <div class="create-challenge">
      <v-btn
        elevation="2"
        color="red darken-4"
        x-large
        fab
        @click="$router.push('/create-challenge')"
      ><span class="plus">+</span></v-btn>
    </div>
  </v-container>
</template>

<script>
export default {
  props: ['searchWord'],
  data() {
    return {
      items: [],
    }
  },
  created() {
    this.$Axios.get(`${this.$store.state.host}/v1/challenges`)
      .then(res => {
        // console.log(res.data.data)
        // console.log(this.searchWord)
        const challenges = res.data.data
        for (let i = 0; i < challenges.length; i++) {
          // console.log(challenges[i])
          if(challenges[i].challengeName.includes(this.searchWord)) {
            this.items.push(challenges[i])
          }
        // console.log(this.items)
        }
      })
  },
  methods: {
    moveToChallengeDetail(item) {
      this.$store.commit("SELECTEDCHALLENGE", item.challengeID);
      this.$router.push("/challenge-detail");
    }
  }
}
</script>

<style scoped>
.new-challenge-info {
  margin: 0.5rem 0 -1rem 0;
  display: flex;
  justify-content: space-between;
  font-size: 1rem;
  font-weight: 600;
  max-width: 12rem;
}

.create-challenge {
  position: fixed;
  right: 2rem;
  bottom: 2rem;
}

.plus {
  margin-top: -0.1rem;
  font-size: 4rem;
  color: white;
}
</style>