<template>
  <v-sheet
    class="mx-auto"
    max-width="1800"
  >
    <v-slide-group
      center-active
    >
      <v-slide-item
        v-for="(item,i) in items"
        :key="i"
        v-slot="{ toggle }"
      >
        <v-card
          class="ma-2 rounded-lg"
          height="390"
          width="370"
          @click="toggle"
        >
          <v-img
            class="rounded-lg"
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
      </v-slide-item>
    </v-slide-group>
  </v-sheet>
</template>

<script>
import HashTag from '../../components/common/HashTag.vue'
export default {
  components: { HashTag },
  data () {
    return {
      items: [
        {
          src: 'https://img.huffingtonpost.com/asset/5acacc721f00002d0016ca31.jpeg?cache=0lOXly1x2s&ops=1778_1000',
          title: '하루 5분 플랭크 챌린지',
          official: true,
          category: '운동',
          proof: 5,
          word: ''
        },
        {
          src: 'https://www.wallpapertip.com/wmimgs/41-412909_github-octocat.jpg',
          title: '1일 1커밋 챌린지',
          official: true,
          category: '공부',
          proof: 7,
          word: ''
        },
        {
          src: 'http://img.insight.co.kr/static/2016/01/25/700/fguurh33in34jq0x698a.jpg',
          title: '아침 6시 기상하기 챌린지',
          official: true,
          category: '생활습관',
          proof: 5,
          word: ''
        },
        {
          src: 'https://t1.daumcdn.net/cfile/tistory/990290335A0A2B2B0B',
          title: '매일 화분에 물주기 챌린지',
          official: false,
          category: '돌봄',
          proof: 7,
          word: ''
        },
      ],
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
.hash-tag-bundle {
  display: flex;
  align-items: flex-start;
}
</style>