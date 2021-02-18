<template>
  <div class="outside">
    <div class="logo"></div>
    <img class="chabit-logo" src="../../assets/img/chabit.jpg" alt="chabit" />
    <div class="inside">
      <v-form class="signup">
        <p>회원가입</p>
        <v-card-text class="text-input">
          <v-text-field
            :error-messages="nameErrors"
            v-model="userName"
            label="이름"
            required
            @input="$v.userName.$touch()"
            @blur="$v.userName.$touch()"
          >
          </v-text-field>
          <v-text-field
            class="input-with-btn"
            :error-messages="emailErrors"
            v-model="userEmail"
            required
            id="btnCkEmail"
            label="이메일"
            type="email"
            @input="$v.userEmail.$touch()"
            @blur="$v.userEmail.$touch()"
          >
          </v-text-field>
          <button class="btn-get-check " name="checkBtn" type="button" @click="checkEmail">
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
          <v-text-field
            :error-messages="nickNameErrors"
            class="input-with-btn"
            v-model="userNickname"
            label="닉네임"
            id="btnCkNN"
            required
            @input="$v.userNickname.$touch()"
            @blur="$v.userNickname.$touch()"
          >
          </v-text-field>
          <button class="btn-get-check" type="button" @click="checkNickname">
            중복확인
          </button>
          <v-text-field
            :error-messages="phoneErrors"
            class="input-with-btn"
            v-model="userPhone"
            label="연락처"
            id="btnCkPN"
            type="tel"
            required
            @input="$v.userPhone.$touch()"
            @blur="$v.userPhone.$touch()"
          ></v-text-field>
          <button class="btn-get-check" type="button" @click="requestVali">인증요청</button>
          <v-text-field
            :error-messages="certificationNumberErrors"
            class="input-with-btn"
            v-model="certificationNumber"
            id="btnCkCN"
            label="인증번호 입력"
            required
            @input="$v.certificationNumber.$touch()"
            @blur="$v.certificationNumber.$touch()"
          ></v-text-field
          ><button class="btn-get-check" type="button" @click="checkNum">확인</button>
          <!-- 타이머 -->
          <div class="display-timer">
            <p class="display__time-left"></p>
          </div>
          <!-- 타이머 -->
        </v-card-text>
        <div class="bottom">
          <button
            class="btn-signup"
            type="submit"
            @click.prevent="onSubmit"
            :disabled="submitStatus === 'PENDING'"
          >
            가입하기
          </button>
        </div>
      </v-form>
    </div>
    <br />
  </div>
</template>

<script>
import {
  required,
  minLength,
  email,
  sameAs,
  helpers,
  numeric,
  maxLength,
} from "vuelidate/lib/validators";
import { mapGetters } from "vuex";

var countdown;
const password = helpers.regex("password", /^.*(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&*]).*$/);

export default {
  validations: {
    userName: { required },
    userEmail: { required, email },
    userPassword: { required, minLength: minLength(5), password },
    confirm: { required, sameAsPassword: sameAs("userPassword") },
    userNickname: { required },
    userPhone: { required, numeric, minLength: minLength(11), maxLength: maxLength(11) },
    certificationNumber: { required, numeric },
  },
  data() {
    return {
      userName: "",
      userEmail: "",
      userPassword: "",
      userNickname: "",
      userPhone: "",
      confirm: "", // 비밀번호 확인
      certificationNumber: "", // 인증번호
      ckEmail: false, // 이메일 중복확인 여부
      ckNickname: false, // 닉네임 중복확인 여부
      rqNumber: false, // 연락처 인증요청 여부
      ckrqNum: false, // 인증번호 입력 후 확인버튼 클릭 여부
      form: "",
      submitStatus: null, // 가입하기 기능 활성화 수준
      certNum: "", // 인증번호 요청 후 sms를 통해 수령한 인증번호
      timerOut: false, // 3분 타이머가 아웃되었는지 확인
      callReset: false, // 3분 타이머가 만료되었을 경우 다시 초기화
    };
  },
  computed: {
    ...mapGetters({ userRole: "getUserRole" }),

    // 에러체크
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
      !this.$v.userPhone.numeric && errors.push("숫자(11자리)만 입력 가능합니다.");
      !this.$v.userPhone.minLength && errors.push("숫자(11자리)만 입력 가능합니다.");
      !this.$v.userPhone.maxLength && errors.push("숫자(11자리)만 입력 가능합니다.");
      return errors;
    },
    certificationNumberErrors() {
      const errors = [];
      if (!this.$v.certificationNumber.$dirty) return errors;
      !this.$v.certificationNumber.required && errors.push("필수 입력입력항목입니다.");
      !this.$v.certificationNumber.numeric && errors.push("숫자만 입력 가능합니다.");
      return errors;
    },
  },
  methods: {
    // 타이머 설정
    timer(seconds) {
      clearInterval(countdown);
      const now = Date.now();
      const then = now + seconds * 1000;
      this.displayTimeLeft(seconds);
      countdown = setInterval(() => {
        const secondsLeft = Math.round((then - Date.now()) / 1000);
        if (secondsLeft < 0) {
          clearInterval(countdown);
          return;
        }
        this.displayTimeLeft(secondsLeft);
      }, 1000);
    },

    // 남은 시간 계산
    displayTimeLeft(seconds) {
      const timerDisplay = document.querySelector(".display__time-left");
      const minutes = Math.floor(seconds / 60);
      const remainderSeconds = seconds % 60;
      const display = `${minutes}:${remainderSeconds < 10 ? "0" : ""}${remainderSeconds}`;
      if (remainderSeconds <= 0) this.timerOut = true;
      if (this.callReset) {
        this.timerOut = false;
        this.callReset = false;
      }
      timerDisplay.textContent = display;
    },

    // 이메일 중복체크
    checkEmail() {
      if (this.userEmail.length > 0 && !this.ckEmail) {
        this.$Axios
          .get(`${this.$store.state.host}/v1/users/` + this.userEmail)
          .then((res) => {
            if (res.data.status == "success") {
              alert("중복된 이메일이 존재합니다. 다른 이메일을 입력해주세요.");
            } else {
              alert("사용 가능한 이메일입니다.");
              this.ckEmail = true;
              document.getElementById("btnCkEmail").disabled = true;
            }
          })
          .catch((err) => {
            console.log(err);
          });
      } else if (this.ckEmail) {
        alert("이미 중복확인을 하였습니다.");
      } else {
        alert("이메일을 입력해주세요!");
      }
    },

    // 닉네임 중복체크
    checkNickname() {
      if (this.userNickname.length != 0 && !this.ckNickname) {
        this.$Axios
          .get(`${this.$store.state.host}/v1/users/` + this.userNickname)
          .then((res) => {
            if (res.data.status == "success") {
              alert("중복된 닉네임이 존재합니다. 다른 닉네임을 입력해주세요.");
            } else {
              alert("사용 가능한 닉네임입니다.");
              this.ckNickname = true;
              document.getElementById("btnCkNN").disabled = true;
            }
          })
          .catch((err) => {
            console.log(err);
          });
      } else if (this.ckNickname) {
        alert("이미 중복확인을 하였습니다.");
      } else {
        alert("닉네임을 입력해주세요!");
      }
    },

    // 인증번호 요청
    requestVali() {
      if (this.userPhone.length == 11 && !this.rqNumber) {
        this.$Axios
          .get(`${this.$store.state.host}/v1/auth/sms?phone=` + this.userPhone)
          .then((res) => {
            if (res.data.status == "success") {
              alert("인증번호가 전송되었습니다.");
              this.timer(180);
              this.rqNumber = true;
              this.timerOut = false;
              this.certNum = res.data.data.certificateNum;
              document.getElementById("btnCkPN").disabled = true;
            } else {
              console.log(res);
            }
          })
          .catch((err) => {
            console.log(err);
          });
      } else if (this.rqNumber) {
        alert("이미 전송되었습니다. 3분 후 다시 시도해주세요.");
      } else {
        alert("올바른 연락처를 입력해주세요!");
      }
    },

    // 인증번호 확인
    checkNum() {
      if (this.timerOut) {
        alert("인증번호 입력시간이 만료되었습니다. 다시 인증번호 요청을 해주세요.");
        this.rqNumber = false;
        this.callReset = true;
      } else {
        if (this.certificationNumber.length > 0 && !this.ckrqNum) {
          if (this.certificationNumber == this.certNum) {
            alert("인증번호가 일치합니다.");
            this.ckrqNum = true;
            document.getElementById("btnCkCN").disabled = true;
          } else {
            alert("인증번호가 일치하지 않습니다. 다시 입력해주세요");
          }
        } else if (this.ckrqNum) {
          alert("이미 인증번호 확인이 완료되었습니다.");
        } else {
          alert("인증번호를 입력해주세요!");
        }
      }
    },

    // 로그인여부 확인
    checkLogin() {
      if (this.userRole != null) this.$router.push("/home");
    },

    // 회원가입 요청
    onSubmit() {
      this.$v.$touch();
      if (this.$v.$invalid) {
        this.submitStatus = "ERROR";
      } else if (this.ckEmail == false) {
        this.submitStatus = "ERROR";
        alert("이메일 중복 확인을 해주세요!");
      } else if (this.ckNickname == false) {
        this.submitStatus = "ERROR";
        alert("닉네임 중복 확인을 해주세요!");
      } else if (this.rqNumber == false) {
        this.submitStatus = "ERROR";
        alert("휴대전화번호 인증이 필요합니다!");
      } else if (this.ckrqNum == false) {
        this.submitStatus = "ERROR";
        alert("인증번호 확인이 필요합니다!");
      } else {
        this.submitStatus = "PENDING";
        this.form = {
          userName: this.userName,
          userEmail: this.userEmail,
          userPassword: this.userPassword,
          userNickname: this.userNickname,
          userPhone: this.userPhone,
          userProfileMessage: "",
        };
        this.$Axios
          .post(`${this.$store.state.host}/v1/users/`, this.form)
          .then((res) => {
            if (res.data.data.joinResult == "success") {
              clearInterval(countdown);
              alert("가입이 완료되었습니다.");
              this.$router.push("/instruction");
            } else {
              console.log(res);
              alert("가입을 할 수 없습니다.");
            }
          })
          .catch((err) => {
            alert("가입 중 오류가 발생하였습니다.");
            console.log(err);
          });
        setTimeout(() => {
          this.submitStatus = "OK";
        }, 500);
      }
    },
  },
  created() {
    this.checkLogin();
    this.timerOut = false;
  },
};
</script>
