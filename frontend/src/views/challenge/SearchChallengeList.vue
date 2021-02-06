<template>
  <v-container>
    <v-container>
      <v-row
      >
        <v-col
          cols="6"
          sm
          v-for="(item, idx) in items"
          :key="idx"
        >
          <v-card
            max-width="12rem"
          >
            <v-img
              :src="item.src"
              height="8rem"
            >

            </v-img>
          </v-card>
          <p></p>
          <v-row
            align="center"
            class="mx-0"
            v-if="item.official"
          >
            <div style="color: #B71C1C; font-size: 0.8rem; font-weight: 600;" >
              <i class="fas fa-gem"></i>
            </div>
            <div class="grey--text ml-1" style="font-size: 0.8rem; font-weight: 600;">
              공식 챌린지
            </div>
          </v-row>
          <div class="new-challenge-info">
            <span>{{item.title}}</span>
          </div>
        </v-col>
      </v-row>
    </v-container>
    <div class="create-challenge">
      <v-btn
        elevation="2"
        color="red darken-4"
        x-large
        fab
        @click="$router.push('/create-challenge')"
      ><span class="plus">+</span></v-btn>
    </div>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      items: [
        {
          src: 'https://i.ytimg.com/vi/i0kiRwCOdI0/maxresdefault.jpg',
          title: '오전 5시 기상 챌린지',
          official: false,
          category: '취미',
          proof: 5,
          word: ''
        },
        {
          src: 'https://wonderfulmind.co.kr/wp-content/uploads/2017/03/%EA%B5%BF%EB%AA%A8%EB%8B%9D.jpg',
          title: '오전 6시 기상 챌린지',
          official: true,
          category: '다이어트',
          proof: 5,
          word: ''
        },
        {
          src: 'https://t2.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/Jp6/image/IwYzW72Uo74eQO38aVTWktWY8Gc.jpg',
          title: '오전 7시 기상 챌린지',
          official: true,
          category: '취미',
          proof: 5,
          word: ''
        },
      ],
    }
  },
}
</script>

<style scoped>
.new-challenge-info {
  margin: 0.5rem 0 -1rem 0;
  display: flex;
  justify-content: space-between;
  font-size: 1rem;
  font-weight: 600;
  max-width: 12rem;
}

.create-challenge {
  position: fixed;
  right: 2rem;
  bottom: 2rem;
}

.plus {
  margin-top: -0.1rem;
  font-size: 4rem;
  color: white;
}
</style>