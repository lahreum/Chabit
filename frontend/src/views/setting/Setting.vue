<template>
  <v-app id="setting" class="setting">
    <v-list>
      <v-list-item-group v-model="selectedItem" color="#B71C1C">
        <v-list-item v-for="(item, i) in items" :key="i" router-link :to="{ name: item.path }">
          <!-- <v-list-item v-for="(item, i) in items" :key="i" @click="$router.push(item.path)"> -->
          <v-list-item-content>
            <v-list-item-title color="black" v-text="item.text"></v-list-item-title>
          </v-list-item-content>
          <v-list-item-icon>
            <v-icon>mdi-chevron-right</v-icon>
          </v-list-item-icon>
        </v-list-item>
      </v-list-item-group>
    </v-list>
    <Footer />
  </v-app>
</template>

<script>
import "./setting.css";
import Footer from "../../components/include/Footer.vue";
export default {
  components: { Footer },
  data: () => ({
    selectedItem: 1,
    items: [
      // { text: "알림설정", path: "alarm-setting" },
      { text: "알림설정", path: "AlarmSetting" },
      { text: "계정설정", path: "UserSetting" },
      { text: "회원관리", path: "ManageMember" },
    ],
  }),
};
</script>
