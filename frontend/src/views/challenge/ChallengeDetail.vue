<template>
  <div>
    <v-card
      :loading="loading"
      class="mx-auto"
    >
      <template slot="progress">
        <v-progress-linear
          color="deep-purple"
          height="10"
          indeterminate
        ></v-progress-linear>
      </template>

      <v-img
        height="300"
        src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
        fixed
        top
      ></v-img>
    </v-card>
    <v-card
      class="mx-auto rounded-t-xl challenge-content"
    >
      <v-card-text>
        <p>
          도전! 파이 만들기
        </p>
        <i class="far fa-smile"></i>
        <span>1,428명 참여 중</span>
        <div class="hash-tag-bundle">
          <hash-tag/>
          <hash-tag/>
          <hash-tag/>
        </div>
        
        <i class="far fa-clock"></i>
        <span>2021/01/20 ~ 2021/02/03</span>
        <br>
        <i class="fas fa-book"></i>
        <span>취미</span>
        <br>
        <i class="fas fa-gem"></i>
        <span> 참가비: 1,000채빗</span>
        <br>
        <i class="fas fa-award"></i>
        <span>인증서 수여</span>
        <br>
        <i class="far fa-star"></i>
        <span>오븐에서 갓 꺼낸 상태의 사진 올리기</span>
        <br>
        <br>
        <i class="fas fa-file"></i>
        <span>맛있는 파이를 만들며, 요리 실력을 키워봅시다!</span>
        <br>
        <br>
        <i class="fas fa-hourglass-half"></i>
        <span>공휴일 인증 X</span>
        <br>
        <i class="fas fa-user-clock"></i>
        <span>00:00 ~ 24:00 인증 가능</span>
        <br>
        <i class="fas fa-camera"></i>
        <span>올바른 인증 예시</span>
        <br>
        <br>
        <v-img
          width="90%"
          src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
        ></v-img>
        <span>오늘의 제스처와 함께 사진을 찍어 올려주세요.</span>
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

</style>