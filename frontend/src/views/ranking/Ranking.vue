<template>
  <div id="app">
    <v-app id="inspire">
      <div>
        <v-row justify="center" class="r-header ma-" dense align="center">
          <div class="r-menu">
            <v-menu bottom offset-y rounded="lg">
              <template v-slot:activator="{ on, attrs }">
                <v-btn v-bind="attrs" v-on="on" text plain>
                  All
                  <v-icon>mdi-menu-down</v-icon>
                </v-btn>
              </template>
              <v-list>
                <v-list-item v-for="(item, i) in folItems" :key="i" @click="() => {}">
                  <v-list-item-title>{{ item.title }} </v-list-item-title>
                </v-list-item>
              </v-list>
            </v-menu>
            <v-menu bottom offset-y rounded="lg">
              <template v-slot:activator="{ on, attrs }">
                <v-btn v-bind="attrs" v-on="on" text plain>
                  Category
                  <v-icon>mdi-menu-down</v-icon>
                </v-btn>
              </template>
              <v-list>
                <v-list-item v-for="(item, i) in cateItems" :key="i" @click="() => {}">
                  <v-list-item-title>{{ item.title }} </v-list-item-title>
                </v-list-item>
              </v-list>
            </v-menu>
          </div>
          <v-btn text :ripple="false" plain>ALL</v-btn>
          <v-switch inset color="red darken-4primary" :ripple="false"></v-switch>
          <v-btn text :ripple="false" plain>MONTHLY</v-btn>
        </v-row>
        <v-divider></v-divider>
        <ranking-list></ranking-list>
        <router-view></router-view>
      </div>
    </v-app>
  </div>
</template>

<script>
import RankingList from "./RankingList.vue";
export default {
  components: { RankingList },
  data() {
    return {
      folItems: [
        {
          title: "All",
        },
        {
          title: "Following",
        },
      ],
      cateItems: [
        {
          title: "운동",
        },
        {
          title: "취미",
        },
        {
          title: "독서",
        },
        {
          title: "생활습관",
        },
        {
          title: "돈관리",
        },
        {
          title: "돌봄",
        },
        {
          title: "다이어트",
        },
        {
          title: "감정관리",
        },
        {
          title: "공부",
        },
      ],
    };
  },
};
</script>
<style scoped>
.r-header .v-btn {
  margin: 0;
  padding: 0;
}

.container {
  max-width: 100%;
  padding: 12px;
  margin-right: 0;
  margin-left: 0;
  min-width: 100%;
}
.row--dense {
  margin: 0;
}
.row {
  margin: 0;
}
</style>
