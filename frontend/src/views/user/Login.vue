<template>
  <div class="outside">
    <div class="logo"></div>
    <img class="chabit-logo" src="../../assets/img/chabit.jpg" alt="chabit" />
    <div class="inside">
      <div class="login">
        <p>로그인</p>
        <v-card-text class="text-input">
          <v-text-field v-model="form.email" label="이메일" type="email"></v-text-field>
          <v-text-field v-model="form.password" label="비밀번호" type="password"></v-text-field>
        </v-card-text>
        <div class="bottom">
          <button class="btn-login">
            로그인
          </button>
          <div class="btn-forget-signup">
            <a href="">아이디/비밀번호찾기</a>
            <span> | </span>
            <a href="">회원가입</a>
          </div>
          <div class="btn-social-login">
            <Google />
            <Kakao />
          </div>
        </div>
      </div>
    </div>
    <br />
  </div>
</template>

<script>
import "./user.css";
import Kakao from "../../components/user/snsLogin/Kakao.vue";
import Google from "../../components/user/snsLogin/Google.vue";

export default {
  components: {
    Kakao,
    Google,
  },
  data() {
    return {
      loading: false,
      form: {
        email: "",
        password: "",
      },
    };
  },
};
</script>
