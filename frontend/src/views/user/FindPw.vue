<template>
  <div class="find-pw">
    <v-card-text class="text-input">
      <v-text-field
        :error-messages="emailErrors"
        v-model="userEmail"
        required
        label="이메일"
        type="email"
        @input="$v.userEmail.$touch()"
        @blur="$v.userEmail.$touch()"
      >
      </v-text-field>
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
        class="btn-next"
        type="submit"
        @click.prevent="onSubmit"
        :disabled="submitStatus === 'PENDING'"
      >
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
import { required, minLength, email, numeric, maxLength } from "vuelidate/lib/validators";

var countdown;

export default {
  validations: {
    userEmail: { required, email },
    userPhone: { required, numeric, minLength: minLength(11), maxLength: maxLength(11) },
    certificationNumber: { required, numeric },
  },

  data() {
    return {
      userEmail: "",
      userPhone: "",
      certificationNumber: "",
      rqNumber: false, // 연락처 인증요청 여부
      ckrqNum: false, // 인증번호 입력 후 확인버튼 클릭 여부
      submitStatus: null, // 다음버튼 기능 활성화 수준
      certNum: "", // 인증번호 요청 후 sms를 통해 수령한 인증번호
      timerOut: false, // 3분 타이머가 아웃되었는지 확인
      callReset: false, // 3분 타이머가 만료되었을 경우 다시 초기화
      // 비밀번호 변경 시 넘겨 줄 데이터
      userName: "",
      userNickname: "",
      userPassword: "",
      userProfileMessage: "",
    };
  },

  computed: {
    emailErrors() {
      const errors = [];
      if (!this.$v.userEmail.$dirty) return errors;
      !this.$v.userEmail.email && errors.push("이메일 형식이 올바르지 않습니다.");
      !this.$v.userEmail.required && errors.push("필수 입력항목입니다.");
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
    // 비밀번호 요청
    onSubmit() {
      this.$v.$touch();
      if (this.$v.$invalid) {
        this.submitStatus = "ERROR";
      } else if (this.rqNumber == false) {
        this.submitStatus = "ERROR";
        alert("휴대전화번호 인증이 필요합니다!");
      } else if (this.ckrqNum == false) {
        this.submitStatus = "ERROR";
        alert("인증번호 확인이 필요합니다!");
      } else {
        this.submitStatus = "PENDING";
        this.$Axios
          .get(`${this.$store.state.host}/v1/users/` + this.userEmail)
          .then((res) => {
            if (res.data.status == "success") {
              if (this.userPhone == res.data.data.userPhone) {
                (this.userName = res.data.data.userName),
                  (this.userNickname = res.data.data.userNickname),
                  (this.userPassword = res.data.data.userPassword),
                  (this.userProfileMessage = res.data.data.userProfileMessage),
                  this.$router.push({
                    name: "ChangePw",
                    params: {
                      userName: this.userName,
                      userNickname: this.userNickname,
                      userProfileMessage: this.userProfileMessage,
                      userEmail: this.userEmail,
                      userPhone: this.userPhone,
                    },
                  });
                clearInterval(countdown);
              } else {
                alert("회원 정보를 찾을 수가 없습니다.");
              }
            } else {
              alert("회원 정보를 찾을 수가 없습니다.");
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
    this.timerOut = false;
  },
};
</script>
