<template>
<sequential-entrance fromBottom> 
  <div id="ranking">
    <v-app id="inspire">
      <v-container fluid>
        <v-row justify="center" dense align="center">
          <div class="ranking-nav">
            <v-col class="d-flex" cols="3" sm="1">
              <v-select
                v-model="selectedFol"
                :items="onlyFollowing"
                item-text="title"
                item-value="value"
                label="구분"
                return-value
              >
              </v-select>
            </v-col>
            <v-col class="d-flex" cols="2.5" sm="2">
              <v-select
                v-model="selectedCate"
                :items="categoryId"
                item-text="title"
                item-value="value"
                label="카테고리"
                return-value
              >
              </v-select>
            </v-col>
            <div class="r-toggle">
              <v-switch
                v-model="selectedMon"
                inset
                color="red darken-4 primary"
                :ripple="false"
                label="Monthly"
              >
                <template class="r-left-label" #prepend>
                  <v-label>All</v-label>
                </template>
              </v-switch>
            </div>
          </div>
        </v-row>
        <v-divider></v-divider>
        <ranking-list
          :categoryId="selectedCate"
          :monthlyRanking="selectedMon"
          :onlyFollowing="selectedFol"
        ></ranking-list>
        <router-view></router-view>
      </v-container>
    </v-app>
  </div>
  </sequential-entrance> 

</template>

<script>
import RankingList from "./RankingList.vue";
import "../ranking/ranking.css";

export default {
  components: { RankingList },
  data() {
    return {
      selectedCate: 0,
      selectedMon: false,
      selectedFol: false,
      monthlyRanking: false,
      onlyFollowing: [
        {
          title: "전체",
          value: false,
        },
        {
          title: "팔로잉",
          value: true,
        },
      ],
      categoryId: [
        {
          title: "전체",
          value: "0",
        },
        {
          title: "운동",
          value: "21",
        },
        {
          title: "취미",
          value: "24",
        },
        {
          title: "독서",
          value: "27",
        },
        {
          title: "생활습관",
          value: "25",
        },
        {
          title: "돈관리",
          value: "28",
        },
        {
          title: "돌봄",
          value: "23",
        },
        {
          title: "다이어트",
          value: "26",
        },
        {
          title: "감정관리",
          value: "29",
        },
        {
          title: "공부",
          value: "22",
        },
      ],
    };
  },
};
</script>
