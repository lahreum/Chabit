<template>
  <div class="carousel-container">
  <h1>환영합니다.</h1>
  <v-carousel :show-arrows="false"
          cycle
          interval = 3700
          height = 55vh
          hide-delimiter-background
  >
    <v-carousel-item v-for="(Item,i) in Items" :key="i">
    <div classs="instruction-container">
      <img id="InstructionImage" :src="Item.src">
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
  </div>
</template>

<script>
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
  }
</script>

<style>
  h1{
    margin-bottom : 5vh;
  }
  #InstructionImage{
    width : 80vw;
    height: 35vh;
    max-width : 350px;
    max-heigth : 240px;
  }
  .carousel-container{
    width:100vw;
    height:100vh;
    text-align : center;
  }
  .item-content{
    align-items: center;
    align-self: center;
    flex-wrap: wrap;
    flex: 1 1;
    overflow: hidden;
  }
  .item-title{
      color:black;
      font-size : 2.7vh;
      font-weight: 600;
      margin-bottom : 1vh;
  }
  .item-subtitle{
    color : rgba(0, 0, 0, 0.6);
    font-size : 2.1vh;
    font-weight : 500;
  }
</style>