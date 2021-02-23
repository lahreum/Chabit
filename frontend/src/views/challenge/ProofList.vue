<template>
  <div>
    <div class="proof-list">
      <span>인증 목록</span>
      <hr>
      <v-btn style="margin:10px;" color="#E57373" @click="goChallenge"><span style="color:white;">챌린지 상세보기</span></v-btn>
      <v-btn style="margin:10px;" color="#424242" @click="goHome"><span style="color:white;"><i class="fas fa-home"></i> 홈으로</span></v-btn>
    </div>

    <v-container
      my-15
    >
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
          >
            <v-img
              :src="item.proofImage"
              height="10rem"
              @click="chooseProof(item)"
            >

            </v-img>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
    <v-overlay
      :z-index="zIndex"
      :value="overlay"
      :chooseItem="this.chooseItem"
    >
      <div style="width: 80%; margin: 0 auto; font-weight: 600;">
        <span>{{chooseItem.userNickname}}</span>
        <p>{{chooseItem.proofDate}}</p>
      </div>
      <div class="overlay-container">
        <img 
          :src="chooseItem.proofImage" 
          alt=""
          style="margin: 0 auto; border-radius: 1rem; width: 80%;"
        >
        <v-row v-if="manager === this.$store.state.user.userEmail">
          <v-col
            cols="6"
          >
            <v-btn
              class="white--text my-4"
              color="red darken-4"
              width="10"
              @click="reject(chooseItem)"
            >
              반려
            </v-btn>
          </v-col>
          <v-col
            cols="6"
          >
            <v-btn
              class="white--text my-4"
              color="grey darken-3"
              width="10"
              @click="overlay = false"
            >
              이전
            </v-btn>
          </v-col>
        </v-row>
        <v-row v-else>
          <v-col
            cols="12"
          >
            <v-btn
              class="white--text my-4"
              color="grey darken-3"
              width="10"
              @click="overlay = false"
            >
              이전
            </v-btn>
          </v-col>
        </v-row>
      </div>
    </v-overlay>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [],
      overlay: false,
      zIndex: 1,
      chooseItem: {},
      manager: ""
    }
  },
  methods: {
    chooseProof(item) {
      this.overlay = !this.overlay
      // console.log(item)
      this.chooseItem = item
    },
    reject(item) {
      if(confirm("해당 인증사진을 반려하시겠습니까?")) {
        this.$Axios.delete(`${this.$store.state.host}/v1/challenges/${item.challengeId}/proof/${item.proofId}?ownerEmail=${this.manager}`)
          .then(res => {
            console.log(res.data.data)
            alert("반려하였습니다.")
            history.go(0)
          })
      }
    },
    goChallenge() {
      this.$router.push('/challenge-detail');
    },
    goHome() {
      this.$router.push('/home');
    }
  },
  created() {
    this.$Axios.get(`${this.$store.state.host}/v1/challenges/${this.$store.state.prooflistChallengeId}/proof`)
      .then(res => {
        console.log(res.data.data)
        this.items = res.data.data
      })
      .catch(err => {
        console.log(err)
      })
    
    this.$Axios.get(`${this.$store.state.host}/v1/challenges/${this.$store.state.prooflistChallengeId}`)
      .then(res => {
        // console.log(res.data.data)
        this.manager = res.data.data.challengeOwner.userEmail
        // console.log(this.manager)
      })
      .catch(err => {
        console.log(err)
      })
  }
}
</script>

<style scoped>
.proof-list {
  /* position: fixed; */
  z-index: 1;
  top: 3rem;
  width: 100%;
  background-color: white;
  text-align: center; 
  font-size: 1.7rem; 
  font-weight: bold;
}

.proof-list > hr {
  width: 90%;
  margin: 1rem auto;
  color: gray;
}

.overlay-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>