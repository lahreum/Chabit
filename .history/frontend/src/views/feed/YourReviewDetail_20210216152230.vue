<template>
<div style="width:100%; height:100%;">
  <div>
    <Profile :src="this.yourImage" style="float:left;margin-left:10px;margin-bottom:10px;margin-top:20px;"/><br>
    <div style="float:left; margin-left:12px;margin-top:15px;"><strong>{{ this.yourNickname }}</strong></div>
  </div>
    <v-carousel :show-arrows="false" height="280" style="margin-top:10px;" >
      <v-carousel-item
        v-for="(oneImage,i) in this.review.reviewImages"
        :key="i"
        :src="oneImage" 
      ></v-carousel-item>
    </v-carousel>

    <div style="margin: 20px;">
      <span style="font-size:12px;" >
        <!-- 채워진 하트, 안채워진 하트 -->
        <i class="fa fa-heart fa-2x" style="margin-right:10px;" v-on:click="addgood" v-if="this.review.pressCool" ></i>
        <i class="far fa-heart fa-2x" style="margin-right:10px;"  v-on:click="addgood" v-if="!this.review.pressCool" ></i>
      </span>
      <span style="font-size:12px;">
        <router-link to="/review-comment" style="text-decoration:none;color:inherit;"><i class="far fa-comment fa-2x" style="margin-right:10px;" ></i></router-link>
      </span>
      <span style="font-size:12px;">
        <i class="fas fa-share-alt fa-2x" ></i>
      </span>
    </div>
    <div style="margin: 15px;">
      <p style="font-weight:600"> 멋져요 {{ this.review.coolCount }} 개</p>
    </div>
    <div style="margin: 15px;">
      {{ this.review.reviewContent }}
      <br>
    </div>
    <div style="margin-left: 15px;">
      <router-link to="/review-comment" style="text-decoration:none;color:inherit;"><p style="color:gray; font-size:12px;">댓글 {{ this.review.commentCount }} 개 더보기..</p></router-link>
      <p style="color:gray; font-size:10px;">
        {{ this.review.reviewDate }}
      </p>
      </div>
  </div>
</template>

<script>
import Profile from '../../components/common/Profile.vue';
import { mapGetters } from 'vuex';

export default {
  components: {
      Profile
  },
  data: () => ({
      // count: 0,
      // choose: false,
      review: {
        "reviewId": 0,
        "reviewContent": "",
        "reviewDate": "",
        "coolCount": 0,
        "pressCool": false,
        "commentCount": 0,
        "userId": 0,
        "challengeId": 0,
        "reviewImages": [],
      }
  }),
  methods: {
    addgood: function() {
      this.$Axios
      .post(`${this.$store.state.host}/v1/review/` + this.review.reviewId + '/cool?userEmail=' + this.email)
      .then((res) => {
        if(res.data.status == "success") {
          console.log("좋아요 성공");
          this.getReviewDetail();   // 좋아요 누르고나서 다시 내역 받아옴
        } else {
          console.log("좋아요 실패");
        }
      })
      .catch((error) => {
        console.log(error);
      })
    },
    getReviewDetail() {
      this.$Axios
      .get(`${this.$store.state.host}/v1/review/detail/${this.$store.state.reviewDetailId}` + '?userEmail=' + this.email)
      .then((res) =>{
        if(res.data.status === "success") {
          console.log("리뷰상세보기 성공");
          this.review.reviewId = res.data.data.reviewId;
          this.review.reviewContent = res.data.data.reviewContent;
          this.review.reviewData = res.data.data.reviewData;
          this.review.coolCount = res.data.data.coolCount;
          this.review.pressCool = res.data.data.pressCool;
          this.review.commentCount = res.data.data.commentCount;
          this.review.userId = res.data.data.userId;
          this.review.challengeId = res.data.data.challengeId;
          this.review.reviewImages = res.data.data.reviewImages;
        } else {
          console.log("리뷰상세보기 성공");
        }
      })
      .catch((error) => {
        console.log(error);
      })
    },
  },
  computed: {
    ...mapGetters({ 
      yourEmail: 'getYourEmail',
      yourImage: 'getYourImage',
      yourNickname: 'getYourNickname',}),
  },
  created() {
    this.getReviewDetail();
  }
}
</script>

<style>

</style>