<template>
<sequential-entrance fromBottom> 
  <div class="outside">
    <div class="logo"></div>
    <img class="chabit-logo" src="../../assets/img/chabit.jpg" alt="chabit" />
    <div class="inside">
      <sequential-entrance fromBottom>
      <div class="login">
        <p>로그인</p>
        <v-card-text class="text-input">
          <v-text-field
            :error-messages="emailErrors"
            v-model="userEmail"
            required
            label="이메일"
            type="email"
            @keyup.enter="onLogin"
            @input="$v.userEmail.$touch()"
            @blur="$v.userEmail.$touch()"
          >
          </v-text-field>
          <v-text-field
            :error-messages="passwordErrors"
            v-model="userPassword"
            label="비밀번호"
            type="password"
            required
            @keyup.enter="onLogin"
            @input="$v.userPassword.$touch()"
            @blur="$v.userPassword.$touch()"
          >
          </v-text-field>
        </v-card-text>
        <div class="bottom">
          <button
            class="btn-login"
            @click.prevent="onLogin"
            type="submit"
            :disabled="submitStatus === 'PENDING'"
          >
            로그인
          </button>
          <div class="btn-forget-signup">
            <a href="/find">이메일/비밀번호찾기</a>
            <span> | </span>
            <a href="/join">회원가입</a>
          </div>
          <div class="btn-social-login"></div>
        </div>
      </div>
      </sequential-entrance> 
    </div>
  </div>
  </sequential-entrance> 
</template>

<script>
import "../../assets/css/font.css";
import { required, minLength, email, helpers } from "vuelidate/lib/validators";
import { mapGetters } from "vuex";

const password = helpers.regex(
  "password",
  /^.*(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&*]).*$/
);

export default {
  validations: {
    userEmail: { required, email },
    userPassword: { required, minLength: minLength(5), password },
  },

  computed: {
    ...mapGetters({ userNickname: "getUserNickname" }),

    emailErrors() {
      const errors = [];
      if (!this.$v.userEmail.$dirty) return errors;
      !this.$v.userEmail.email &&
        errors.push("이메일 형식이 올바르지 않습니다.");
      !this.$v.userEmail.required && errors.push("필수 입력항목입니다.");
      return errors;
    },

    passwordErrors() {
      const errors = [];
      if (!this.$v.userPassword.$dirty) return errors;
      !this.$v.userPassword.minLength &&
        errors.push("비밀번호 입력 형식이 올바르지 않습니다.");
      !this.$v.userPassword.required && errors.push("필수 입력입력항목입니다.");
      !this.$v.userPassword.password &&
        errors.push("비밀번호 입력 형식이 올바르지 않습니다.");
      return errors;
    },
  },

  data() {
    return {
      loading: false,
      form: "",
      userEmail: "",
      userPassword: "",
      submitStatus: null, // 로그인 기능 활성화 수준
    };
  },

  methods: {
    onLogin() {
      this.$v.$touch();
      if (this.$v.$invalid) {
        this.submitStatus = "ERROR";
      } else {
        this.submitStatus = "PENDING";
        this.form = {
          userEmail: this.userEmail,
          userPassword: this.userPassword,
        };
        this.$Axios
          .post(`${this.$store.state.host}/v1/login`, this.form)
          .then((res) => {
            if (res.data.status == "fail") {
              console.log(res.data.status);
              alert("로그인 정보가 없습니다.");
            } else {
              this.$store.commit("LOGIN", res.data.data);
              this.$router.push("/home");
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
  created() {
    if (this.userNickname != null) {
      this.$router.push("/home");
    }
  },
};
</script>
