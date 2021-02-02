<template>
  <div id="calender" class="calender">
    <v-app id="code">
      <div>
        <v-date-picker
          v-model="picker"
          :events="arrayEvents"
          event-color="#a62f22"
          color="#a62f22"
        ></v-date-picker>
      </div>
      <div>
        <kakaoLogin :component="component" />
        <GoogleLogin :component="component" />
      </div>
    </v-app>
  </div>
</template>

<script>
import KakaoLogin from "../user/snsLogin/Kakao.vue";
import GoogleLogin from "../user/snsLogin/Google.vue";
import "../../assets/css/alarm.css";
export default {
  components: {
    KakaoLogin,
    GoogleLogin,
  },
  data: () => ({
    arrayEvents: null,
    date1: new Date().toISOString().substr(0, 10),
    picker: null,
  }),

  mounted() {
    this.arrayEvents = [...Array(999)].map(() => {
      // 이 부분에 해당 날짜의 챌린지가 입력되도록....(+1 해줘야함)
      // ------------------------------------------------
      const day = 15;
      // ------------------------------------------------
      const d = new Date();
      d.setDate(day);
      return d.toISOString().substr(0, 10);
    });
  },
};
</script>
