<template>
  <div>
    <!-- Situation 1 : 알림 내역이 없을 때 -->
    <div>
      <v-btn class="ma-2" text icon color="#FF4081 lighten-2">
        <v-icon>mdi-bell-outline</v-icon>
      </v-btn>
    </div>
    <!-- Situation 2 : 알림이 하나 이상 있을 때 -->
    <div>
      <v-btn class="ma-2" text icon color="#a62f22">
        <!-- <v-badge dot overlap color="#a62f22"> -->
        <v-badge dot overlap color="orange">
          <!-- <v-icon>mdi-bell-ring-outline</v-icon> -->
          <v-icon>mdi-bell-outline</v-icon>
        </v-badge>
      </v-btn>
    </div>
  </div>
</template>

<script>
export default {};
</script>
