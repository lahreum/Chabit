<template>
  <v-carousel :show-arrows="false"
          cycle
          interval = 4000
          height = 50vh
          align="center"
          justify="center"
  >
    <v-carousel-item v-for="(Image,i) in Images" :key="i">
    <div classs="instruction-Container">
      <img id="InstructionImage" :src="Image.src"
            align="center"
            justify="center">
    </div>
    </v-carousel-item>
  </v-carousel>
</template>

<script>
  export default {
    data () {
      return {
        Images: [
          {
            src: require('@/assets/img/InstructionImage/Coding.png')
          },
          {
            src: require('@/assets/img/InstructionImage/Cooking.png')
          },
          {
            src: require('@/assets/img/InstructionImage/Yoga.png')
          },
          {
            src: require('@/assets/img/InstructionImage/HobbyCool.png')
          },
        ],
      }
    },
  }
</script>

<style>
  #InstructionImage{
    width : 80vw;
    height: 35vh;
    max-width : 350px;
    max-heigth : 240px;
  }
  .v-carousel {
    height : 50vh;
  }
</style>