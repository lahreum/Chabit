<template>
  <div class="outside">
    <div class="logo"></div>
    <img class="chabit-logo" src="../../assets/img/chabit.jpg" alt="chabit" />
    <div class="inside">
      <v-form class="signup" v-on:submit.prevent="onSubmit">
        <p>회원가입</p>
        <v-card-text class="text-input">
          <v-text-field v-model="form.userName" label="이름"></v-text-field>
          <div class="input-with-btn">
            <v-text-field v-model="form.userEmail" label="이메일" type="email"></v-text-field>
            <button class="btn-get-check">중복확인</button>
          </div>
          <v-text-field v-model="form.userPassword" label="비밀번호" type="password"></v-text-field>
          <!-- <v-text-field v-model="form.confirm" label="비밀번호 확인" type="password"></v-text-field> -->
          <div class="input-with-btn">
            <v-text-field v-model="form.userNickName" label="닉네임"></v-text-field
            ><button class="btn-get-check">중복확인</button>
          </div>
          <div class="input-with-btn">
            <v-text-field
              v-model="form.userPhone"
              label="연락처"
              type="tel"
              @input="enforcePhoneFormat()"
            ></v-text-field>
            <button class="btn-get-check">인증요청</button>
          </div>
          <div class="input-with-btn">
            <v-text-field label="인증번호 입력"></v-text-field
            ><button class="btn-get-check">확인</button>
          </div>
        </v-card-text>
        <div class="bottom">
          <button class="btn-signup" type="submit" @click="onSubmit">
            가입하기
          </button>
        </div>
      </v-form>
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
        userName: "",
        userEmail: "",
        userPassword: "",
        // confirm: "",
        userNickName: "",
        userPhone: "",
        // certificationNumber: "",
      },
    };
  },
  methods: {
    enforcePhoneFormat() {
      let x = this.form.userPhone.replace(/\D/g, "").match(/(\d{0,3})(\d{0,4})(\d{0,4})/);

      this.form.userPhone = !x[2] ? x[1] : "(" + x[1] + ") " + x[2] + (x[3] ? "-" + x[3] : "");
    },
    onSubmit() {
      this.$Axios
      .post(`${this.$store.state.host}/v1/users`, this.form)
      .then(
        ({data}) => {
        if(data == 'success'){
          alert('가입이 완료되었습니다.');
        } else if(data == 'fail') {
          alert('가입을 할 수 없습니다.');
        }
        this.$router.push("/");
      })
      .catch((error) => {
        alert('가입 중 오류가 발생하였습니다.');
        console.log(error);
      });
      alert(JSON.stringify(this.form));
    }
  },
};
</script>
