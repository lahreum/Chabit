<template>
  <v-row>
    <v-col
      v-for="review in reviews"
      :key="review.reviewId"
      class="d-flex child-flex"
      cols="4"
    >
      <div @click="moveToReviewDetail(review)">
        <v-img v-if="review.reviewId" :src="review.reviewImages[0]" alt="photo" class="review-photo" style="width:100px;height:100px;"/>
      </div>
        <template v-slot:placeholder>
          <v-row
            class="fill-height ma-0"
            align="center"
            justify="center"
          >
            <v-progress-circular
              indeterminate
              color="grey lighten-5"
            ></v-progress-circular>
          </v-row>
        </template>
    </v-col>
  </v-row>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  data() {
    return {
      reviews: [

      ],
    }
  },
  computed: {
    ...mapGetters({ email: 'getYourEmail'}),
  },
  created() {
    this.getReviewList(); // 페이지 열자마자 리뷰리스트 출력
  },
  methods: {
    getReviewList() {
      this.$Axios
      .get(`${this.$store.state.host}/v1/review/` + this.email)
      .then((res) => {
        if(res.data.status === "success") {
          console.log('리뷰리스트 잘 가져옴');
          this.reviews = res.data.data;
        } else {
          console.log('리뷰리스트 가져오기 실패!!!!!!!!');
        }
      })
      .catch((error) => {
        console.log(error);
      })
    },
    moveToReviewDetail(review){
      this.$store.commit("MOVETOREVIEWDETAIL", review.reviewId);
      this.$router.push("/your-review-detail");
    }
  },
}
</script>

<style>
.review-photo {
  width: 100%;
  height: 100%;
}
</style>