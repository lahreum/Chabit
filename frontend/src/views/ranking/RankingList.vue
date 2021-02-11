<template>
  <v-container grid-list-xs>
    <v-layout>
      <v-flex class="section">
        <v-card class="mx-auto rounded-xl" max-width="100%" flat>
          <!-- <div v-if="myEmail != null"> -->
          <v-list class="myRank" rounded color="rgb(183,28,28,.1)">
            <v-list-item>
              <v-list-item-avatar class="avatar" size="60">
                <v-img :src="this.users[this.mine - 1].userImage"></v-img>
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title class="black--text"
                  >{{ myNickname }} <strong>(나)</strong></v-list-item-title
                >
                <v-list-item-subtitle class="point gray--text">{{
                  this.users[this.mine - 1].userPoints
                }}</v-list-item-subtitle>
              </v-list-item-content>
              <v-footer color="transparent">
                <v-list-item-title class="black--text"
                  ><strong>{{ this.mine }}</strong> 위</v-list-item-title
                >
              </v-footer>
            </v-list-item>
          </v-list>
          <!-- </div> -->
          <!-- <div v-else>
            <v-list class="myRank" rounded color="rgb(183,28,28,.1)">
              <v-list-item>
                <v-list-item-title>데이터가 존재하지 않습니다.</v-list-item-title>
              </v-list-item>
            </v-list>
          </div> -->
          <v-divider></v-divider>
          <div class="searchBox">
            <v-autocomplete
              v-model="search"
              :items="users"
              item-value="userNickname"
              item-text="userNickname"
              append-icon="mdi-magnify"
              clearable
              label="검색"
              return-object
              :menu-props="{ top: false, bottom: true, offsetY: true, offsetX: true }"
            >
            </v-autocomplete>
          </div>
          <v-expand-transition>
            <v-list v-if="search" rounded color="rgb(100, 100, 100,.1)">
              <v-list-item :items="users" item-key="search" show-select sort :search="search">
                <v-list-item-avatar class="avatar" size="60">
                  <v-img :alt="`${search.userNickname} avatar`" :src="search.userImage"></v-img>
                </v-list-item-avatar>
                <v-list-item-content>
                  <v-list-item-title class="black--text" v-text="search.userNickname">
                  </v-list-item-title>
                  <v-list-item-subtitle class="point gray--text" v-text="search.userPoints">
                  </v-list-item-subtitle>
                </v-list-item-content>
                <v-icon
                  v-if="users.findIndex((i) => i.userNickname == search.userNickname) == 0"
                  color="yellow darken-2"
                  >mdi-crown</v-icon
                >
                <v-icon
                  v-if="users.findIndex((i) => i.userNickname == search.userNickname) == 1"
                  color="grey lighten-2"
                  >mdi-crown</v-icon
                >
                <v-icon
                  v-if="users.findIndex((i) => i.userNickname == search.userNickname) == 2"
                  color="brown lighten-2"
                  >mdi-crown</v-icon
                >
                <v-footer color="transparent">
                  <v-list-item
                    class="black--text"
                    v-text="
                      users.findIndex((i) => i.userNickname == search.userNickname) + 1 + `위`
                    "
                  ></v-list-item>
                </v-footer>
              </v-list-item>
            </v-list>
          </v-expand-transition>
          <!-- <div v-if="checkListLength() == false"> -->
          <v-list>
            <v-list-item v-for="(user, index) in users" :key="index">
              <v-list-item-avatar class="avatar" size="60">
                <v-img :src="user.userImage"></v-img>
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title class="black--text">{{ user.userNickname }}</v-list-item-title>
                <v-list-item-subtitle class="point gray--text"
                  >{{ user.userPoints }}
                </v-list-item-subtitle>
              </v-list-item-content>
              <v-icon v-if="index == 0" color="yellow darken-2">mdi-crown</v-icon>
              <v-icon v-if="index == 1" color="grey lighten-2">mdi-crown</v-icon>
              <v-icon v-if="index == 2" color="brown lighten-2">mdi-crown</v-icon>
              <v-footer color="transparent">
                <v-list-item
                  class="black--text"
                  v-text="users.findIndex((i) => i.userNickname == user.userNickname) + 1 + `위`"
                ></v-list-item>
              </v-footer>
            </v-list-item>
          </v-list>
          <!-- </div> -->
          <!-- <div v-else>
            야 왜 안떠 이유가 뭔데
          </div> -->
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  props: {
    monthlyRanking: Boolean,
    categoryId: Number,
    onlyFollowing: Boolean,
  },

  data: () => ({
    userPoints: 0,
    search: null,
    users: [],
    mine: [],
  }),
  computed: {
    ...mapGetters({ myEmail: "getUserEmail" }),
    ...mapGetters({ myNickname: "getUserNickname" }),
  },
  methods: {
    // checkListLength() {
    //   if (this.users.length == 0) return true;
    //   else return false;
    // },
    getRankList() {
      if (this.myEmail != null) {
        this.$Axios
          .get(
            `${this.$store.state.host}/v1/users/ranking/` +
              this.myEmail +
              `?categoryId=` +
              this.categoryId +
              `&monthlyRanking=` +
              this.monthlyRanking +
              `&onlyFollowing=` +
              this.onlyFollowing
          )
          .then((res) => {
            if (res.data.status == "success") {
              this.mine = res.data.data.myRanking;
              this.users = res.data.data.userRanking;
            } else {
              console.log(res);
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
  },
  created() {
    this.getRankList();
  },
  watch: {
    monthlyRanking: function() {
      this.getRankList();
      console.log("월별");
    },
    categoryId: function() {
      this.getRankList();
      console.log("카테");
    },
    onlyFollowing: function() {
      this.getRankList();
      console.log("팔로");
    },
  },
};
</script>

<style scoped>
.avatar {
  border: white solid 1px;
}
.myRank {
  position: sticky;
  margin-bottom: 10px;
}
.v-input {
  max-width: 70%;
}
.searchBox {
  display: flex;
  justify-content: center;
  margin-top: 2rem;
}
</style>
