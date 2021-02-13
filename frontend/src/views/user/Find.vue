<template>
  <div class="outside">
    <div class="logo"></div>
    <img class="chabit-logo" src="../../assets/img/chabit.jpg" alt="chabit" />
    <div class="inside">
      <div class="email-password-button b2" id="button-10">
        <input type="checkbox" class="checkbox" @click="checkForm" />
        <div class="knobs">
          <span>이메일</span>
        </div>
        <div class="layer"></div>
      </div>
      <FindEmail v-if="!isTrue" />
      <FindPw v-else />
    </div>
    <br />
  </div>
</template>

<script>
import "./user.css";
import FindEmail from "../../views/user/FindEmail.vue";
import FindPw from "../../views/user/FindPw.vue";
import { mapGetters } from "vuex";

export default {
  components: {
    FindEmail,
    FindPw,
  },
  data() {
    return {
      isTrue: false,
    };
  },
  computed: {
    ...mapGetters({ userRole: "getUserRole" }),
  },
  methods: {
    // 이메일-비밀번호 스위치
    checkForm(event) {
      if (event.target.checked === false) {
        this.isTrue = false;
      } else {
        this.isTrue = true;
      }
    },
    // 로그인여부 확인
    checkLogin() {
      if (this.userRole != null) this.$router.push("/");
    },
  },
  created() {
    this.checkLogin();
  },
};
</script>
