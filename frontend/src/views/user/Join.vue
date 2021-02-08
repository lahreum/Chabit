<template>
  <div class="outside">
    <div class="logo"></div>
    <img class="chabit-logo" src="../../assets/img/chabit.jpg" alt="chabit" />
    <div class="inside">
      <div class="signup">
        <p>회원가입</p>
        <v-card-text class="text-input">
          <v-text-field v-model="form.name" label="이름"></v-text-field>
          <div class="input-with-btn">
            <v-text-field v-model="form.email" label="이메일" type="email"></v-text-field>
            <button class="btn-get-check">중복확인</button>
          </div>
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
        <div class="bottom">
          <button class="btn-signup">
            가입하기
          </button>
        </div>
      </div>
    </div>
    <br />
  </div>
</template>

<script>
import "./user.css";

export default {
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
    };
  },
  methods: {
    enforcePhoneFormat() {
      let x = this.form.phoneNumber.replace(/\D/g, "").match(/(\d{0,3})(\d{0,4})(\d{0,4})/);

      this.form.phoneNumber = !x[2] ? x[1] : "(" + x[1] + ") " + x[2] + (x[3] ? "-" + x[3] : "");
    },
  },
};
</script>
