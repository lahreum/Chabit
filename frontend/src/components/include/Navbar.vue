<template>
  <div id="navbar" class="navbar">
    <v-app id="inspire">
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-navigation-drawer v-model="drawer" absolute temporary color="#a62f22">
        <v-list nav dense>
          <div id="nav-top" class="nav-top">
            <v-list-item>
              <v-list-item-content>
                <div class="nav-top-avatar">
                  <v-list-item-avatar style="height: 60px; min-width: 60px; width: 60px">
                    <!-- 프로필 사진 입력 -->
                    <!-- <v-img src=></v-img> -->
                    <v-icon>mdi-account-circle</v-icon>
                  </v-list-item-avatar>
                </div>
                <div class="nav-top-content">
                  <v-list-item-title class="title"> Saljjingae </v-list-item-title>
                  <v-list-item-subtitle color="white"> Bronze </v-list-item-subtitle>
                  <v-list-item-subtitle> 200/3000p </v-list-item-subtitle>
                </div>
              </v-list-item-content>
            </v-list-item>
          </div>
          <v-divider></v-divider>
          <div id="nav-mid" class="nav-mid">
            <v-list-item-group v-model="group" active-class="text--accent-4">
              <v-list-item>
                <v-list-item-title>Home</v-list-item-title>
              </v-list-item>

              <v-list-item>
                <v-list-item-title>마이피드</v-list-item-title>
              </v-list-item>

              <v-list-item>
                <v-list-item-title>팔로우</v-list-item-title>
              </v-list-item>

              <v-list-item>
                <v-list-item-title>챌린지</v-list-item-title>
              </v-list-item>

              <v-list-item>
                <v-list-item-title>랭킹</v-list-item-title>
              </v-list-item>

              <v-list-item>
                <v-list-item-title>설정</v-list-item-title>
              </v-list-item>
            </v-list-item-group>
          </div>
          <!-- 로그인 되었을 때만 뜨도록 수정할 예정  -->
          <div id="nav-bottom" class="nav-bottom">
            <v-list-item-group v-model="group" active-class="text--accent-4">
              <v-list-item class="logout-list">
                <v-icon class="logout-icon" color="white">mdi-logout</v-icon>
                <v-list-item-title>로그아웃</v-list-item-title>
              </v-list-item>
            </v-list-item-group>
          </div>
          <!-- 로그아웃 -->
        </v-list>
      </v-navigation-drawer>
    </v-app>
  </div>
</template>

<script>
export default {
  data: () => ({
    drawer: false,
    group: null,
  }),
  watch: {
    group() {
      this.drawer = false;
    },
  },
};
</script>

<style scoped>
/* Navbar */
.v-navigation-drawer {
  border-radius: 0 20px 20px 0;
}
.v-list-item__title {
  color: white;
}
#nav-top {
  margin: 10% 5% 10% 5%;
}
#nav-top .v-list-item__title {
  line-height: 1.5;
}
#nav-top .v-list-item__content {
  display: contents;
  flex: 1 1;
}
#nav-top .v-list-item__content * {
  flex: 0;
}
#nav-top .v-avatar {
  border-radius: 100%;
}
#nav-mid {
  padding-left: 10px;
}
#nav-mid .v-list-item {
  width: 70%;
}
#nav-mid .v-list-item:hover {
  background-color: white;
  border-radius: 0 15px 15px 0;
  box-shadow: 2px 2px 7px #3c0903;
}
#nav-mid .theme--light.v-list-item--active::before {
  background-color: white;
  border-radius: 0 15px 15px 0;
  box-shadow: 2px 2px 7px #3c0903;
  opacity: 0.8;
  color: #a62f22;
}
#nav-mid .v-list-item .v-list-item__title {
  font-size: 20px;
  line-height: normal;
}
#nav-mid .v-list-item .v-list-item__title:hover {
  color: #a62f22;
}
#nav-mid .v-list .v-list-item--active {
  color: #a62f22;
}
#nav-bottom {
  width: 90%;
  margin: 0 auto;
  position: fixed;
  bottom: 10%;
}
#nav-bottom .v-list-item {
  width: fit-content;
  margin: 0 auto;
}
</style>
