<template>
  <v-container>
    <div class="hot-review">
      <span class="hot">인기</span><span>리뷰</span>
    </div>
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
            @click="moveToReviewDetail(item)"
          >
            <v-img
              :src="item.reviewImages[0]"
              height="8rem"
            >

            </v-img>
          </v-card>
          <div class="hot-review-info">
            <span>{{item.userNickname}}</span>
            <span class="like">{{item.coolCount}}<i class="far fa-thumbs-up"></i></span>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      items: []
    }
  },
  created() {
    this.$Axios.get(`${this.$store.state.host}/v1/review/hot`)
      .then(res => {
        console.log(res.data.data)
        this.items = res.data.data
      })
      .catch(err => {
        console.log(err)
      })
  },
  methods: {
    moveToReviewDetail(item) {
      this.$store.commit("MOVETOREVIEWDETAIL", item.reviewId)
      if(this.$store.state.user.userEmail == item.userEmail) {
        this.$router.push('/review-detail')
      } else {
        let reviewer = {
          'yourEmail': item.userEmail,
          'yourImage': item.userImage,
          'yourNickname': item.userNickname
        }
        this.$store.commit("SETYOURINFO", reviewer);
        this.$router.push('/your-review-detail')
      }
    }
  }
}
</script>

<style scoped>
.hot-review {
  padding-left: 0.5rem;
  margin-top: -2.5rem;
  margin-bottom: 1rem;
  font-size: 1.5rem;
  font-weight: 600;
}

.hot-review > .hot {
  color: #B71C1C;
  padding-right: 0.5rem;
}

.hot-review-info {
  margin: 0.5rem 0.5rem -1rem 0.5rem;
  display: flex;
  justify-content: space-between;
  font-size: 1rem;
  font-weight: 600;
  max-width: 12rem;
}

.hot-review-info > .like {
  color: #B71C1C;
}

.hot-review-info > span > i {
  margin-left: 0.5rem;
  color: black;
}
</style>