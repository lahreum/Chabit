<template>
  <div class="outside">
    <div class="logo">
    </div>
    <img class="chabit-logo" src="../../assets/img/chabit.jpg" alt="chabit">
    <div class="inside">
      <div class="login">
        <p>Log In</p>
        <div class="input-with-label">
          <input type="text" placeholder="Email">
        </div>
        <hr>
        <div class="input-with-label">
          <input type="password" placeholder="Password">
        </div>
        <hr>
        <button class="btn-login">
          Log In
        </button>
        <div class="btn-forget-signup">
          <a href="">Forget ID / PW</a>
          <span> | </span>
          <a href="">Sign up</a>
        </div>
        <div class="btn-social-login">
          <Google />
          <Kakao />
        </div>
      </div>
    </div>
    <br>
  </div>
</template>

<script>
import "./user.css"
import Kakao from "../../components/user/snsLogin/Kakao.vue"
import Google from "../../components/user/snsLogin/Google.vue"

export default {
  components: {
    Kakao,
    Google
  }
}
</script>

<style>

</style>