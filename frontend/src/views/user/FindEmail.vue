<template>
  <div class="find-email">
    <v-card-text class="text-input">
      <v-text-field v-model="form.name" label="이름"></v-text-field>
      <v-text-field
        v-model="form.phoneNumber"
        label="연락처"
        type="tel"
        @input="enforcePhoneFormat()"
      ></v-text-field>
    </v-card-text>
    <div class="bottom">
      <button class="btn-next">
        다음
      </button>
    </div>
  </div>
</template>

<script>
import "./user.css";

export default {
  data() {
    return {
      form: {
        name: "",
        phoneNumber: "",
      },
    };
  },
  methods: {
    enforcePhoneFormat() {
      let x = this.form.phoneNumber.replace(/\D/g, "").match(/(\d{0,3})(\d{0,4})(\d{0,4})/);

      this.form.phoneNumber = !x[2] ? x[1] : "(" + x[1] + ") " + x[2] + (x[3] ? "-" + x[3] : "");
    },
  },
};
</script>

<style></style>
