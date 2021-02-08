<template>
  <v-container>
    <div class="all-ranking">
      <span>전체 랭킹</span>
      <i class="fas fa-plus" @click="$router.push('/ranking')"></i>
    </div>
    <v-card
      class="mx-auto rounded-xl"
      max-width="385"
      color="red darken-4"
      outlined
    >
      <v-list>
        <v-list-item
          v-for="item in ranking"
          :key="item.rank"
        >
          <v-list-item-subtitle class="text-left" style="font-size: 1.2rem; font-weight: 600;">
            {{ item.rank }}
          </v-list-item-subtitle>

          <v-list-item-subtitle class="text-left" style="font-size: 1.1rem; font-weight: 600;">
            {{ item.nickname }}
          </v-list-item-subtitle>

          <v-list-item-subtitle class="text-right" style="font-size: 1.2rem; font-weight: 600; color: #B71C1C;">
            {{ item.point }}
          </v-list-item-subtitle>
        </v-list-item>
      </v-list>

    </v-card>
  </v-container>
</template>

<script>
export default {
  data () {
    return {
      ranking: [
        {rank: '1위', nickname: '백찐개', point: '129345'},
        {rank: '2위', nickname: '어텀리', point: '29246'},
        {rank: '3위', nickname: '김미역', point: '9441'},
      ],
    }
  },
}
</script>

<style scoped>
.all-ranking {
  margin-top: 1rem;
  padding-left: 0.5rem;
  padding-bottom: 1rem;
  font-size: 1.5rem;
  font-weight: 600;
}

.all-ranking > .fa-plus {
  padding-left: 0.5rem;
  color: #B71C1C;
}
</style>