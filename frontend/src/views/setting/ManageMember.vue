<template>
  <v-card>
    <v-card-title color="white">
      <v-spacer></v-spacer>
      <v-autocomplete
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-autocomplete>
      <v-spacer></v-spacer>
      <v-btn elevation="1" rounded plain depressed color="error"> Forced</v-btn>
    </v-card-title>
    <v-data-table
      v-model="selected"
      :headers="headers"
      :items="memberInfo"
      item-key="name"
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
          :readonly="item.permission"
          :disabled="item.permission"
          @input="select($event)"
        ></v-simple-checkbox>
      </template>
    </v-data-table>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      search: "",
      selected: [],
      disabledCount: 0,
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
          permission: true,
        },
        {
          name: "쟤이름",
          email: "saljjingae@help.com",
          nickName: "백찐개",
          password: "qwer5789",
          permission: false,
        },
        {
          name: "걔이름",
          email: "ssafy@ssafy.com",
          nickName: "어반동",
          password: "KDJfdjg!",
          permission: false,
        },
        {
          name: "헐이름",
          email: "daejeon@ssafy.com",
          nickName: "어럼리",
          password: "sdfahiul654",
          permission: false,
        },
      ],
    };
  },
  methods: {
    selectAllToggle(props) {
      if (this.selected.length != this.memberInfo.length - this.disabledCount) {
        this.selected = [];
        const self = this;
        props.items.forEach((item) => {
          if (!item.permission) {
            self.selected.push(item);
          }
        });
      } else this.selected = [];
    },
  },
  created() {
    const self = this;
    this.memberInfo.map((item) => {
      if (item.permission) self.disabledCount += 1;
    });
  },
};
</script>
