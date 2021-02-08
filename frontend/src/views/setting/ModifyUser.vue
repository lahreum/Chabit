<template>
  <div class="outside">
    <div class="logo"></div>
    <img class="chabit-logo" src="../../assets/img/chabit.jpg" alt="chabit" />
    <div class="inside">
      <div class="modify-user">
        <p>회원정보 변경</p>
        <v-card-text class="text-input">
          <v-flex class="mb-4 text-center">
            <v-badge
              color="red darken-4"
              bordered
              bottom
              icon="mdi-camera-outline"
              offset-x="30"
              offset-y="30"
            >
              <v-avatar size="128" color="grey">
                <img src="../../assets/img/maja.png" />
              </v-avatar>
            </v-badge>
          </v-flex>
          <v-text-field disabled v-model="form.name" label="이름"></v-text-field>
          <v-text-field disabled v-model="form.email" label="이메일" type="email"></v-text-field>
          <v-text-field v-model="form.password" label="비밀번호" type="password"></v-text-field>
          <v-text-field v-model="form.confirm" label="비밀번호 확인" type="password"></v-text-field>
          <div class="input-with-btn">
            <v-text-field v-model="form.nickName" label="닉네임"></v-text-field
            ><button class="btn-get-check">중복확인</button>
          </div>
          <div class="input-with-btn">
            <v-text-field
              v-model="form.phoneNumber"
              label="연락처"
              type="tel"
              @input="enforcePhoneFormat()"
            ></v-text-field>
            <button class="btn-get-check">인증요청</button>
          </div>
          <div class="input-with-btn">
            <v-text-field v-model="form.certificationNumber" label="인증번호 입력"></v-text-field
            ><button class="btn-get-check">확인</button>
          </div>
        </v-card-text>
        <v-card-actions class="bottom"
          ><v-spacer></v-spacer><btn-small text="취소" class="btn-left"></btn-small
          ><v-spacer></v-spacer><btn-small text="저장"></btn-small><v-spacer></v-spacer>
        </v-card-actions>
        <!-- <avatar-picker
          v-model="showAvatarPicker"
          :current-avatar="form.avatar"
          @selected="selectAvatar"
        ></avatar-picker> -->
      </div>
    </div>
  </div>
</template>

<script>
import BtnSmall from "../../components/common/BtnSmall.vue";
import "../user/user.css";
export default {
  components: { BtnSmall },
  pageTitle: "My Profile",
  data() {
    return {
      form: {
        name: "",
        email: "",
        password: "",
        confirm: "",
        nickName: "",
        phoneNumber: "",
        certificationNumber: "",
      },
      // showAvatarPicker: false,
    };
  },
  methods: {
    enforcePhoneFormat() {
      let x = this.form.phoneNumber.replace(/\D/g, "").match(/(\d{0,3})(\d{0,4})(\d{0,4})/);

      this.form.phoneNumber = !x[2] ? x[1] : "(" + x[1] + ") " + x[2] + (x[3] ? "-" + x[3] : "");
    },
  },
  // methods: {
  //   openAvatarPicker() {
  //     this.showAvatarPicker = true;
  //   },
  //   selectAvatar(avatar) {
  //     this.form.avatar = avatar;
  //   },
  // },
};
</script>
