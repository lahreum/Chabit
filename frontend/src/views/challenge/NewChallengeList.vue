<template>
  <v-container>
    <div class="new-challenge">
      <span class="hot">새로운</span><span>챌린지</span>
    </div>
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
          src: 'https://www.gereports.kr/wp-content/uploads/2020/09/IMG_1219_0-738x820.jpg',
          title: '레고로 작품 만들기 챌린지',
          official: true,
          category: '취미',
          proof: 5,
          word: ''
        },
        {
          src: 'http://image.dongascience.com/Photo/2019/07/35bfc9ddc157d6faa3c211cee6c55715.jpg',
          title: '체중 2kg 감량하기 챌린지',
          official: false,
          category: '다이어트',
          proof: 7,
          word: ''
        },
        {
          src: 'https://mblogthumb-phinf.pstatic.net/MjAyMDAxMDNfMjY3/MDAxNTc4MDE4NDk2NjQ0.Y5bF47ZdptLfUeQQFJJ5MDQtP7A6ufURnW3-5Iovb10g.K8DO-8hkF2EOYh3lGpl6Lp55HDne0nUOFXLrGXrH34Yg.PNG.drawinart/%ED%8E%AD%EC%88%98_%EA%B7%B8%EB%A6%AC%EA%B8%B0.png?type=w800',
          title: '펭수 그리기 챌린지',
          official: true,
          category: '취미',
          proof: 1,
          word: ''
        },
        {
          src: 'https://img.huffingtonpost.com/asset/5d7011fa2400004f007476d5.jpeg?ops=scalefit_630_noupscale',
          title: '하루 영단어 10개 공부하기 챌린지',
          official: true,
          category: '공부',
          proof: 7,
          word: ''
        },
      ],
    }
  },
}
</script>

<style scoped>
.new-challenge {
  padding-left: 0.5rem;
  margin-top: 1.5rem;
  margin-bottom: 1rem;
  font-size: 1.5rem;
  font-weight: 600;
}

.new-challenge > .hot {
  color: #B71C1C;
  padding-right: 0.5rem;
}

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