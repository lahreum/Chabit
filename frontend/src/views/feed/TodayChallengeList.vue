<template>

  <!-- <v-card class="mx-auto today-challenge rounded-10" tile style="width:100%; margin-top:20px; padding:10px;">
            <v-app>
                <v-list>
                    <v-subheader style="font-weight:600">현재 진행중인 챌린지</v-subheader>
                    <v-list-item-group
                        v-model="selectedItem"
                        color="#B71C1C" 
                    >
                        <v-list-item
                        v-for="(item, i) in items"
                        :key="i"
                        >
                        <v-list-item-content>
                            <router-link to="/proof" style="text-decoration:none; color:inherit;"><v-list-item-title v-text="item.text" style="text-align:left; padding-left:15px; font-size:13px;"></v-list-item-title></router-link>
                        </v-list-item-content>
                        </v-list-item>
                    </v-list-item-group>
                </v-list>
            </v-app>
        </v-card> -->
  <v-card flat>
    <p style="font-size: 1rem; font-weight: 600;">진행중인 챌린지 ({{date}})</p>
    <v-row>
      <v-col cols="12" v-for="(item, idx) in items" :key="idx" class="py-2">
        <v-card elevation="1" color="grey lighten-3" class="py-2" @click="moveToChallengeDetail(item)">
          <span style="font-size: 1rem;">{{item.challengeName}}</span>
        </v-card>
      </v-col>
    </v-row>
  </v-card>
</template>

<script>
export default {
    props: ['date', 'items'],
    data: () => ({
      selectedItem: 1,
    }),
    methods: {
      moveToChallengeDetail(item) {
      this.$store.commit("SELECTEDCHALLENGE", item.challengeID);
      this.$router.push("/challenge-detail");
    }
    }
}
</script>

<style>
/* .v-sheet.v-card{
    border-radius: 20px;
    margin: 0 auto;
} */
</style>