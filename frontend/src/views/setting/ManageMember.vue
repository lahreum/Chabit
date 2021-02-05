<template>
  <v-card>
    <v-card-title color="white">
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-spacer></v-spacer>
      <v-btn color="red darken-4 white--text">Forced</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :single-select="singleSelect"
      :search="search"
      sort
      item-key="name"
      show-select
      class="elevation-1"
      hide-default-footer
      :items="memberInfo"
      v-model="selected"
    >
    </v-data-table>
  </v-card>
</template>

<script>
export default {
  methods: {
    onClick() {
      console.log(this.withdraw);
    },
    test() {
      if (this.memberInfo.withdraw) {
        this.selected = this.memberInfo;
      }
    },
  },
  data() {
    return {
      search: "",
      singleSelect: false,
      selected: [],
      headers: [
        { text: "Name", value: "name" },
        {
          text: "Email",
          align: "start",
          sortable: false,
          value: "email",
        },
        { text: "Nickname", value: "nickName" },
        { text: "Password", sortable: false, value: "password" },
        { text: "Permission", value: "permission" },
      ],
      memberInfo: [
        {
          name: "내이름",
          email: "hello@ssafy.com",
          nickName: "김미역",
          password: "asdf12345",
          permission: "회원",
        },
        {
          name: "쟤이름",
          email: "saljjingae@help.com",
          nickName: "백찐개",
          password: "qwer5789",
          permission: "관리자",
        },
        {
          name: "걔이름",
          email: "ssafy@ssafy.com",
          nickName: "어반동",
          password: "KDJfdjg!",
          permission: "회원",
        },
        {
          name: "헐이름",
          email: "daejeon@ssafy.com",
          nickName: "어럼리",
          password: "sdfahiul654",
          permission: "회원",
        },
      ],
    };
  },
};
</script>
