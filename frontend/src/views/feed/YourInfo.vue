 <template>
  <div
    class="overflow-hidden"
    style="margin-left: 8%; margin-right: 8%; margin-bottom: -40px"
  >
    <div>
      <div style="margin-top: 20px; width: 100%">
        <div style="float: left">
          <FeedProfile
            :src="user.userImage"
            alt="profile"
            :userLevelImage="user.userLevel.levelImage"
            style="width: 70px; height: 70px"
          />
        </div>
        <div>
          <span
            style="
              color: #424242;
              font-weight: 600;
              margin-left: 10px;
              font-size: 20px;
            "
          >
            {{ this.user.userNickname }} <br
          /></span>
          <span
            style="
              color: #424242;
              font-weight: 600;
              margin-left: 10px;
              font-size: 15px;
            "
            >({{ this.user.userPoints }}/{{
              this.user.userLevel.levelMaxPoint
            }})p<br
          /></span>
          <span style="color: #424242; margin-left: 10px; font-size: 13px"
            >{{ this.user.userLevel.level }}
          </span>
        </div>

        <v-text-field
          :disabled="!isEditing"
          placeholder="상태메세지"
          v-model="this.user.userProfileMessage"
          style="margin-top: 15px"
        ></v-text-field>
      </div>
      <v-btn
        @click="doFollowing"
        v-if="!flag"
        style="margin-bottom: 20px"
        elevation="0"
        color="rgb(225, 245, 254)"
        width="100%"
        ><span class="fix-font-mid" style="color: rgb(21, 101, 192)"
          >팔로우 하기</span
        >
      </v-btn>
      <v-btn
        @click="cancelFollowing"
        v-else
        style="margin-bottom: 20px"
        elevation="0"
        color="rgb(255, 241, 241)"
        width="100%"
        ><span class="fix-font-mid" style="color: rgb(183, 28, 28)"
          >언팔로우 하기</span
        >
      </v-btn>
    </div>
  </div>
</template>

<script>
import FeedProfile from "../../components/common/FeedProfile.vue";
import { mapGetters } from "vuex";

export default {
  components: {
    FeedProfile,
  },
  data() {
    return {
      flag: false,
      followings: [],
      hasSaved: false,
      isEditing: null,
      status: "",
      chips: [],
      user: {
        // 유저 정보를 한번에 가져와서 저장해 놓을 객체
        userEmail: "",
        userName: "",
        userNickname: "",
        userPhone: "",
        userPassword: "",
        userProfileMessage: "",
        userPoints: "",
        userLevel: {
          level: "",
          levelMaxPoint: "",
          levelImage: "",
        },
        userImage: "",
      },
    };
  },
  computed: {
    ...mapGetters({
      yourEmail: "getYourEmail",
      email: "getUserEmail",
    }),
  },
  methods: {
    getUserInfo() {
      this.$Axios
        .get(`${this.$store.state.host}/v1/users/` + this.yourEmail)
        .then((res) => {
          if (res.data.status == "success") {
            this.user.userNickname = res.data.data.userNickname;
            this.user.userName = res.data.data.userName;
            this.user.userPhone = res.data.data.userPhone;
            this.user.userPassword = res.data.data.userPassword;
            this.user.userProfileMessage = res.data.data.userProfileMessage;
            this.user.userPoints = res.data.data.userPoints;
            this.user.userLevel.level = res.data.data.userLevel.level;
            this.user.userLevel.levelMaxPoint =
              res.data.data.userLevel.levelMaxPoint;
            this.user.userLevel.levelImage = res.data.data.userLevel.levelImage;
            this.user.userImage = res.data.data.userImage;
          } else {
            console.log("데이터 안넘어옴..");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getFollowings() {
      this.$Axios
        .get(`${this.$store.state.host}/v1/follow/` + this.email)
        .then((res) => {
          if (res.data.status === "success") {
            this.followings = res.data.data.followings.followings;

            for (var follow of this.followings) {
              if (follow.userEmail === this.yourEmail) {
                this.flag = true;
              }
            }
          } else {
            console.log("팔로잉 정보 가져오지 못함");
          }
        });
    },
    doFollowing() {
      this.$Axios
        .post(`${this.$store.state.host}/v1/follow`, {
          followingEmail: this.yourEmail,
          userEmail: this.email,
        })
        .then((res) => {
          if (res.data.status === "success") {
            console.log("팔로잉 성공!");
            window.location.reload();
          } else {
            console.log("팔로잉 실패");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    cancelFollowing() {
      this.$Axios
        .delete(`${this.$store.state.host}/v1/follow`, {
          data: {
            //////// 질문 필요
            followingEmail: this.yourEmail,
            userEmail: this.email,
          },
        })
        .then((res) => {
          if (res.data.status === "success") {
            window.location.reload();
            console.log("팔로우 취소 성공");
          } else {
            console.log("팔로우 취소 실패");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  created() {
    this.getUserInfo(); // 페이지 열자마자 유저 정보 가져옴.
    this.getFollowings(); // 팔로우 정보 가져옴
  },
};
</script>

<style>
.v-text-field {
  padding: 0;
  margin-top: 0;
}
</style>