<template>
  <div>
    <p></p>
    <v-card
      class="mx-auto"
      width="90%"
      color="grey lighten-5"
    >
      <v-card-text>
        <div style="font-size: 1.3rem; font-weight: 600;">{{item.challengeName}}</div>
      </v-card-text>
    </v-card>
    <p></p>
    <v-card
      class="mx-auto"
      width="90%"
      color="grey lighten-5"
    >
      <v-card-text>
        <p style="font-size: 1.1rem; font-weight: 600;">리뷰 쓰기</p>
        <v-textarea
          flat
          solo
          name="input-7-4"
          label="리뷰를 작성하세요(최대 1000자)"
          :rules="textRules"
          v-model="texts"
        ></v-textarea>
      </v-card-text>
    </v-card>
    <p></p>
    <v-container style="width: 90%;">
      <v-row>
        <v-col
          cols="8"
        >
          <v-file-input
            outlined
            dense
            multiple
            :rules="fileRules"
            v-model="files"
            accept="image/png, image/jpeg, image/bmp"
          ></v-file-input>
        </v-col>
        <v-col
          cols="4"
        >
          <v-btn
            rounded-lg
            color="red darken-4"
            class="white--text"
            :disabled="this.files === ''"
            height="40px"
            @click="upload"
          >
            업로드
          </v-btn>
        </v-col>
      </v-row>
      <!-- <v-row>
        <v-col
          cols="4"
          v-for="(reviewImage, idx) in reviewImages"
          :key="idx"
        >
          <v-img
            src=""
            class="rounded-lg"
          ></v-img>
        </v-col>
      </v-row> -->
      <p></p>
      <div>
        <v-btn
          rounded
          color="red darken-4"
          class="white--text"
          :disabled="this.reviewImages.length === 0 || this.texts === ''"
          width="100%"
          height="60px"
          @click="onSubmit"
        >
          완료
        </v-btn>
      </div>
    </v-container>
  </div>
</template>

<script>
export default {
  data: () => ({
    item: {},
    textRules: [v => v.length <= 1000 || '1000자 이상 입력할 수 없습니다'],
    texts: '',
    fileRules: [v => v.length <= 4 || '최대 4개의 파일을 올릴 수 있습니다'],
    files: '',
    reviewImages: [],
  }),
  methods: {
    upload () {
      console.log(this.files)
      alert("이미지가 업로드 되었습니다.")
      this.reviewImages = this.files
      // console.log(this.reviewImages)
    },
    onSubmit () {
      if(this.texts === "") {
        alert("리뷰 내용을 채워주세요.")
      } else {
        let fd = new FormData();
        fd.append('challengeId', this.$store.state.writeReviewChallengeId)
        fd.append('reviewContent', this.texts)
        for (let i=0; i < this.reviewImages.length; i++) {
          fd.append('reviewImages', this.reviewImages[i], "[PROXY]")
        }
        fd.append('userEmail', this.$store.state.user.userEmail)
        console.log(fd)
        this.$Axios.post(`${this.$store.state.host}/v1/review`, 
          fd, {
              headers: {
                'Content-Type': 'multipart/form-data'
            }
          }
        )
        .then(res => {
          console.log(res.data)
          alert("리뷰가 성공적으로 저장되었습니다.")
          this.$store.commit("MOVETOREVIEWDETAIL", res.data.data.reviewId)
          this.$router.push('/review-detail')
        })
        .catch(err => {
          console.log(err)
        })
      }
    }
  },
  created() {
    const challengeId = this.$store.state.writeReviewChallengeId
    // console.log(challengeId)
    // console.log(this.$store.state.user)
    this.$Axios.get(`${this.$store.state.host}/v1/challenges/${challengeId}?userEmail=${this.$store.state.user.userEmail}`)
      .then(res => {
        console.log(res.data)
        this.item = res.data.data
      })
      .catch(err => {
        console.log(err)
      })
  }
}
</script>

<style scoped>

</style>