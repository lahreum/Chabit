<template>
  <div id="popup" class="popup">
    <v-app id="inspire">
      <v-row justify="center">
        <v-dialog v-model="dialog" persistent max-width="290">
          <v-card class="rounded-xl justify-center text-sm-center">
            <v-card-title class="headline justify-center rounded-xl">
              <strong>{{ title }}</strong>
            </v-card-title>
            <v-card-text>{{ text }}</v-card-text>
            <p>회원가입하시겠습니까?</p>
            <v-divider></v-divider>
            <v-card-actions class="rounded-xl">
              <v-spacer></v-spacer>
              <v-btn text @click="dialog = false">
                No
              </v-btn>
              <v-spacer></v-spacer>
              <v-divider vertical></v-divider>
              <v-spacer></v-spacer>
              <v-btn color="red darken-4" text @click="dialog = false">
                Yes
              </v-btn>
              <v-spacer></v-spacer> </v-card-actions
          ></v-card>
        </v-dialog>
      </v-row>
    </v-app>
  </div>
</template>

<script>
export default {
  props: ["title", "text"],
  data() {
    return {
      dialog: true,
    };
  },
};
</script>
