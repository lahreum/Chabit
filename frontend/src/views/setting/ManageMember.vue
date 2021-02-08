<template>
  <v-card class="manage-member" flat>
    <v-card-title color="white">
      <v-spacer></v-spacer>
      <v-autocomplete
        v-model="search"
        append
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-no-data
        hide-details
        clearable
        item-value="userName"
        item-text="userName"
        :items="memberInfo"
        :menu-props="{ top: false, bottom: true, offsetY: true, offsetX: true }"
      ></v-autocomplete>
      <v-spacer></v-spacer>
      <v-btn elevation="1" rounded plain depressed color="error" @click="check()"> Forced</v-btn>
    </v-card-title>
    <v-data-table
      v-model="selected"
      :headers="headers"
      :items="memberInfo"
      item-key="userName"
      show-select
      sort
      :search="search"
      class="elevation-1"
      hide-default-footer
      @toggle-select-all="selectAllToggle"
    >
      <template v-slot:[`item.data-table-select`]="{ item, isSelected, select }">
        <v-simple-checkbox
          v-ripple
          :value="isSelected"
          :readonly="checkRole(item.userRole)"
          :disabled="checkRole(item.userRole)"
          @input="select($event)"
        ></v-simple-checkbox>
      </template>
    </v-data-table>
  </v-card>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      search: "",
      selected: [],
      disabledCount: 0,
      headers: [
        { text: "Name", value: "userName" },
        {
          text: "Email",
          align: "start",
          sortable: false,
          value: "userEmail",
        },
        { text: "Nickname", value: "userNickname" },
        { text: "Password", sortable: false, value: "userPassword" },
        { text: "Permission", value: "userRole" },
      ],
      memberInfo: [],
    };
  },
  methods: {
    // 전체선택 자동 계산
    selectAllToggle(props) {
      if (this.selected.length != this.memberInfo.length - this.disabledCount) {
        this.selected = [];
        props.items.forEach((item) => {
          if (!this.checkRole(item.userRole)) {
            this.selected.push(item);
          }
        });
      } else this.selected = [];
    },
    check() {
      console.log(this.disabledCount);
    },
    // 사용자 권한 확인
    checkRole(input) {
      if (input != "USER") return true;
      else return false;
    },

    // 데이터 가져오기
    getMemberList() {
      axios
        .get("http://i4b207.p.ssafy.io/api/v1/users")
        .then((res) => {
          if (res.data.status == "success") {
            this.memberInfo = res.data.data;
            // 관리자 권한일 경우 선택 못하도록 카운트
            this.memberInfo.map((item) => {
              if (this.checkRole(item.userRole)) this.disabledCount += 1;
            });
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  created() {
    // 자동으로 함수 실행
    this.getMemberList();
  },
};
</script>
