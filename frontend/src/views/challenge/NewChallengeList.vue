<template>
  <v-container>
    <sequential-entrance fromBottom> 
    <div class="new-challenge font-title">
      <span class="hot">새로운</span><span>챌린지</span>
    </div>
    <v-container fluid style="width:95%;">
      <v-row>
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
            <div style="color: #B71C1C;" class="fix-font-small">
              <i class="fas fa-gem"></i>
            </div>
            <div class="grey--text ml-1 fix-font-small">
              공식 챌린지
            </div>
          </v-row>
          <v-else></v-else>
          <div class="new-challenge-info fix-font-mid">
            <span>{{item.challengeName}}</span>
          </div>
        </v-col>
      </v-row>
    </v-container>
    </sequential-entrance> 
    <div class="create-challenge">
      <v-btn
        color="red darken-4"
        medium
        fab
        @click="moveToCreateChallenge"
      ><span class="plus">+</span></v-btn>
    </div>
  </v-container>

</template>

<script>
import { mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters({ userEmail: "getUserEmail" }),
  },
  data() {
    return {
      items: [],
    }
  },
  created() {
    this.$Axios.get(`${this.$store.state.host}/v1/challenges`)
      .then(res => {
        // console.log(res.data.data)
        const challenges = res.data.data
        challenges.sort(function(a, b) {
          return b.challengeID - a.challengeID
        })
        // console.log(challenges)
        this.items = challenges
      })
      .catch(err => {
        console.log(err)
      })
  },
  methods: {
    moveToChallengeDetail(item) {
      if(this.userEmail) {
        this.$store.commit("SELECTEDCHALLENGE", item.challengeID);
        this.$router.push("/challenge-detail");
      } else {
        alert("로그인 후 확인 가능합니다.");
        this.$router.push({ name: 'Login' });
      }
    },
    moveToCreateChallenge() {
      if(this.userEmail) {
        this.$router.push('/create-challenge');
      } else {
        alert("로그인 후 확인 가능합니다.");
        this.$router.push({ name: 'Login' });
      }
    }
  }
}
</script>

<style scoped>
.new-challenge {
  padding-left: 0.5rem;
  margin-bottom: 0.5rem;
  font-size: 1.3rem;
  font-weight: 600;
}

.new-challenge > .hot {
  color: #B71C1C;
  padding-right: 0.5rem;
}

.new-challenge-info {
  margin: 0.7rem 0 1rem 0;
  display: flex;
  justify-content: space-between;
  font-weight: 600;
  max-width: 12rem;
}

.create-challenge {
  position: fixed;
  right: 3vh;
  bottom: 3vh;
}

.plus {
  margin-top: -0.1rem;
  font-size: 3.5rem;
  color: white;
}
</style>