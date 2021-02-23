<template>
  <v-card flat>
    <p class ="fix-font-mid">진행중인 챌린지 ({{date}})</p>
    <v-row>
      <v-col cols="12" v-for="(item, idx) in items" :key="idx" class="py-2">
        <v-card elevation="0" outlined class="py-2" @click="moveToChallengeDetail(item)">
          <span style = "padding-left : 2vh" class ="fix-font-mid-bold-half">{{item.challengeName}}</span>
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
