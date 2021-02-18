<template>
  <sequential-entrance fromBottom>
    <v-card class="manage-member" flat>
      <v-card-title color="white">
        <v-spacer></v-spacer>
        <v-autocomplete
          v-model="search"
          append
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-no-data
          hide-details
          clearable
          item-value="userName"
          item-text="userName"
          :items="memberInfo"
          :menu-props="{
            top: false,
            bottom: true,
            offsetY: true,
            offsetX: true,
          }"
        ></v-autocomplete>
        <v-spacer></v-spacer>
        <v-btn
          elevation="1"
          rounded
          plain
          depressed
          color="error"
          @click="dialog = !dialog"
        >
          Forced</v-btn
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
                  <strong>회원삭제</strong>
                </v-card-title>
                <v-card-text>강제로 회원을 탈퇴시키겠습니까?</v-card-text>
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
                    @click="deleteUserForced()"
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
                  <strong>회원삭제 결과</strong>
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
                    @click="afterDialog = !afterDialog"
                  >
                    확인
                  </v-btn>
                  <v-spacer></v-spacer> </v-card-actions
              ></v-card>
            </v-dialog>
          </v-row>
        </p>
        <!-- End AfterPopup -->
      </v-card-title>
      <v-data-table
        v-model="selected"
        :headers="headers"
        :items="memberInfo"
        item-key="userNickname"
        show-select
        single-select
        sort
        :search="search"
        class="elevation-1"
        disable-pagination
        hide-default-footer
      >
        <template
          v-slot:[`item.data-table-select`]="{ item, isSelected, select }"
        >
          <v-simple-checkbox
            v-ripple
            :value="isSelected"
            :readonly="checkRole(item.userRole)"
            :disabled="checkRole(item.userRole)"
            @input="select($event)"
          ></v-simple-checkbox>
        </template>
      </v-data-table>
    </v-card>
  </sequential-entrance>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      search: "",
      selected: [],
      disabledCount: 0,
      headers: [
        { text: "Name", value: "userName" },
        {
          text: "Email",
          align: "start",
          sortable: false,
          value: "userEmail",
        },
        { text: "Nickname", value: "userNickname" },
        { text: "Password", sortable: false, value: "userPassword" },
        { text: "Permission", value: "userRole" },
      ],
      memberInfo: [],
      dialog: false,
      afterDialog: false,
      afterMessage: "",
    };
  },
  computed: {
    ...mapGetters({ userRole: "getUserRole" }),
  },
  methods: {
    // 사용자 권한 확인
    checkRole(input) {
      if (input != "USER") return true;
      else return false;
    },

    // 데이터 가져오기
    getMemberList() {
      this.$Axios
        .get(`${this.$store.state.host}/v1/users`)
        .then((res) => {
          if (res.data.status == "success") {
            this.memberInfo = res.data.data;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    deleteUserForced() {
      if (this.userRole == "ADMIN") {
        let deleteEmail = this.selected[this.selected.length - 1].userEmail;
        this.$Axios
          .delete(`${this.$store.state.host}/v1/users/` + deleteEmail)
          .then((res) => {
            if (res.data.data.deleteResult == "success") {
              this.dialog = false;
              this.afterDialog = true;
              this.afterMessage = res.data.data.message;
              this.getMemberList();
            } else {
              this.afterMessage = res.data.data.message;
            }
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        this.dialog = false;
        this.afterDialog = true;
        this.afterMessage = "관리자만 사용이 가능합니다.";
      }
    },
  },
  mounted() {
    // 자동으로 함수 실행
    this.getMemberList();
  },
};
</script>
