<template>
  <div class="outside">
    <div class="logo"></div>
    <img class="chabit-logo" src="../../assets/img/chabit.jpg" alt="chabit" />
    <div class="inside">
      <div class="login">
        <p>로그인</p>
        <v-card-text class="text-input">
          <v-text-field v-model="form.userEmail" label="이메일" type="email" @keyup.enter="onLogin"></v-text-field>
          <v-text-field v-model="form.userPassword" label="비밀번호" type="password" @keyup.enter="onLogin"></v-text-field>
        </v-card-text>
        <div class="bottom">
          <button class="btn-login" @click="onLogin">
            로그인
          </button>
          <div class="btn-forget-signup">
            <a href="/find">아이디/비밀번호찾기</a>
            <span> | </span>
            <a href="/join">회원가입</a>
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
        userEmail: "",
        userPassword: "",
      },
    };
  },
  // created() {
  //   this.$store.commit('LOGOUT');
  // },
  methods: {
    onLogin() {
      this.$Axios.post(`${this.$store.state.host}/v1/login`, this.form)
      .then(
        res=> {
          console.log(res);
          if (res.data.status == "fail") {
            console.log(res.data.status)
            alert("로그인 정보가 없습니다.")
          } else {
            console.log('데이터는 잘 넘어옴~ ');
            this.$store.commit('LOGIN', res.data.data);
            this.$router.push("/");
          }
        }
      )
      .catch(
        err => {
          console.log(err);
        })
    }
  }
};
</script>
