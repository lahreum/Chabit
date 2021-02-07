<template>
  <div>
    <v-card
      class="mx-auto"
    >
      <v-img
        height="300"
        :src="item.src"
        fixed
        top
      ></v-img>
    </v-card>
    <v-card
      class="mx-auto rounded-t-xl challenge-content"
    >
      <v-card-text class="challenge-detail">
        <p style="font-size: 1.4rem; font-weight: 600; margin-top: 1rem; margin-left: 0.5rem; color: black;">
          {{item.title}}
        </p>
        <i class="far fa-smile" style="font-size: 1.2rem; margin-left: 0.6rem;"></i>
        <span style="font-size: 1.1rem; margin-left: 0.5rem;"><span style="font-weight: 600">{{item.total}}</span>명 참여 중</span>
        <div class="hash-tag-bundle">
          <hash-tag :content="item.category"/>
          <hash-tag :content="item.word"/>
        </div>
        <p style="margin-top: 2rem"></p>
        <hr>
        <p style="margin-top: 2rem"></p>
        <v-row>
          <v-col cols="4" class="py-1">
            <span style="font-weight: 600;">챌린지 기간</span>
          </v-col>
          <v-col cols="8" class="py-1">
            <span>{{item.startDate}} ~ {{item.endDate}}</span>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="4" class="py-1">
            <span style="font-weight: 600;">카테고리</span>
          </v-col>
          <v-col cols="8" class="py-1">
            <span>{{item.category}}</span>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="4" class="py-1">
            <span style="font-weight: 600;">참가비</span>
          </v-col>
          <v-col cols="8" class="py-1">
            <span>{{item.chabit}}채빗</span>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="4" class="py-1">
            <span style="font-weight: 600;">보상</span>
          </v-col>
          <v-col cols="8" class="py-1">
            <span>인증서 수여</span>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="4" class="py-1">
            <span style="font-weight: 600;">공휴일 인증</span>
          </v-col>
          <v-col cols="8" class="py-1">
            <span v-if="item.holiday">O</span>
            <span v-else>X</span>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="4" class="py-1">
            <span style="font-weight: 600;">인증시간</span>
          </v-col>
          <v-col cols="8" class="py-1">
            <span>{{item.startPossible}} ~ {{item.endPossible}} 인증 가능</span>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="4" class="py-1">
            <span style="font-weight: 600;">인증 방법</span>
          </v-col>
          <v-col cols="8" class="py-1">
            <span>{{item.proofType}}</span>
          </v-col>
        </v-row>
        <p style="margin-top: 2rem"></p>
        <hr>
        <p style="margin-top: 2rem"></p>
        <v-row>
          <v-col cols="4" class="py-1">
            <span style="font-weight: 600;">챌린지 설명</span>
          </v-col>
          <v-col cols="8" class="py-1">
            <span>{{item.describeChallenge}}</span>
          </v-col>
        </v-row>
        <p style="margin-top: 2rem"></p>
        <hr>
        <p style="margin-top: 2rem"></p>
        <div>
          <span style="font-size: 1.1rem; margin-left: 1.1rem; font-weight: 600;">올바른 인증 예시</span>
        </div>
        <p></p>
        <div style="display: flex; flex-direction: column; align-items: center;">
          <v-img
            class="rounded-lg"
            width="90%"
            :src="item.photoExample"
          ></v-img>
        </div>
        <p></p>
        <div style="text-align: center;">
          <span>오늘의 제스처와 함께 사진을 찍어 올려주세요.</span>
        </div>
        <p style="margin: 1rem 0 7rem 0"></p>
        <div style="margin-left: 7%;">
          <v-btn
            rounded
            color="red darken-4"
            dark
            width="80%"
            height="60px"
            fixed
            bottom
          >
            참가하기
          </v-btn>
        </div>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import HashTag from '../../components/common/HashTag.vue'
export default {
  components: { HashTag },
  data() {
    return {
      item: {
        src: 'https://cdn.vuetifyjs.com/images/cards/cooking.png',
        title: '도전! 파이 만들기 챌린지',
        official: true,
        category: '취미',
        proof: 1,
        word: '',
        total: 1428,
        startDate: "2021/01/20",
        endDate: "2021/02/03",
        chabit: 1000,
        proofType: "오븐에서 갓 꺼낸 상태의 사진 올리기",
        describeChallenge: "맛있는 파이를 만들며, 요리 실력을 키워봅시다!",
        holiday: false,
        startPossible: '00:00',
        endPossible: '24:00',
        photoExample: 'http://storage.enuri.info/pic_upload/knowbox2/202006/03591085720200613575bf006-c900-4a5d-b2d2-985893e0530b.JPG'
      }
    }
  },
  mounted() {
    this.item.word = "주 " + this.item.proof + "회 인증"
  }
}
</script>

<style scoped>
.challenge-content {
  margin: -50px 0 0 0;
}

.hash-tag-bundle {
  display: flex;
  align-items: flex-start;
}

.challenge-detail {
  font-size: 1.1rem;
}
</style>