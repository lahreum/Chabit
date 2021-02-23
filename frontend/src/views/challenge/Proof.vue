<template>
  <div>
    <p></p>
    <v-card class="mx-auto" width="90%" color="grey lighten-5">
      <v-card-text>
        <div style="font-size: 1.3rem; font-weight: 600">
          {{ item.challengeName }}
        </div>
      </v-card-text>
    </v-card>
    <p></p>
    <v-card class="mx-auto" width="90%" color="grey lighten-5">
      <v-card-text>
        <p style="font-size: 1.1rem; font-weight: 600">인증 방법</p>
        <p style="font-size: 1rem">{{ item.authWay }}</p>
        <hr />
        <p style="font-size: 1.1rem; font-weight: 600; margin-top: 1rem">
          예시
        </p>
        <v-row>
          <v-col cols="12">
            <v-img :src="item.authExample" class="rounded-lg"></v-img>
          </v-col>
        </v-row>
        <p></p>
        <hr />
        <p style="font-size: 1.1rem; font-weight: 600; margin-top: 1rem">
          오늘의 손동작
        </p>
        <v-row>
          <v-col cols="6">
            <v-img :src="gesture" class="rounded-lg"></v-img>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
    <p></p>
    <v-container style="width: 90%">
      <v-row>
        <v-col cols="8">
          <v-file-input
            label="인증 사진"
            outlined
            dense
            accept="image/png, image/jpeg, image/bmp"
            v-model="proofFiles"
          ></v-file-input>
        </v-col>
        <v-col cols="4">
          <v-btn
            rounded-lg
            color="red darken-4"
            dark
            height="40px"
            @click="proofUpload"
          >
            업로드
          </v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-img src="" class="rounded-lg" max-width="50%"></v-img>
        </v-col>
      </v-row>
      <p></p>
      <div>
        <v-btn
          rounded
          color="red darken-4"
          class="white--text"
          width="100%"
          height="60px"
          @click="proof"
          :disabled="this.proofImage === ''"
        >
          인증하기
        </v-btn>
      </div>
    </v-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      item: [],
      proofFiles: [],
      proofImage: "",
      gesture: "",
    };
  },
  created() {
    const challengeId = this.$store.state.proofChallengeId;
    // console.log(challengeId)
    // console.log(this.$store.state.user)
    this.$Axios
      .get(
        `${this.$store.state.host}/v1/challenges/${challengeId}?userEmail=${this.$store.state.user.userEmail}`
      )
      .then((res) => {
        console.log(res.data);
        this.item = res.data.data;
      })
      .catch((err) => {
        console.log(err);
      });

    this.$Axios
      .get(`${this.$store.state.host}/v1/challenges/todayProof`)
      .then((res) => {
        // console.log(res.data.data)
        this.gesture = res.data.data.proofExampleImg;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    proofUpload() {
      if (this.proofFiles.size != null) {
        console.log(this.proofFiles);
        let fd = new FormData();
        fd.append("proofImage", this.proofFiles);
        console.log(fd);
        alert("이미지가 업로드 되었습니다.");
        this.proofImage = fd;
      } else {
        alert("이미지 파일을 업로드해주세요!");
      }
    },
    proof() {
      const challengeId = this.$store.state.proofChallengeId;
      const proofImage = this.proofImage;
      const userEmail = this.$store.state.user.userEmail;

      this.$Axios
        .post(
          `${this.$store.state.host}/v1/challenges/${challengeId}/proof/${userEmail}`,
          proofImage,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
        )
        .then((res) => {
          console.log(res.data.data);
          alert("성공적으로 인증을 완료하였습니다.");
          this.$store.commit("MOVETOPROOFLIST", challengeId);
          this.$router.push("/finish-proof");
        })
        .catch((err) => {
          alert("인증 사진을 올려주세요");
          console.log(err);
        });
    },
  },
};
</script>

<style scoped>
</style>