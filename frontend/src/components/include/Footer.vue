<template>
  <div id="app">
    <v-app id="inspire">
      <v-footer color="red darken-4 lighten-1" padless fixed>
        <v-row justify="center" no-gutters>
          <!-- 링크 넣을 경우 살리기 -->
          <v-btn v-for="link in links" :key="link" color="white" text rounded class=" text-caption">
            {{ link }}
          </v-btn>
          <v-col class="red darken-3 lighten-2 py-4 white--text" cols="12">
            <div class="text-caption">
              COPYRIGHTⒸ 2021 살짝찐텐인개발자들. ALL RIGHT RESERVED. <br />
              대전광역시 유성구 학하동<br />
              TEL : 042-1234-5678 | E-mail : help@saljg.com
            </div>
          </v-col>
        </v-row>
      </v-footer>
    </v-app>
  </div>
</template>

<script>
export default {
  data: () => ({
    links: ["Home", "About Us", "Team"],
  }),
};
</script>
