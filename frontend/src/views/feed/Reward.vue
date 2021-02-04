<template>
  <div>
    <!-- 상단 금,은,동 계기판 -->
      <v-card style="width:90%;">
        <v-list-item>
            <div class="top-text" style="width:90%; margin:10px auto;">
                <div style="float:left;width:30%;">1</div>
                <div style="float:left;margin-left:5%;width:30%;">2</div>
                <div style="float:right;width:30%;">0</div>
            </div>
        </v-list-item>
        <v-list-item>
            <div style="width:90%; margin:10px auto;">
                <div style="float:left;width:30%;"><img :src="require(`../../assets/img/silver.jpg`)" style="height:40px;"></div>
                <div style="float:left;margin-left:5%;width:30%;"><img :src="require(`../../assets/img/gold.jpg`)" style="height:40px;"></div>
                <div style="float:right;width:30%;"><img :src="require(`../../assets/img/bronze.jpg`)" style="height:40px;"></div>
            </div>
        </v-list-item>
      </v-card>
      <!-- 뱃지 리스트 -->
      <v-card>
        <div style="width:90%; margin:20px; padding:10px;text-align:center;">
          <v-row no-gutters>
            <v-col>
                <img :src="require(`../../assets/img/piggybank.png`)" style="height:70px;">
            </v-col>
            <v-col order="12">
                <img :src="require(`../../assets/img/pencil.png`)" style="height:70px;">
            </v-col>
            <v-col order="1">
                <img :src="require(`../../assets/img/plant.png`)" style="height:70px;">
            </v-col>
          </v-row>
          <v-row no-gutters>
            <v-col>
              <div>
                <strong>프로 절약러</strong>
              </div>
            </v-col>
            <v-col>
              <div>
                <strong>공부하는 맛</strong>
              </div>
            </v-col>
            <v-col>
              <div>
                <strong>프로 돌봄이</strong>
              </div>
            </v-col>
          </v-row>
          <v-row no-gutters>
            <v-col>
                <img :src="require(`../../assets/img/palette.png`)" style="height:70px;">
            </v-col>
            <v-col order="12">
                <img :src="require(`../../assets/img/question.png`)" style="height:70px;">
            </v-col>
            <v-col order="1">
                <img :src="require(`../../assets/img/question.png`)" style="height:70px;">
            </v-col>
          </v-row>
          <v-row no-gutters>
            <v-col>
                <div>
                  <strong>덕밍아웃</strong>
                </div>
            </v-col>
            <v-col order="12">
                <div style="color:#caccce;">
                  획득하러 가기
                </div>
            </v-col>
            <v-col order="1">
                <div style="color:#caccce;">
                  획득하러 가기
                </div>
            </v-col>
          </v-row>
          <v-row no-gutters>
            <v-col>
                <img :src="require(`../../assets/img/question.png`)" style="height:70px;">
            </v-col>
            <v-col order="12">
                <img :src="require(`../../assets/img/question.png`)" style="height:70px;">
            </v-col>
            <v-col order="1">
                <img :src="require(`../../assets/img/question.png`)" style="height:70px;">
            </v-col>
          </v-row>
          <v-row no-gutters>
            <v-col>
                <div style="color:#caccce;">
                  획득하러 가기
                </div>
            </v-col>
            <v-col order="12">
                <div style="color:#caccce;">
                  획득하러 가기
                </div>
            </v-col>
            <v-col order="1">
                <div style="color:#caccce;">
                  획득하러 가기
                </div>
            </v-col>
          </v-row>
        </div>
      </v-card>
    </div>
</template>

<script>
export default {
  data: () => ({
    badges: [
      {
        src: 'require(`../../assets/img/weight.png`)',   // 운동
        name: 'weight'
      },
      {
        src: 'require(`../../assets/img/pencil.png`)',      // 공부
        name: 'pencil'
      },
      {
        src: 'require(`../../assets/img/plant.png`)',       // 돌봄
        name: 'plant'
      },
      {
        src: 'require(`../../assets/img/diet.png`)',        // 다이어트
        name: 'diet'
      },
      {
        src: 'require(`../../assets/img/clock.png`)',        // 생활습관
        name: 'clock'
      },
      {
        src: 'require(`../../assets/img/palette.png`)',      // 취미
        name: 'palette'
      },
      {
        src: 'require(`../../assets/img/book.png`)',          // 독서
        name: 'book'
      },
      {
        src: 'require(`../../assets/img/piggybank.png`)',     // 돈관리
        name: 'piggybank'
      },
      {
        src: 'require(`../../assets/img/dove.png`)',          // 감정관리
        name: 'dove'
      },
      {
        src: 'require(`../../assets/img/question.png`)',           // 미정
        name: 'question'
      },
    ]
  })
}
</script>

<style>

</style>