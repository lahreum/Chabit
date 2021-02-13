<template>
  <div class="outside">
    <div class="logo"></div>
    <img class="chabit-logo" src="../../assets/img/chabit.jpg" alt="chabit" />
    <div class="inside">
      <div class="find-email-finish">
        <h3>이메일 찾기 완료</h3>
        <br />
        <p>
          <strong>{{ $route.params.userName }}</strong
          >님의 가입 이메일은
        </p>
        <p>
          <strong>{{ $route.params.userEmail }}</strong> 입니다.
        </p>
      </div>
      <div class="bottom">
        <button class="btn-ok" @click="$router.push('/login')">
          로그인하기
        </button>
      </div>
    </div>
    <br />
  </div>
</template>

<script>
import "./user.css";

export default {
  props: {
    userName: { type: String, default: "" },
    userEmail: { type: String, default: "" },
  },
};
</script>
