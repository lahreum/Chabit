<template>
  <div class="outside">
    <div class="logo"></div>
    <img class="chabit-logo" src="../../assets/img/chabit.jpg" alt="chabit" />
    <div class="inside">
      <v-form class="signup" v-on:submit.prevent="onSubmit">
        <p>회원가입</p>
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
            class="input-with-btn"
            :error-messages="emailErrors"
            v-model="userEmail"
            required
            label="이메일"
            type="email"
            @input="$v.userEmail.$touch()"
            @blur="$v.userEmail.$touch()"
          ></v-text-field>
          <button class="btn-get-check " name="checkBtn" @click="checkLog()">
            중복확인
          </button>
          <v-text-field
            :error-messages="passwordErrors"
            v-model="userPassword"
            label="비밀번호"
            type="password"
            required
            @input="$v.userPassword.$touch()"
            @blur="$v.userPassword.$touch()"
          ></v-text-field>
          <v-text-field
            :error-messages="confirmErrors"
            v-model="confirm"
            label="비밀번호 확인"
            type="password"
            required
            @input="$v.confirm.$touch()"
            @blur="$v.confirm.$touch()"
          ></v-text-field>
          <v-text-field
            :error-messages="nickNameErrors"
            class="input-with-btn"
            v-model="userNickname"
            label="닉네임"
            required
            @input="$v.userNickname.$touch()"
            @blur="$v.userNickname.$touch()"
          ></v-text-field
          ><button class="btn-get-check">중복확인</button>
          <v-text-field
            :error-messages="phoneErrors"
            class="input-with-btn"
            v-model="userPhone"
            label="연락처"
            type="tel"
            required
            @input="$v.userPhone.$touch()"
            @blur="$v.userPhone.$touch()"
          ></v-text-field>
          <button class="btn-get-check">인증요청</button>
          <v-text-field
            :error-messages="certificationNumberErrors"
            class="input-with-btn"
            v-model="certificationNumber"
            label="인증번호 입력"
            required
            @input="$v.certificationNumber.$touch()"
            @blur="$v.certificationNumber.$touch()"
          ></v-text-field
          ><button class="btn-get-check">확인</button>
        </v-card-text>
        <div class="bottom">
          <button class="btn-signup" @click="onSubmit">
            가입하기
          </button>
        </div>
      </v-form>
    </div>
    <br />
  </div>
</template>

<script>
import { validationMixin } from "vuelidate";
import { required, minLength, email, sameAs, helpers } from "vuelidate/lib/validators";
const password = helpers.regex("password", /^.*(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&*]).*$/);
const phoneNumber = helpers.regex("phoneNumber", /^[0-9]{11}/);
export default {
  mixins: [validationMixin],
  validations: {
    userName: { required },
    userEmail: { required, email },
    userPassword: { required, minLength: minLength(5), password },
    confirm: { required, sameAsPassword: sameAs("userPassword") },
    userNickname: { required },
    userPhone: { required, phoneNumber },
    certificationNumber: { required },
  },
  data() {
    return {
      userName: "",
      userEmail: "",
      userPassword: "",
      confirm: "",
      userNickname: "",
      userPhone: "",
      certificationNumber: "",
    };
  },
  computed: {
    nameErrors() {
      const errors = [];
      if (!this.$v.userName.$dirty) return errors;
      !this.$v.userName.required && errors.push("필수 입력항목입니다.");
      return errors;
    },
    emailErrors() {
      const errors = [];
      if (!this.$v.userEmail.$dirty) return errors;
      !this.$v.userEmail.email && errors.push("이메일 형식이 올바르지 않습니다.");
      !this.$v.userEmail.required && errors.push("필수 입력항목입니다.");
      return errors;
    },
    passwordErrors() {
      const errors = [];
      if (!this.$v.userPassword.$dirty) return errors;
      !this.$v.userPassword.minLength && errors.push("최소 5글자 이상 입력해주세요");
      !this.$v.userPassword.required && errors.push("필수 입력입력항목입니다.");
      !this.$v.userPassword.password &&
        errors.push("영문, 숫자, 특수문자(!@#$%^&*)를 입력해주세요.");
      return errors;
    },
    confirmErrors() {
      const errors = [];
      if (!this.$v.confirm.$dirty) return errors;
      !this.$v.confirm.required && errors.push("필수 입력입력항목입니다.");
      !this.$v.confirm.sameAsPassword && errors.push("입력한 비밀번호와 일치하지 않습니다.");
      return errors;
    },
    nickNameErrors() {
      const errors = [];
      if (!this.$v.userNickname.$dirty) return errors;
      !this.$v.userNickname.required && errors.push("필수 입력입력항목입니다.");
      return errors;
    },
    phoneErrors() {
      const errors = [];
      if (!this.$v.userPhone.$dirty) return errors;
      !this.$v.userPhone.required && errors.push("필수 입력입력항목입니다.");
      !this.$v.userPhone.phoneNumber && errors.push("숫자(11자리)만 입력 가능합니다.");
      return errors;
    },
    certificationNumberErrors() {
      const errors = [];
      if (!this.$v.certificationNumber.$dirty) return errors;
      !this.$v.certificationNumber.required && errors.push("필수 입력입력항목입니다.");
      return errors;
    },
  },
  methods: {
    checkLog() {
      console.log("hello");
    },
    onSubmit() {
      this.$v.$touch();
      this.$Axios
        .post(`${this.$store.state.host}/v1/users`, this.form)
        .then(({ data }) => {
          if (data == "success") {
            alert("가입이 완료되었습니다.");
            this.$router.push("/login");
          } else if (data == "fail") {
            alert("가입을 할 수 없습니다.");
          }
        })
        .catch((error) => {
          alert("가입 중 오류가 발생하였습니다.");
          console.log(error);
        });
      alert(JSON.stringify(this.form));
    },
  },
};
</script>
