<template>
  <div class="finish-proof">
    <h1>인증 완료</h1>
    <h3>인증사진이</h3>
    <h3>업로드 되었습니다.</h3>
    <p></p>
    <v-card flat>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          rounded
          color="red darken-4"
          dark
          width="30%"
          height="60px"
          class="proof-btn"
          @click="toHome"
        >
          <span>홈으로</span>
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn
          rounded
          color="white"
          width="30%"
          height="60px"
          class="proof-btn"
          @click="toProofList"
        >
          <span>인증목록</span>
        </v-btn>
        <v-spacer></v-spacer>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
export default {
  methods: {
    toHome() {
      this.$router.push("/home");
    },
    toProofList() {
      this.$router.push("/proof-list");
    },
  },
};
</script>

<style>
.finish-proof {
  text-align: center;
  margin-top: 55%;
}

.finish-proof > h1 {
  font-size: 45px;
  margin-bottom: 10px;
}

.finish-proof > h3 {
  font-size: 25px;
  color: gray;
}

.finish-proof > p {
  margin-bottom: 50px;
}

.proof-btn > span {
  font-size: 1.1rem;
  font-weight: 600;
}
</style>