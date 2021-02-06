<template>
  <div>
    <p></p>
    <v-card
      class="mx-auto"
      width="90%"
      color="grey lighten-5"
    >
      <v-card-text>
        <div style="font-size: 1.3rem; font-weight: 600;">레고 만들기 챌린지</div>
      </v-card-text>
    </v-card>
    <p></p>
    <v-card
      class="mx-auto"
      width="90%"
      color="grey lighten-5"
    >
      <v-card-text>
        <p style="font-size: 1.1rem; font-weight: 600;">인증 방법</p>
        <p style="font-size: 1rem;">자신이 만든 레고 사진을 오늘의 제스처 손동작이 나오도록 올려주세요.</p>
        <hr>
        <p style="font-size: 1.1rem; font-weight: 600; margin-top: 1rem;">예시</p>
        <v-row>
          <v-col
            cols="6"
          >
            <v-img
              src="http://files.ciokorea.com/archive/images/201801/GettyImages-459315505.jpg"
              class="rounded-lg"
            ></v-img>
          </v-col>

          <v-col
            cols="6"
          >
            <v-img 
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTYJ8oGOpLT8gltD-FyOJtaYL6ZPaMhay_U4Q&usqp=CAU"
              class="rounded-lg"
            ></v-img>
          </v-col>
        </v-row>
        <p></p>
        <hr>
        <p style="font-size: 1.1rem; font-weight: 600; margin-top: 1rem;">오늘의 손동작</p>
        <v-row>
          <v-col
            cols="6"
          >
            <v-img
              src="https://cdn.crowdpic.net/list-thumb/thumb_l_A9B6BC32AFCE767900378297235318FC.png"
              class="rounded-lg"
            ></v-img>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
    <p></p>
    <v-container style="width: 90%;">
      <v-row>
        <v-col
          cols="8"
        >
          <v-file-input
            label="인증 사진"
            outlined
            dense
            accept="image/png, image/jpeg, image/bmp"
          ></v-file-input>
        </v-col>
        <v-col
          cols="4"
        >
          <v-btn
            rounded-lg
            color="red darken-4"
            dark
            height="40px"
          >
            업로드
          </v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col
          cols="12"
        >
          <v-img
            src="https://image.freepik.com/free-photo/a-man-and-a-boy-assemble-from-a-constructor-of-a-robot-male-and-children-s-hands-collect-lego_96336-244.jpg"
            class="rounded-lg"
            max-width="50%"
          ></v-img>
        </v-col>
      </v-row>
      <p></p>
      <div>
        <v-btn
          rounded
          color="red darken-4"
          dark
          width="100%"
          height="60px"
        >
          다음
        </v-btn>
      </div>
    </v-container>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>

</style>