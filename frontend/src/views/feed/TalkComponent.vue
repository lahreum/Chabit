<template>
  <div
    class="talk"
    style="line-height: 1.2; height: 100%; font-family: 'Muli', sans-serif"
  >
    <!-- 댓글 부분 -->
    <div class="box">
      <article class="media">
        <div class="media-left">
          <figure class="image">
            <Profile
              :src="talk.userImage"
              :userLevelImage="talk.userLevelImage"
              style="margin-right: 20px"
            />
          </figure>
        </div>
        <div class="media-content">
          <div class="content">
            <p>
              <strong>{{ talk.userNickname }}</strong>
            </p>
            <!-- 댓글 내의 수정 또는 삭제 버튼을 클릭하면 이벤트 발생, 처리 -->
            <p v-if="!modifying2">{{ talk.commentContent }}</p>
            <div v-if="modifying2">
              <v-col cols="12" md="6">
                <v-textarea
                  v-if="talk.reviewCommentId == modifyNumber"
                  name="input-7-1"
                  rows="1"
                  row-height="5"
                  auto-grow
                  v-model="modifyContent"
                ></v-textarea>
              </v-col>
            </div>
            <!-- 수정중 아닐때-> 수정, 삭제 -->
            <div v-if="!modifying2">
              <span
                @click="toggleReply"
                style="
                  color: gray;
                  font-size: 10px;
                  font-weight: 600;
                  margin-right: 10px;
                "
                >답글달기</span
              >
              <span
                @click="
                  sendCommentInfo2(talk.reviewCommentId, talk.commentContent)
                "
                v-if="talk.userNickname == nickname"
                style="
                  color: gray;
                  font-size: 10px;
                  font-weight: 600;
                  margin-right: 10px;
                "
                >수정</span
              >
              <span
                @click="deleteComment(talk.reviewCommentId)"
                v-if="talk.userNickname == nickname"
                style="
                  color: gray;
                  font-size: 10px;
                  font-weight: 600;
                  margin-right: 10px;
                "
                >삭제</span
              >
            </div>
            <!-- 수정중일때 -> 저장 -->
            <div v-if="modifying2">
              <span
                @click="modifyComment(modifyNumber, modifyContent)"
                v-if="talk.userNickname == nickname"
                style="
                  color: gray;
                  font-size: 10px;
                  font-weight: 600;
                  margin-right: 10px;
                "
                >저장</span
              >
              <span
                @click="toggleModify2"
                v-if="talk.userNickname == nickname"
                style="
                  color: gray;
                  font-size: 10px;
                  font-weight: 600;
                  margin-right: 10px;
                "
                >취소</span
              >
            </div>

            <!-- <span @click="modifyComment" v-if="talk.userNickname == nickname" style="color:gray; font-size:10px; font-weight:600;margin-right:10px;">수정</span>
                <span @click="deleteComment" v-if="talk.userNickname == nickname" style="color:gray; font-size:10px; font-weight:600;margin-right:10px;">삭제</span> -->

            <!-- 답글달기 부분 -->
            <div v-if="writingReply">
              <v-col cols="12" md="6">
                <v-textarea
                  name="input-7-4"
                  clearable
                  auto-grow
                  rows="1"
                  row-height="5"
                  style="font-size: 15px"
                  full-width
                  v-model="reply"
                  @keyup.enter="addReply"
                ></v-textarea>
                <button
                  @click="addReply"
                  style="
                    margin-right: 20px;
                    color: gray;
                    font-size: 10px;
                    font-weight: 600;
                  "
                >
                  등록하기
                </button>
                <button
                  @click="toggleReply"
                  style="
                    color: gray;
                    font-size: 10px;
                    font-weight: 600;
                    margin-right: 10px;
                  "
                >
                  취소
                </button>
              </v-col>
            </div>
          </div>
        </div>
      </article>
    </div>
    <!-- 대댓글 부분 -->
    <div v-if="talk.replies.length">
      <div
        class="box"
        v-for="reply in this.talk.replies"
        v-bind:key="reply.reviewCommentId"
        style="margin-left: 40px; margin-top: 20px"
      >
        <article class="media">
          <div class="media-left">
            <figure class="image">
              <Profile
                :src="reply.userImage"
                :userLevelImage="reply.userLevelImage"
                style="margin-right: 20px"
              />
            </figure>
          </div>
          <div class="media-content">
            <!-- 대댓글 내용 -->
            <div class="content">
              <p>
                <strong>{{ reply.userNickname }}</strong>
              </p>
              <!-- 수정버튼을 눌러 modifying = true 만들었다면 기존 내용 적힌 textatrea로 대체함 -->
              <p v-if="!modifying">{{ reply.commentContent }}</p>
              <div v-if="modifying">
              <v-col cols="12" md="6">
                <v-textarea
                  v-if="reply.reviewCommentId == modifyNumber"
                  name="input-7-1"
                  v-model="modifyContent"
                  auto-grow
                  rows="1"
                  row-height="5"
                ></v-textarea>
              </v-col>
              </div>
              <!-- 수정, 삭제 이벤트 처리 부분 -->
              <div v-if="!modifying">
                <span
                  @click="
                    sendCommentInfo(reply.reviewCommentId, reply.commentContent)
                  "
                  v-if="reply.userNickname == nickname"
                  style="
                    color: gray;
                    font-size: 10px;
                    font-weight: 600;
                    margin-right: 10px;
                  "
                  >수정</span
                >
                <span
                  @click="deleteComment(reply.reviewCommentId)"
                  v-if="reply.userNickname == nickname"
                  style="
                    color: gray;
                    font-size: 10px;
                    font-weight: 600;
                    margin-right: 10px;
                  "
                  >삭제</span
                >
              </div>
              <!-- 수정중일때 -> 저장, -->
              <div v-if="modifying">
                <span
                  @click="modifyComment(modifyNumber, modifyContent)"
                  v-if="reply.userNickname == nickname"
                  style="
                    color: gray;
                    font-size: 10px;
                    font-weight: 600;
                    margin-right: 10px;
                  "
                  >저장</span
                >
                <span
                  @click="toggleModify"
                  v-if="reply.userNickname == nickname"
                  style="
                    color: gray;
                    font-size: 10px;
                    font-weight: 600;
                    margin-right: 10px;
                  "
                  >취소</span
                >
              </div>
            </div>
          </div>
        </article>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import Profile from "../../components/common/Profile.vue";

export default {
  data() {
    return {
      reply: "",
      writingReply: false,
      modifying: false,
      modifying2: false,
      deleting: false,
      modifyContent: "",
      modifyNumber: 0,
    };
  },
  components: {
    Profile,
  },
  props: {
    talk: Object,
  },
  computed: {
    ...mapGetters({
      email: "getUserEmail",
      nickname: "getUserNickname",
    }),
  },
  methods: {
    addReply() {
      if (this.reply.length >= 1) {
        this.$Axios
          .post(
            `${this.$store.state.host}/v1/review/` +
              this.talk.reviewId +
              `/comment/` +
              this.talk.reviewCommentId,
            { commentContent: this.reply, userEmail: this.email }
          )
          .then((res) => {
            if (res.data.status === "success") {
              console.log("대댓글 성공");
              this.toggleReply();
              this.clearInput();
              window.location.reload();
            } else {
              console.log("대댓글 에러");
            }
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        alert("내용을 입력해주세요!");
      }
    },
    toggleReply() {
      this.writingReply = !this.writingReply;
    },
    toggleModify() {
      this.modifying = !this.modifying;
    },
    toggleModify2() {
      this.modifying2 = !this.modifying2;
    },
    toggleDelete() {
      this.deleting = !this.deleting;
    },
    clearInput: function () {
      this.reply = "";
    },
    sendCommentInfo(reviewCommentId, commentContent) {
      // 댓글 수정
      this.toggleModify();
      this.modifyNumber = reviewCommentId;
      this.modifyContent = commentContent;
    },
    sendCommentInfo2(reviewCommentId, commentContent) {
      // 댓글 수정
      this.toggleModify2();
      this.modifyNumber = reviewCommentId;
      this.modifyContent = commentContent;
    },
    modifyComment() {
      this.$Axios
        .put(
          `${this.$store.state.host}/v1/review/comment/` + this.modifyNumber,
          { commentContent: this.modifyContent, userEmail: this.email }
        )
        .then((res) => {
          if (res.data.status === "success") {
            console.log("댓글 수정 성공");
            window.location.reload();
          } else {
            console.log("댓글 수정 실패");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteComment(reviewCommentId) {
      // 댓글 삭제
      this.$Axios
        .delete(
          `${this.$store.state.host}/v1/review/comment/` +
            this.email +
            "/" +
            reviewCommentId
        )
        .then((res) => {
          if (res.data.status === "success") {
            console.log("댓글 삭제 성공!");
            window.location.reload();
          } else {
            console.log("댓글 삭제 실패");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style>
.talk {
  background-color: #fff;
  color: #373737;
  box-shadow: 0 2px 3px rgba(10,10,10,0.1), 0 0 0 1px rgba(10,10,10,0.1);
  padding: 20px;
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