<template>
  <sequential-entrance fromRight>
  <div class="carousel-container">
  <h1 id ="instruction-headline" class="big-title">환영합니다.</h1>
  <v-carousel :show-arrows="true"
          :cycle = true
          :interval = 3700
          height = 55vh
          :show-arrows-on-hover = true
          :hide-delimiters = true
  >
    <v-carousel-item v-for="(Item,i) in Items" :key="i">
    <div classs="instruction-container">
      <img id="instructionImage" :src="Item.src">
      <v-list light>
        <v-list-item>
          <div class="item-content">
            <div class ="item-title" v-html="Item.title"></div>
            <div class ="item-subtitle" v-html="Item.subtitle"></div>
          </div>
        </v-list-item>
      </v-list>
    </div>
    </v-carousel-item>
  </v-carousel>
    <div>
          <button class="btn-skip" @click="onSkip">
            건너뛰기
          </button>
    </div>
  </div>
    </sequential-entrance>
</template>

<script>

import "./user.css";
import "../../assets/css/font.css"
  export default {
    data () {
      return {
        Items: [
          {
            src: require('@/assets/img/InstructionImage/Coding.png'),
            title : "같이 공부해요!",
            subtitle : "만약 공부를 한다면,<br>공부 챌린지에 함께 도전해요!"
          },
          {
            src: require('@/assets/img/InstructionImage/Cooking.png'),
            title : "취미도 공유해요!",
            subtitle : "요리부터 미술까지!<br>다양한 취미에 참가해봐요!"
          },
          {
            src: require('@/assets/img/InstructionImage/Yoga.png'),
            title : "함께 운동해요!",
            subtitle : "작심삼일 그것은 옛말입니다.<br>이제는 다른 사람과 함께 운동해봐요!"
          },
          {
            src: require('@/assets/img/InstructionImage/Congratu.png'),
            title : "함께 축하해요!",
            subtitle : "성공 시 리뷰를 남기고,<br>다른사람과 함께 축하해봐요."
          },
          {
            src: require('@/assets/img/InstructionImage/HobbyCool.png'),
            title : "멋져요!",
            subtitle : "다른 사람의 리뷰에 `멋져요!`를 남겨봐요.<br>동기부여가 남다르겠죠?"
          },
        ],
      }
    },
    methods : {
      onSkip(){
        this.$router.push("/login");
      }
    }
  }
</script>