<template>
  <div class="find-email">
    <v-card-text class="text-input">
      <v-text-field
        :error-messages="nameErrors"
        v-model="userName"
        label="이름"
        required
        @input="$v.userName.$touch()"
        @blur="$v.userName.$touch()"
      ></v-text-field>
      <v-text-field
        :error-messages="phoneErrors"
        v-model="userPhone"
        label="연락처"
        type="tel"
        required
        @input="$v.userPhone.$touch()"
        @blur="$v.userPhone.$touch()"
      ></v-text-field>
    </v-card-text>
    <div class="bottom">
      <button class="btn-next" @click.prevent="onSubmit" :disabled="submitStatus === 'PENDING'">
        다음
      </button>
      <div class="btn-login-signup">
        <a href="/login">로그인</a>
        <span> | </span>
        <a href="/join">회원가입</a>
      </div>
    </div>
  </div>
</template>

<script>
import "./user.css";
import { required, minLength, numeric, maxLength } from "vuelidate/lib/validators";

export default {
  validations: {
    userName: { required },
    userPhone: { required, numeric, minLength: minLength(11), maxLength: maxLength(11) },
  },

  data() {
    return {
      userName: "",
      userPhone: "",
      userEmail: "",
      submitStatus: null, // 다음 버튼 활성화 수준
      memberInfo: [],
      findName: false,
      findPNum: false,
    };
  },

  computed: {
    // 에러체크
    nameErrors() {
      const errors = [];
      if (!this.$v.userName.$dirty) return errors;
      !this.$v.userName.required && errors.push("필수 입력항목입니다.");
      return errors;
    },

    phoneErrors() {
      const errors = [];
      if (!this.$v.userPhone.$dirty) return errors;
      !this.$v.userPhone.required && errors.push("필수 입력입력항목입니다.");
      !this.$v.userPhone.numeric && errors.push("숫자(11자리)만 입력 가능합니다.");
      !this.$v.userPhone.minLength && errors.push("숫자(11자리)만 입력 가능합니다.");
      !this.$v.userPhone.maxLength && errors.push("숫자(11자리)만 입력 가능합니다.");
      return errors;
    },
  },

  methods: {
    // 전체 가입자 정보 가져오기
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

    // 전체가입자 중 이름 및 전화번호 일치하는 사람 찾기
    findUser() {
      for (var i = 0; i < this.memberInfo.length; i++) {
        if (this.memberInfo[i].userName == this.userName) {
          if (this.memberInfo[i].userPhone == this.userPhone) {
            this.findName = true;
            this.findPNum = true;
            this.userEmail = this.memberInfo[i].userEmail;
            break;
          } else {
            this.findName = true;
          }
        }
      }
    },

    // 지연발생
    sleep(ms) {
      return new Promise((r) => setTimeout(r, ms));
    },
    // 다음버튼 눌러서 이메일 찾아주기
    async onSubmit() {
      this.$v.$touch();
      if (this.$v.$invalid) {
        this.submitStatus = "ERROR";
      } else {
        this.submitStatus = "PENDING";
        this.getMemberList();
        await this.sleep(500);
        this.findUser();
        if (this.findName && this.findPNum) {
          this.$router.push({
            name: "FindEmailFinish",
            params: {
              userName: this.userName,
              userEmail: this.userEmail,
            },
          });
        } else {
          alert("일치하는 회원정보를 찾을 수 없습니다.");
        }
        () =>
          setTimeout(() => {
            this.submitStatus = "OK";
          });
      }
    },
  },
};
</script>
