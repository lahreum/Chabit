<template>
  <div class="ranking-list">
    <v-container grid-list-xs>
      <v-layout>
        <v-flex class="section">
          <v-card class="mx-auto rounded-xl" max-width="100%" flat>
            <v-list rounded color="rgb(183,28,28,.1)">
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
                :menu-props="{
                  top: false,
                  bottom: true,
                  offsetY: true,
                  offsetX: true,
                }"
              >
              </v-autocomplete>
            </div>
            <v-expand-transition>
              <v-list v-if="search" rounded color="rgb(100, 100, 100,.1)">
                <v-list-item
                  :items="users"
                  item-key="search"
                  show-select
                  sort
                  :search="search"
                >
                  <v-list-item-avatar class="avatar" size="60">
                    <v-img
                      :alt="`${search.userNickname} avatar`"
                      :src="search.userImage"
                    ></v-img>
                  </v-list-item-avatar>
                  <v-list-item-content>
                    <v-list-item-title
                      class="black--text"
                      v-text="search.userNickname"
                    >
                    </v-list-item-title>
                    <v-list-item-subtitle
                      class="point gray--text"
                      v-text="search.userPoints"
                    >
                    </v-list-item-subtitle>
                  </v-list-item-content>
                  <v-icon
                    v-if="
                      users.findIndex(
                        (i) => i.userNickname == search.userNickname
                      ) == 0
                    "
                    color="yellow darken-2"
                    >mdi-crown</v-icon
                  >
                  <v-icon
                    v-if="
                      users.findIndex(
                        (i) => i.userNickname == search.userNickname
                      ) == 1
                    "
                    color="grey lighten-2"
                    >mdi-crown</v-icon
                  >
                  <v-icon
                    v-if="
                      users.findIndex(
                        (i) => i.userNickname == search.userNickname
                      ) == 2
                    "
                    color="brown lighten-2"
                    >mdi-crown</v-icon
                  >
                  <v-footer color="transparent">
                    <v-list-item
                      class="black--text"
                      v-text="
                        users.findIndex(
                          (i) => i.userNickname == search.userNickname
                        ) +
                        1 +
                        `위`
                      "
                    ></v-list-item>
                  </v-footer>
                </v-list-item>
              </v-list>
            </v-expand-transition>
            <div>
              <v-list>
                <v-list-item v-for="(user, index) in users" :key="index">
                  <v-list-item-avatar class="avatar" size="60">
                    <v-img :src="user.userImage"></v-img>
                  </v-list-item-avatar>
                  <v-list-item-content>
                    <v-list-item-title class="black--text">{{
                      user.userNickname
                    }}</v-list-item-title>
                    <v-list-item-subtitle class="point gray--text"
                      >{{ user.userPoints }}
                    </v-list-item-subtitle>
                  </v-list-item-content>
                  <v-icon v-if="index == 0" color="yellow darken-2"
                    >mdi-crown</v-icon
                  >
                  <v-icon v-if="index == 1" color="grey lighten-2"
                    >mdi-crown</v-icon
                  >
                  <v-icon v-if="index == 2" color="brown lighten-2"
                    >mdi-crown</v-icon
                  >
                  <v-footer color="transparent">
                    <v-list-item
                      class="black--text"
                      v-text="
                        users.findIndex(
                          (i) => i.userNickname == user.userNickname
                        ) +
                        1 +
                        `위`
                      "
                    ></v-list-item>
                  </v-footer>
                </v-list-item>
              </v-list>
            </div>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  props: {
    monthlyRanking: Boolean,
    categoryId: String,
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
    checkLog() {
      if (this.myEmail == null) {
        alert("로그인 후 확인 가능합니다.");
        this.$router.push("/login");
      }
    },
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
              console.log(res.data);
              if (res.data.data.userRanking.length <= 0) {
                alert("해당되는 데이터가 없습니다.");
              }
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
    this.checkLog();
  },
  watch: {
    monthlyRanking: function () {
      this.getRankList();
    },
    categoryId: function () {
      this.getRankList();
    },
    onlyFollowing: function () {
      this.getRankList();
    },
  },
};
</script>
