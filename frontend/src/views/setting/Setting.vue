<template>
  <v-app id="setting" class="setting">
    <v-list>
      <!-- 알림설정 -->
      <v-list-group color="#B71C1C" no-action>
        <template v-slot:activator>
          <v-list-item-title class="font-weight-black">알림설정</v-list-item-title>
        </template>
        <v-list-item>
          <v-list-item-content>
            <v-list-item-title class="font-weight-medium">관계</v-list-item-title>
            <v-list-item-subtitle
              >팔로우 신청, 팔로워 신청, 좋아요, 댓글 알림 등</v-list-item-subtitle
            >
          </v-list-item-content>
          <!-- <v-switch v-model="switch1"></v-switch> -->
          <div><v-switch inset color="red darken-4primary"></v-switch></div>
        </v-list-item>
        <v-list-item>
          <v-list-item-content>
            <v-list-item-title class="font-weight-medium">챌린지</v-list-item-title>
            <v-list-item-subtitle>챌린지 시작 안내</v-list-item-subtitle>
          </v-list-item-content>
          <v-switch inset color="red darken-4primary"></v-switch>
        </v-list-item>
      </v-list-group>
      <!-- 계정설정 -->
      <v-list-group color="#B71C1C" no-action>
        <template v-slot:activator>
          <v-list-item-title class="font-weight-black">계정설정</v-list-item-title>
        </template>
        <v-list-item router-link :to="{ name: 'ModifyUser' }">
          <v-list-item-content>
            <v-list-item-title class="font-weight-medium">회원정보 수정</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item>
          <v-list-item-content>
            <v-list-item-title class="font-weight-medium" @click="openDialog = !openDialog"
              >회원탈퇴</v-list-item-title
            >
            <p v-if="openDialog">
              <PopUp
                title="회원 탈퇴"
                text="탈퇴시 3개월 동안 재가입할 수 없습니다."
                btnLeft="취소"
                btnRight="탈퇴"
              /></p
          ></v-list-item-content>
        </v-list-item>
      </v-list-group>
      <!-- 회원관리 -->
      <v-list-group color="#B71C1C" @click="$router.push('/manage-member')">
        <template v-slot:activator>
          <v-list-item-title class="font-weight-black">회원관리</v-list-item-title>
        </template>
      </v-list-group>
    </v-list>
    <Footer />
  </v-app>
</template>

<script>
import "./setting.css";
import Footer from "../../components/include/Footer.vue";
import PopUp from "../../components/common/PopUp.vue";

export default {
  components: { Footer, PopUp },
  data: () => ({
    // switch1: true,
    openDialog: false,
  }),
};
</script>
