 <template>
  <div
    class="overflow-hidden"
    style="margin-left:8%;margin-right:8%; margin-bottom:-40px;"
  >
    <div>
      <div style="margin-top: 20px; width:100%;">
          <div style="float:left;">
            <FeedProfile :src="user.userImage" alt="profile" :userLevelImage="user.userLevel.levelImage" style="width:70px;height:70px;"/>
          </div>
          <div>
            <span style="color:#424242;font-weight:600; margin-left:10px; font-size:20px;"> {{ user.userNickname }} <br></span>
            <span style="color:#424242;font-weight:600; margin-left:10px; font-size:15px;">({{ user.userPoints }}/{{ user.userLevel.levelMaxPoint }})p<br></span>
            <span style="color:#424242;margin-left:10px; font-size:13px;">{{ user.userLevel.level }} </span>
              <v-btn
                outlined
                color=#aaa
                fab
                x-small
                @click="isEditing = !isEditing" style="float:right; margin-right:10px;"
              >
                <v-icon v-if="isEditing" @click="save">
                  mdi-close
                </v-icon>
                <v-icon v-else>
                  mdi-pencil
                </v-icon>
              </v-btn>
          </div>
        </div>

      <v-text-field
        :disabled="!isEditing"
        placeholder="상태메세지" v-model="msg" style="margin-top:15px;"
      ></v-text-field>
    </div>
    <v-card-actions>
      <v-spacer></v-spacer>
    </v-card-actions>
    <v-snackbar
      v-model="hasSaved"
      :timeout="1000"
      absolute
      top
    >
      저장되었습니다.
    </v-snackbar>
  </div>
</template>

<script>
import FeedProfile from '../../components/common/FeedProfile.vue'
import { mapGetters } from 'vuex'

  export default {
    components: {
      FeedProfile
    },
    data () {
      return {
        hasSaved: false,
        isEditing: null,
        msg: "",
        chips: [],
        user: {                 // 유저 정보를 한번에 가져와서 저장해 놓을 객체
          "userEmail" : "",
          "userName" : "",
          "userNickname": "",
          "userPhone": "",
          "userPassword": "",
          "userProfileMessage": "",
          "userPoints": "",
          "userLevel": {
            "level": "",
            "levelMaxPoint": "",
            "levelImage": "",
          },
          "userImage": "",
          // "badges": {
            // },            
        }
      }
    },
    computed: {
      ...mapGetters({ email: 'getUserEmail' }),
    },
    methods: {
      getUserInfo()  {
        this.$Axios
        .get(`${this.$store.state.host}/v1/users/` + this.email)
        .then((res) => {
          if(res.data.status == "success") {
            this.user.userNickname = res.data.data.userNickname;
            this.user.userName = res.data.data.userName;
            this.user.userPhone = res.data.data.userPhone;
            this.user.userPassword = res.data.data.userPassword;
            this.user.userProfileMessage = res.data.data.userProfileMessage;
            this.user.userPoints = res.data.data.userPoints;
            this.user.userLevel.level = res.data.data.userLevel.level;
            this.user.userLevel.levelMaxPoint = res.data.data.userLevel.levelMaxPoint;
            this.user.userLevel.levelImage = res.data.data.userLevel.levelImage;
            this.user.userImage = res.data.data.userImage;
            this.msg = res.data.data.userProfileMessage;
            // this.badges = data.data.userNickname;
          } else {
            console.log('데이터 안넘어옴..');
          }
        })
        .catch((error) => {
          console.log(error);
        })
      },
      save () {
        
        this.$Axios
        .patch(`${this.$store.state.host}/v1/users/`+ this.email + '/profile', {
          "userEmail": this.user.userEmail,
          "userName": this.user.userName,
          "userNickname": this.user.userNickname,
          "userPassword": this.user.userPassword,
          "userPhone": this.user.userPhone,
          "userProfileMessage": this.msg
        })
        .then((res) => {
          if(res.data.status == "success") {
            this.hasSaved = true;
          } else {
            console.log('상메 안보내짐....');
          }
        })
        .catch((error) => {
          console.log(error);
        });
      },
      remove (item) {
        this.chips.splice(this.chips.indexOf(item), 1)
        this.chips = [...this.chips]
      },
    },
    created() {
      this.user.userEmail = this.email;   // 유저 이메일 정보를 로그인한 사람의 정보로
      console.log(this.user.userEmail);
      this.getUserInfo();    // 페이지 열자마자 유저 정보 가져옴.
    }
  }
</script>

<style>
.v-text-field {
    padding: 0;
    margin-top: 0;
}
</style>