<template>
  <div class="talk" style="line-height: 1.2; height: 100%;
    font-family: 'Muli', sans-serif;" >
    <!-- 댓글 부분 -->
      <div class="box">
        <article class="media">
          <div class="media-left">
            <figure class="image">
              <Profile :src="talk.userImage" style="margin-right:20px;"/>
            </figure>
          </div>
          <div class="media-content" >
            <div class="content">
              <p><strong>{{talk.userNickname}}</strong></p>
              <p>{{talk.commentContent}}</p>
              <p @click="toggleReply" style="color:gray; font-size:10px; font-weight:600;">답글달기</p>
              <div v-if="writingReply">
                <v-col
                cols="12"
                md="6"
                >
                  <v-textarea
                    name="input-7-4"
                    placeholder="답글 달기"
                    clearable
                    full-width
                    v-model="reply" @keyup.enter="addReply"
                  ></v-textarea>
                  <button @click="addReply" style="margin-right:20px;">등록하기</button>
                  <button @click="toggleReply">취소</button>
                </v-col>
              </div>
            </div>
          </div>
        </article>
      </div>
      <!-- 대댓글 부분 -->
      <div v-if="talk.replies.length">
        <div class="box" v-for="reply in this.talk.replies" v-bind:key="reply.reviewCommentId" style="margin-left:40px; margin-top:20px;">
          <article class="media">
            <div class="media-left">
              <figure class="image">
                <Profile :src="reply.userImage" style="margin-right:20px;"/>
              </figure>
            </div>
            <div class="media-content" >
              <div class="content">
                <p><strong>{{ reply.userNickname }}</strong></p>
                <p>{{ reply.commentContent }}</p>
              </div>
            </div>
          </article>
        </div>
      </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import Profile from '../../components/common/Profile.vue'

export default {
  data() {
    return {
      reply: "",
      writingReply: false,
    }
  },
  components: {
    Profile
  },
  props: {
    talk: Object  
  },
  computed: {
    ...mapGetters({ email: 'getUserEmail',})
  },
  methods: {
    addReply() {
      this.$Axios
      .post(`${this.$store.state.host}/v1/review/` + this.talk.reviewId + `/comment/` +this.talk.reviewCommentId, {"commentContent": this.reply, "userEmail": this.email})
      .then((res) => {
        if(res.data.status === "success") {
          console.log('대댓글 성공');
          this.toggleReply();
          this.clearInput();
          window.location.reload();
        } else {
          console.log('대댓글 에러')
        }
      })
      .catch((error)=>{
        console.log(error);
      })
    },
    toggleReply() {
      this.writingReply = !this.writingReply;
    },
    clearInput: function() {
      this.reply = "";
    },
  }
}
</script>

<style>
.talk {
  background-color: #fff;
  color: #373737;
  box-shadow: 0 2px 3px rgba(10,10,10,.1), 0 0 0 1px rgba(10,10,10,.1);
  padding: 30px;
  /* + .tweet {
    margin-top: 20px;
  } */
}
.media {
  display: flex;
}
img {
  width: 64px;
  margin-right: 15px;
}
small {
  font-size: 14px;
  color: #657786;
}
</style>