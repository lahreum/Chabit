<template>
  <div class="outside">
    <div class="logo"></div>
    <img class="chabit-logo" src="../../assets/img/chabit.jpg" alt="chabit" />
    <div class="inside">
      <div class="change-password">
        <p>비밀번호 변경</p>
        <v-card-text class="text-input">
          <v-text-field
            :error-messages="passwordErrors"
            v-model="userPassword"
            label="비밀번호"
            type="password"
            required
            @input="$v.userPassword.$touch()"
            @blur="$v.userPassword.$touch()"
          >
          </v-text-field>
          <v-text-field
            :error-messages="confirmErrors"
            v-model="confirm"
            label="비밀번호 확인"
            type="password"
            required
            @input="$v.confirm.$touch()"
            @blur="$v.confirm.$touch()"
          >
          </v-text-field>
        </v-card-text>
        <div class="bottom">
          <button
            class="btn-next"
            type="submit"
            @click.prevent="onSubmit"
            :disabled="submitStatus === 'PENDING'"
          >
            다음
          </button>
        </div>
      </div>
    </div>
    <br />
  </div>
</template>

<script>
import "./user.css";
import { required, minLength, sameAs, helpers } from "vuelidate/lib/validators";

const password = helpers.regex("password", /^.*(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&*]).*$/);

export default {
  validations: {
    userPassword: { required, minLength: minLength(5), password },
    confirm: { required, sameAsPassword: sameAs("userPassword") },
  },

  data() {
    return {
      userPassword: "",
      confirm: "",
      submitStatus: null, // 다음버튼 기능 활성화 수준
      form: [],
    };
  },

  props: {
    userName: { type: String, default: "" },
    userNickname: { type: String, default: "" },
    userProfileMessage: { type: String, default: "" },
    userEmail: { type: String, default: "" },
    userPhone: { type: String, default: "" },
  },

  computed: {
    passwordErrors() {
      const errors = [];
      if (!this.$v.userPassword.$dirty) return errors;
      !this.$v.userPassword.minLength &&
        errors.push("영문, 숫자, 특수문자(!@#$%^&*)로 5글자 이상 입력해주세요.");
      !this.$v.userPassword.required && errors.push("필수 입력입력항목입니다.");
      !this.$v.userPassword.password &&
        errors.push("영문, 숫자, 특수문자(!@#$%^&*)로 5글자 이상 입력해주세요.");
      return errors;
    },
    confirmErrors() {
      const errors = [];
      if (!this.$v.confirm.$dirty) return errors;
      !this.$v.confirm.required && errors.push("필수 입력입력항목입니다.");
      !this.$v.confirm.sameAsPassword && errors.push("입력한 비밀번호와 일치하지 않습니다.");
      return errors;
    },
  },

  methods: {
    onSubmit() {
      this.$v.$touch();
      if (this.$v.$invalid) {
        this.submitStatus = "ERROR";
      } else {
        this.submitStatus = "PENDING";
        this.form = {
          userName: this.userName,
          userEmail: this.userEmail,
          userPassword: this.userPassword,
          userNickname: this.userNickname,
          userPhone: this.userPhone,
          userProfileMessage: this.userProfileMessage,
        };
        this.$Axios
          .put(`${this.$store.state.host}/v1/users/` + this.userEmail, this.form)
          .then((res) => {
            if (res.data.status == "success") {
              alert("회원정보 변경이 완료되었습니다.");
              this.$router.push("/login");
            } else {
              console.log(res);
              alert("회원정보 변경에 실패하였습니다.");
            }
          })
          .catch((err) => {
            console.log(err);
          });
        setTimeout(() => {
          this.submitStatus = "OK";
        }, 500);
      }
    },
  },
};
</script>
