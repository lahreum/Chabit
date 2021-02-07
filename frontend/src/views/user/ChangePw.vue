<template>
  <div class="outside">
    <div class="logo"></div>
    <img class="chabit-logo" src="../../assets/img/chabit.jpg" alt="chabit" />
    <div class="inside">
      <div class="change-password">
        <p>비밀번호 변경</p>
        <v-card-text class="text-input">
          <v-text-field v-model="form.password" label="비밀번호" type="password"></v-text-field>
          <v-text-field v-model="form.confirm" label="비밀번호 확인" type="password"></v-text-field>
        </v-card-text>
        <div class="bottom">
          <button class="btn-next">
            다음
          </button>
        </div>
      </div>
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
        password: "",
        confirm: "",
      },
    };
  },
};
</script>
