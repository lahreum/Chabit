<template>
  <v-container grid-list-xs>
    <v-layout>
      <v-flex class="section">
        <v-card class="mx-auto rounded-xl" max-width="100%" flat>
          <v-list class="myRank" rounded color="rgb(183,28,28,.1)" style="margin-bottom:10px">
            <v-list-item>
              <v-list-item-avatar class="avatar" size="60">
                <v-img src="../../assets/img/maja.png"></v-img>
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title class="name black--text">Majayong (나)</v-list-item-title>
                <v-list-item-subtitle class="point gray--text">200/5000p</v-list-item-subtitle>
              </v-list-item-content>
              <v-footer color="transparent">
                <v-list-item-title class="name black--text">1,231위</v-list-item-title>
              </v-footer>
            </v-list-item>
          </v-list>
          <v-divider></v-divider>
          <v-autocomplete
            v-model="value"
            :items="users"
            item-value="name"
            item-text="name"
            append-icon="mdi-magnify"
          ></v-autocomplete>
          <v-list>
            <v-list-item v-for="user in users" :key="user.name">
              <v-list-item-avatar class="avatar" size="60">
                <v-img :alt="`${user.name} avatar`" :src="user.avatar"></v-img>
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title class="name black--text" v-text="user.name"></v-list-item-title>
                <v-list-item-subtitle
                  class="point gray--text"
                  v-text="user.point"
                ></v-list-item-subtitle>
              </v-list-item-content>
              <v-icon v-if="user.rank == 1" color="yellow darken-2">mdi-crown</v-icon>
              <v-icon v-if="user.rank == 2" color="grey lighten-2">mdi-crown</v-icon>
              <v-icon v-if="user.rank == 3" color="brown lighten-2">mdi-crown</v-icon>
              <v-footer color="transparent">
                <v-list-item class="name black--text" v-text="user.rank + `위`"></v-list-item>
              </v-footer>
            </v-list-item>
          </v-list>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    value: null,
    users: [
      {
        avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
        name: "walkinglife",
        point: "10,892,567/-",
        rank: "1",
      },
      {
        avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg",
        name: "workaholic",
        point: "9,132,422/-",
        rank: "2",
      },
      {
        avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg",
        name: "ILoveChicken",
        point: "5,323,422/-",
        rank: "3",
      },
      {
        avatar: "https://cdn.vuetifyjs.com/images/lists/4.jpg",
        name: "LoveEating",
        point: "4,573,422/-",
        rank: "4",
      },
      {
        avatar: "https://cdn.vuetifyjs.com/images/lists/5.jpg",
        name: "goodFriend",
        point: "3,489,422/-",
        rank: "5",
      },
      {
        avatar: "https://cdn.vuetifyjs.com/images/lists/4.jpg",
        name: "goodFriend2",
        point: "2,456,422/-",
        rank: "6",
      },
      {
        avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
        name: "saljjingae",
        point: "1,948,422/-",
        rank: "7",
      },
      {
        avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg",
        name: "ssafy4th",
        point: "323,422/-",
        rank: "8",
      },
      {
        avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg",
        name: "hello512",
        point: "23,422/-",
        rank: "9",
      },
      {
        avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg",
        name: "mynameis",
        point: "3,422/-",
        rank: "10",
      },
    ],
  }),
};
</script>

<style scoped>
.avatar {
  border: white solid 1px;
}
.myRank {
  position: sticky;
}
</style>
