<template>
<sequential-entrance fromBottom> 
  <v-app class="setting">
    <v-list>
      <!-- 계정설정 -->
      <v-list-group color="#B71C1C" no-action>
        <template v-slot:activator>
          <v-list-item-title class="font-weight-black"
            >계정설정</v-list-item-title
          >
        </template>
        <v-list-item router-link :to="{ name: 'ModifyUser' }">
          <v-list-item-content>
            <v-list-item-title class="font-weight-medium"
              >회원정보 수정</v-list-item-title
            >
          </v-list-item-content>
        </v-list-item>
        <v-list-item v-ripple="{ center: true }">
          <v-list-item-content>
            <v-list-item-title
              class="font-weight-medium"
              @click="dialog = !dialog"
              >회원탈퇴</v-list-item-title
            >
            <!-- Start Popup dialog -->
            <p v-if="dialog">
              <v-row justify="center">
                <v-dialog
                  v-model="dialog"
                  persistent
                  max-width="290"
                  content-class="rounded-xl"
                >
                  <v-card class="rounded-xl justify-center text-sm-center">
                    <v-card-title class="headline justify-center rounded-xl">
                      <strong>회원탈퇴</strong>
                    </v-card-title>
                    <v-card-text
                      >탈퇴시 3개월 동안<br />
                      재가입할 수 없습니다.</v-card-text
                    >
                    <v-divider></v-divider>
                    <v-card-actions class="rounded-xl">
                      <v-spacer></v-spacer>
                      <v-btn text @click="dialog = false"> 취소 </v-btn>
                      <v-spacer></v-spacer>
                      <v-divider vertical></v-divider>
                      <v-spacer></v-spacer>
                      <v-btn
                        color="red darken-4"
                        plain
                        :ripple="false"
                        text
                        @click="deleteUser()"
                      >
                        탈퇴
                      </v-btn>
                      <v-spacer></v-spacer> </v-card-actions
                  ></v-card>
                </v-dialog>
              </v-row>
            </p>
            <!-- End Popup dialog -->
            <!-- Start AfterPopup -->
            <p v-if="afterDialog">
              <v-row justify="center">
                <v-dialog
                  v-model="afterDialog"
                  persistent
                  max-width="290"
                  content-class="rounded-xl"
                >
                  <v-card class="rounded-xl justify-center text-sm-center">
                    <v-card-title class="headline justify-center rounded-xl">
                      <strong>회원탈퇴 결과</strong>
                    </v-card-title>
                    <v-card-text>{{ this.afterMessage }}</v-card-text>
                    <v-divider></v-divider>
                    <v-card-actions class="rounded-xl">
                      <v-spacer></v-spacer>
                      <v-btn
                        color="red darken-4"
                        plain
                        :ripple="false"
                        text
                        @click="movePage()"
                      >
                        확인
                      </v-btn>
                      <v-spacer></v-spacer> </v-card-actions
                  ></v-card>
                </v-dialog>
              </v-row>
            </p>
            <!-- End AfterPopup -->
          </v-list-item-content>
        </v-list-item>
      </v-list-group>
      <!-- 회원관리 -->
      <v-list-group
        v-if="checkRole(userRole)"
        color="#B71C1C"
        @click="$router.push({ name: 'ManageMember' })"
      >
        <template v-slot:activator>
          <v-list-item-title class="font-weight-black"
            >회원관리</v-list-item-title
          >
        </template>
      </v-list-group>
    </v-list>
  </v-app>
  </sequential-entrance> 
</template>

<script>
import "./setting.css";
import { mapGetters } from "vuex";

export default {
  data: () => ({
    dialog: false,
    afterDialog: false,
    afterMessage: "",
  }),
  computed: {
    ...mapGetters({ userEmail: "getUserEmail" }),
    ...mapGetters({ userRole: "getUserRole" }),
  },
  methods: {
    checkRole(input) {
      if (input == "ADMIN") return true;
      else return false;
    },

    deleteUser() {
      if (this.userEmail != null) {
        this.$Axios
          .delete(`${this.$store.state.host}/v1/users/` + this.userEmail)
          .then((res) => {
            if (res.data.data.deleteResult == "success") {
              this.dialog = false;
              this.afterDialog = true;
              this.afterMessage = res.data.data.message;
            } else {
              this.afterDialog = true;
              this.afterMessage = res.data.data.message;
            }
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        this.dialog = false;
        this.afterDialog = true;
        this.afterMessage = "로그인 상태가 아닙니다.";
      }
    },
    movePage() {
      localStorage.removeItem("vuex");
      this.$router.push("/login");
      window.location.reload();
    },
  },
};
</script>
