<template>
  <div class="modifyuser">
    <div class="outside">
      <div class="logo"></div>
      <img class="chabit-logo" src="../../assets/img/chabit.jpg" alt="chabit" />
      <div class="inside" id="inside">
        <p>Modify</p>
        <v-card-text dense>
          <v-flex class="mb-4 text-center">
            <v-badge
              color="red darken-4"
              bordered
              bottom
              icon="mdi-camera-outline"
              offset-x="30"
              offset-y="30"
            >
              <v-avatar size="128" color="grey">
                <img src="../../assets/img/maja.png" />
              </v-avatar>
            </v-badge>
          </v-flex>
          <v-text-field dense filled readonly v-model="form.name" label="Name"></v-text-field>
          <v-text-field
            filled
            dense
            readonly
            v-model="form.email"
            label="Email"
            type="email"
          ></v-text-field>
          <v-text-field
            dense
            v-model="form.password"
            label="Password"
            type="password"
          ></v-text-field>
          <v-text-field v-model="form.confirm" label="Confirm" type="password"></v-text-field>
          <div class="input-with-btn">
            <v-text-field v-model="form.nickName" label="Nickname"></v-text-field
            ><button class="btn-get-check">Check</button>
          </div>
          <div class="input-with-btn">
            <v-text-field v-model="form.phoneNumber" label="Phone number"></v-text-field>
            <button class="btn-get-check">Send</button>
          </div>
          <div class="input-with-btn">
            <v-text-field
              v-model="form.certificationNumber"
              label="Certification Number"
            ></v-text-field
            ><button class="btn-get-check">Confirm</button>
          </div>
        </v-card-text>
        <v-card-actions class="inside-btn"
          ><v-spacer></v-spacer><btn-small text="Cancel" class="btn-left"></btn-small
          ><v-spacer></v-spacer><btn-small text="Change"></btn-small><v-spacer></v-spacer>
          <v-spacer></v-spacer>
        </v-card-actions>
        <!-- <avatar-picker
          v-model="showAvatarPicker"
          :current-avatar="form.avatar"
          @selected="selectAvatar"
        ></avatar-picker> -->
      </div>
    </div>
  </div>
</template>

<script>
import BtnSmall from "../../components/common/BtnSmall.vue";
import "../user/user.css";
export default {
  components: { BtnSmall },
  pageTitle: "My Profile",
  data() {
    return {
      loading: false,
      form: {
        name: "",
        email: "",
        password: "",
        confirm: "",
        nickName: "",
        phoneNumber: "",
        certificationNumber: "",
      },
      // showAvatarPicker: false,
    };
  },
  // methods: {
  //   openAvatarPicker() {
  //     this.showAvatarPicker = true;
  //   },
  //   selectAvatar(avatar) {
  //     this.form.avatar = avatar;
  //   },
  // },
};
</script>

<style scoped>
.inside > p {
  font-size: large;
  font-weight: 600;
  color: #942706;
  margin: 0 30px;
}
.btn-left {
  background-color: white;
  color: black;
  border: grey solid 1px;
}
.input-with-btn {
  display: flex;
}
.btn-get-check {
  margin: auto;
}
.inside-btn {
  margin-bottom: 2rem;
}
img {
  margin: 0;
}
</style>
