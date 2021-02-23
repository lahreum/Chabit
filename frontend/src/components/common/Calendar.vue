<template>
  <div id="calender" class="calender">
    <v-app id="code" style="height:50px;">
      <div style="margin: 0 auto;">
        <v-date-picker
          v-model="picker"
          :events="arrayEvents"
          event-color="red darken-4"
          color="red darken-4"
        ></v-date-picker>
      </div>
    </v-app>
  </div>
</template>

<script>
import "../../assets/css/alarm.css";
export default {
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
