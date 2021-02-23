<template>
<sequential-entrance fromBottom>
  <div class="outside">
    <div class="logo"></div>
    <img class="chabit-logo" src="../../assets/img/chabit.jpg" alt="chabit" />
    <div class="inside">
      <div class="modify-user">
        <p>회원정보 변경</p>
        <v-card-text class="text-input">
          <v-flex class="mb-4 text-center">
            <div v-if="!profileHasClicked" @click="profileChange">
              <v-avatar size="128" color="grey" class="origin-avatar">
                <img :src="userImage" />
              </v-avatar>
            </div>

            <div v-else>
              <image-input v-model="avatar">
                <div slot="activator">
                  <v-avatar
                    size="128px"
                    v-ripple
                    v-if="!avatar"
                    class="grey lighten-3 mb-3"
                  >
                    <span>Click</span>
                  </v-avatar>
                  <v-avatar size="128px" v-ripple v-else class="mb-3">
                    <img :src="avatar.imageURL" />
                  </v-avatar>
                </div>
              </image-input>
              <v-slide-x-transition>
                <div v-if="avatar && saved == false">
                  <v-btn class="primary" @click="uploadImage" :loading="saving"
                    >저장</v-btn
                  >
                </div>
              </v-slide-x-transition>
            </div>
          </v-flex>
          <v-text-field disabled v-model="userName" label="이름"></v-text-field>
          <v-text-field
            disabled
            v-model="userEmail"
            label="이메일"
            type="email"
          ></v-text-field>
          <v-text-field
            :error-messages="passwordErrors"
            v-model="userPassword"
            label="비밀번호"
            type="password"
            @change="changedPassword"
            required
          >
          </v-text-field>
          <v-text-field
            :error-messages="confirmErrors"
            v-model="confirm"
            label="비밀번호 확인"
            type="password"
            @change="finishConfirm"
          >
          </v-text-field>
          <v-text-field
            :error-messages="nickNameErrors"
            class="input-with-btn"
            v-model="userNickname"
            label="닉네임"
            id="btnCkNN"
            required
            @change="changedNickname"
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
            @change="changedPhone"
          ></v-text-field>
          <button class="btn-get-check" type="button" @click="requestVali">
            인증요청
          </button>
          <v-text-field
            :error-messages="certificationNumberErrors"
            class="input-with-btn"
            v-model="certificationNumber"
            id="btnCkCN"
            label="인증번호 입력"
            @change="changedCertNum"
          ></v-text-field
          ><button class="btn-get-check" type="button" @click="checkNum">
            확인
          </button>
          <!-- 타이머 -->
          <div class="display-timer">
            <p class="display__time-left"></p>
          </div>
          <!-- 타이머 -->
        </v-card-text>
        <v-card-actions class="bottom">
          <v-spacer></v-spacer>
          <div @click="moveToPrevious">
            <btn-small text="취소" class="btn-left" type="button"></btn-small>
          </div>
          <v-spacer></v-spacer>
          <div @click="onSubmit">
            <btn-small text="저장" type="submit"></btn-small>
          </div>
          <v-spacer></v-spacer>
        </v-card-actions>
      </div>
    </div>
  </div>
  </sequential-entrance>
</template>

<script>
import BtnSmall from "../../components/common/BtnSmall.vue";
import "../user/user.css";
import { mapGetters } from "vuex";
import {
  required,
  minLength,
  sameAs,
  helpers,
  numeric,
  maxLength,
} from "vuelidate/lib/validators";
import ImageInput from "../../components/common/ImageInput.vue";

var countdown;
const password = helpers.regex(
  "password",
  /^.*(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&*]).*$/
);

export default {
  validations: {
    userPassword: { required, minLength: minLength(5), password },
    confirm: { sameAsPassword: sameAs("userPassword") },
    userNickname: { required },
    userPhone: {
      required,
      numeric,
      minLength: minLength(11),
      maxLength: maxLength(11),
    },
    certificationNumber: { numeric },
  },

  components: { BtnSmall, ImageInput: ImageInput },

  data() {
    return {
      userImage: "",
      userName: "",
      userPassword: "",
      userNickname: "",
      userPhone: "",
      userProfileMessage: "",
      form: [],
      confirm: "",
      certificationNumber: "",
      ckPassword: true, // 비밀번호 변경되었을 경우 확인
      ckNickname: true, // 닉네임 중복확인 여부
      rqNumber: true, // 연락처 인증요청 여부
      ckrqNum: true, // 인증번호 입력 후 확인버튼 클릭 여부
      certNum: "", // 인증번호 요청 후 sms를 통해 수령한 인증번호
      timerOut: false, // 3분 타이머가 아웃되었는지 확인
      callReset: false, // 3분 타이머가 만료되었을 경우 다시 초기화
      passwordHasChanged: false,
      profileHasClicked: false,
      avatar: null,
      saving: false,
      saved: false,
    };
  },
  watch: {
    avatar: {
      handler: function (val) {
        this.saved = false;
        this.userFiles = val.imageFile;
      },
      deep: true,
    },
  },
  computed: {
    ...mapGetters({ userEmail: "getUserEmail" }),

    passwordErrors() {
      const errors = [];
      if (!this.$v.userPassword.$dirty) return errors;
      !this.$v.userPassword.minLength &&
        errors.push(
          "영문, 숫자, 특수문자(!@#$%^&*)로 5글자 이상 입력해주세요."
        );
      !this.$v.userPassword.required && errors.push("필수 입력입력항목입니다.");
      !this.$v.userPassword.password &&
        errors.push(
          "영문, 숫자, 특수문자(!@#$%^&*)로 5글자 이상 입력해주세요."
        );
      return errors;
    },
    confirmErrors() {
      const errors = [];
      if (!this.$v.confirm.$dirty) return errors;
      !this.$v.confirm.sameAsPassword &&
        errors.push("입력한 비밀번호와 일치하지 않습니다.");
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
      !this.$v.userPhone.numeric &&
        errors.push("숫자(11자리)만 입력 가능합니다.");
      !this.$v.userPhone.minLength &&
        errors.push("숫자(11자리)만 입력 가능합니다.");
      !this.$v.userPhone.maxLength &&
        errors.push("숫자(11자리)만 입력 가능합니다.");
      return errors;
    },
    certificationNumberErrors() {
      const errors = [];
      if (!this.$v.certificationNumber.$dirty) return errors;
      !this.$v.certificationNumber.numeric &&
        errors.push("숫자만 입력 가능합니다.");
      return errors;
    },
  },

  methods: {
    // 변경 확인
    finishConfirm() {
      this.ckPassword = true;
    },
    changedPassword(val) {
      this.userPassword = val;
      this.ckPassword = false;
    },
    changedNickname(val) {
      this.userNickname = val;
      this.ckNickname = false;
    },
    changedPhone(val) {
      this.userPhone = val;
      this.rqNumber = false;
    },
    changedCertNum(val) {
      this.certificateNum = val;
      this.ckrqNum = false;
    },
    profileChange() {
      this.profileHasClicked = true;
    },

    // 아바타 업로드
    uploadImage() {
      let fd = new FormData();
      fd.append("userImage", this.userFiles);
      this.userImage = fd;
      this.$Axios
        .post(
          `${this.$store.state.host}/v1/users/` + this.userEmail + `/image`,
          this.userImage,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
        )
        .then((res) => {
          if (res.data.status == "success") {
            console.log(res.data.data);
            this.$store.commit("UPDATEUSERAVATAR", res.data.data);
            alert("사진이 등록되었습니다.");
            this.saving = true;
            setTimeout(() => this.savedAvatar(), 500);
          } else {
            alert("사진 등록에 실패하였습니다. " + res.data.data);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    savedAvatar() {
      this.saving = false;
      this.saved = true;
    },

    // 회원정보 로드
    loadInfo() {
      this.$Axios
        .get(`${this.$store.state.host}/v1/users/` + this.userEmail)
        .then((res) => {
          if (res.data.status == "success") {
            this.userImage = res.data.data.userImage;
            this.userName = res.data.data.userName;
            this.userPassword = res.data.data.userPassword;
            this.userNickname = res.data.data.userNickname;
            this.userPhone = res.data.data.userPhone;
            this.userProfileMessage = res.data.data.userProfileMessage;
          } else {
            console.log(res);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 이전 페이지로 이동
    moveToPrevious() {
      this.$router.go(-1);
    },

    // 유저 정보 변경 요청
    onSubmit() {
      if (this.ckNickname == false) {
        alert("닉네임 중복 확인을 해주세요!");
      } else if (this.ckPassword == false) {
        alert("비밀번호 확인이 필요합니다!");
      } else if (this.rqNumber == false) {
        alert("휴대전화번호 인증이 필요합니다!");
      } else if (this.ckrqNum == false) {
        alert("인증번호 확인이 필요합니다!");
      } else {
        this.form = {
          userName: this.userName,
          userEmail: this.userEmail,
          userPassword: this.userPassword,
          userNickname: this.userNickname,
          userPhone: this.userPhone,
          userProfileMessage: this.userProfileMessage,
        };
        this.$Axios
          .put(
            `${this.$store.state.host}/v1/users/` + this.userEmail,
            this.form
          )
          .then((res) => {
            if (res.data.status == "success") {
              clearInterval(countdown);
              this.$store.commit("UPDATEUSERINFO", this.form);
              alert("회원정보 변경이 완료되었습니다.");
              this.$router.push("/");
            } else {
              console.log(res);
              alert("회원정보 변경에 실패하였습니다.");
            }
          })
          .catch((err) => {
            alert("회원정보 변경 중 오류가 발생하였습니다.");
            console.log(err);
          });
      }
    },

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
      const display = `${minutes}:${
        remainderSeconds < 10 ? "0" : ""
      }${remainderSeconds}`;
      if (remainderSeconds <= 0) this.timerOut = true;
      if (this.callReset) {
        this.timerOut = false;
        this.callReset = false;
      }
      timerDisplay.textContent = display;
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
        alert("사용 가능합니다!");
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
              this.rqNumberHasClicked = true;
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
        alert("사용가능합니다!");
      } else {
        alert("올바른 연락처를 입력해주세요!");
      }
    },

    // 인증번호 확인
    checkNum() {
      if (this.timerOut) {
        alert(
          "인증번호 입력시간이 만료되었습니다. 다시 인증번호 요청을 해주세요."
        );
        this.rqNumber = false;
        this.callReset = true;
      } else {
        if (this.certificationNumber.length > 0 && !this.ckrqNum) {
          if (this.certificationNumber == this.certNum) {
            alert("인증번호가 일치합니다.");
            this.ckrqNum = true;
            this.rqNumberHasClicked = false;
            document.getElementById("btnCkCN").disabled = true;
          } else {
            alert("인증번호가 일치하지 않습니다. 다시 입력해주세요");
          }
        } else if (this.ckrqNum) {
          alert("인증번호 확인이 완료되었습니다!");
        } else {
          alert("인증번호를 입력해주세요!");
        }
      }
    },
  },

  created() {
    this.timerOut = false;
    this.loadInfo();
  },
};
</script>
