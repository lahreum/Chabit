<template>
  <div>
    <div>
      <TalkComponent v-for="talk in this.comments" :talk="talk" v-bind:key="talk.reviewCommentId"/>
    </div>
    <br><br><br><br>

    <!-- 하단 댓글 작성란 -->
    <v-bottom-navigation
    color="primary"
    horizontal fixed height="80"
    >
        <Profile :src="this.userImage" :userLevelImage="levelImage" style="margin:10px;"/><br>
        <v-text-field label="댓글 달기..." v-model="oneComment" v-on:keyup.enter="addComment" style="margin-top:20px; margin-left:10px;"></v-text-field>
        <v-btn @click="addComment" >게시</v-btn>
  
    </v-bottom-navigation>
  </div>
</template>

<script>
import TalkComponent from './TalkComponent.vue';
import Profile from '../../components/common/Profile.vue';
import { mapGetters } from 'vuex';

export default {
  components: {
    TalkComponent,
    Profile
  },
  computed: {
    ...mapGetters({ 
      userImage: 'getUserImage',
      email: 'getUserEmail',
      levelImage: 'getUserLevelImage' })
  },
  data(){
    return {
      user_type: 'all',
      oneComment: "",
      review: {
        "userNickname": "",
        "userImage": "",
        "userLevelImage": "",
        "reviewContent": "",
        "reviewDate": "",
        "reviewImages": [],
      },
    comments:[],
    }
  },
    methods: {
      addComment: function() {
        this.$Axios
        .post(`${this.$store.state.host}/v1/review/${this.$store.state.reviewDetailId}/comment`,{"commentContent": this.oneComment, "userEmail": this.email })
        .then((res) => {
          if(res.data.status === "success") {
            console.log('댓글작성 성공');
          } else {
            console.log('댓글작성 실패');
          }
        })
        .catch((error)=> {
          console.log(error);
        })
        this.clearInput();
        window.location.reload();
      },
      clearInput: function() {
        this.oneComment = ' ';
      },
      getReviewDetail() {   // 필요: 리뷰 작성자 프사, 리뷰 작성자 닉네임, 리뷰 작성자 레벨이미지, 리뷰 내용, 리뷰 작성일
        this.$Axios
        .get(`${this.$store.state.host}/v1/review/detail/${this.$store.state.reviewDetailId}` + '?userEmail=' + this.email)
        .then((res) =>{
          if(res.data.status === "success") {
            this.review.userNickname = res.data.data.userNickname;
            this.review.userImage = res.data.data.userImage;
            this.review.userLevelImage = res.data.data.userLevelImage;
            this.review.reviewContent = res.data.data.reviewContent;
            this.review.reviewDate = res.data.data.reviewDate;
            this.review.reviewImages = res.data.data.reviewImages;
          } else {
            console.log("리뷰 상세보기 실패");
          }
        })
        .catch((error) => {
          console.log(error);
        })
      },
      getCommentList() {
        this.$Axios
        .get(`${this.$store.state.host}/v1/review/${this.$store.state.reviewDetailId}/comment` )
        .then((res) => {
          if(res.data.status === "success") {
            this.comments = res.data.data;
          } else {
            console.log('리뷰 코멘트 불러오기 실!패!')
          }
        })
        .catch((error)=> {
          console.log(error);
        })
      },
    },
    created() {
      this.getCommentList();
    }
}
</script>
