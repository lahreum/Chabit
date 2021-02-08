<template>
  <v-container>
    <div class="deadline-challenge">
      <span class="hot">마감임박</span><span>챌린지</span>
    </div>
    <v-carousel 
      hide-delimiters 
      cycle 
      :show-arrows-on-hover="true"
    >
      <v-carousel-item
        v-for="(item,i) in items"
        :key="i"
        reverse-transition="fade-transition"
        transition="fade-transition"
      >
        <v-card
          class="mx-auto my-12 rounded-xl"
          max-width="380"
          :loading="loading"
          @click="reverse"
          style="background-color: white;"
        >
          <template slot="progress">
            <v-progress-linear
              color="red darken-4"
              height="10"
              indeterminate
            ></v-progress-linear>
          </template>

          <v-img
            height="250"
            :src="item.src"
          ></v-img>

          <v-card-title style="color: black; font-weight: 600;">{{item.title}}</v-card-title>

          <v-card-text>
            <v-row
              align="center"
              class="mx-0"
              v-if="item.official"
            >
              <div style="color: #B71C1C; font-size: 1.1rem; font-weight: 600;" >
                <i class="fas fa-gem"></i>
              </div>
              <div class="grey--text ml-1" style="font-size: 1.1rem; font-weight: 600;">
                공식 챌린지
              </div>
            </v-row>
            <p></p>
            <div class="hash-tag-bundle">
              <hash-tag :content="item.category"/>
              <hash-tag :content="item.word"/>
            </div>
          </v-card-text>
        </v-card>
      </v-carousel-item>
    </v-carousel>
  </v-container>
</template>

<script>
import HashTag from '../../components/common/HashTag.vue'
export default {
  components: { HashTag },
  data () {
    return {
      items: [
        {
          src: 'https://image.lawtimes.co.kr/images/82883.jpg',
          title: '디즈니 영화감상 후기 작성하기 챌린지',
          official: true,
          category: '취미',
          proof: 1,
          word: ''
        },
        {
          src: 'https://www.ekoreanews.co.kr/news/photo/202008/45510_55959_5344.jpg',
          title: '1일 1백준 알고리즘 문제 풀기 챌린지',
          official: true,
          category: '공부',
          proof: 5,
          word: ''
        },
        {
          src: 'https://blog.kakaocdn.net/dn/8VBma/btqFujrPEQO/qUMZoIrWbXHy4vF8sPSZK1/img.jpg',
          title: '봉골레 파스타 만들기 챌린지',
          official: true,
          category: '취미',
          proof: 1,
          word: ''
        },
        {
          src: 'https://wonderfulmind.co.kr/wp-content/uploads/2018/05/knitting.jpg',
          title: '뜨개질해서 목도리 만들기 챌린지',
          official: false,
          category: '취미',
          proof: 2,
          word: ''
        },
      ],
      loading: false,
    }
  },
  methods: {
    reverse () {
      this.loading = true

      setTimeout(() => (this.loading = false), 2000)
    }
  },
  mounted() {
    for (let i=0; i < this.items.length; i++) {
      this.items[i].word = "주 " + this.items[i].proof + "회 인증"
    }
  }
}
</script>

<style scoped>
.deadline-challenge {
  padding-left: 0.5rem;
  margin-top: 2.5rem;
  margin-bottom: -1.5rem;
  font-size: 1.5rem;
  font-weight: 600;
}

.deadline-challenge > .hot {
  color: #B71C1C;
  padding-right: 0.5rem;
}

.hash-tag-bundle {
  display: flex;
  align-items: flex-start;
}
</style>