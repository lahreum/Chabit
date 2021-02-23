<template>
  <div id="loading">
    <v-app id="inspire">
      <v-row justify="center">
        <v-overlay :z-index="zIndex" :value="overlay">
          <div class="text-center">
            <v-progress-circular :width="3" color="#a62f22" indeterminate></v-progress-circular>
          </div>
        </v-overlay>
      </v-row>
    </v-app>
  </div>
</template>

<script>
export default {
  data() {
    return {
      overlay: true,
      // zIndex 설정(배치 순서)
      zIndex: 0,
    };
  },
  mounted() {},
};
</script>
