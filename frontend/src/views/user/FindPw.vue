<template>
  <div class="find-pw">
    <v-card-text class="text-input">
      <v-text-field v-model="form.email" label="이메일" type="email"></v-text-field>
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
      <button class="btn-next">
        다음
      </button>
    </div>
  </div>
</template>

<script>
import "./user.css";
export default {
  data() {
    return {
      form: {
        email: "",
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

<style></style>
