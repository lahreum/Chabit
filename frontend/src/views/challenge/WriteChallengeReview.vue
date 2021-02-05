<template>
  <div>
    <v-card
      class="mx-auto"
      width="80%"
    >
      <v-card-text>
        <div>아침 6시에 일어나기 챌린지</div>
      </v-card-text>
    </v-card>
    <p></p>
    <v-card
      class="mx-auto"
      width="80%"
    >
      <v-card-text>
        <p>인증 방법</p>
        <p>일어나서 세면대에서 물을 튼 사진을 오늘의 제스처 손동작이 나오도록 올려주세요.</p>
        <br>
        <p>예시</p>
        <v-row>
          <v-col
            cols="6"
          >
            <v-img
              src="https://cdn.vuetifyjs.com/images/parallax/material2.jpg"
              gradient="to top right, rgba(100,115,201,.33), rgba(25,32,72,.7)"
              class="rounded-lg"
            ></v-img>
          </v-col>

          <v-col
            cols="6"
          >
            <v-img 
              src="https://cdn.vuetifyjs.com/images/parallax/material2.jpg"
              class="rounded-lg"
            ></v-img>
          </v-col>
        </v-row>
        <p></p>
        <p>오늘의 손동작</p>
        <v-row>
          <v-col
            cols="6"
          >
            <v-img
              src="https://cdn.vuetifyjs.com/images/parallax/material2.jpg"
              gradient="to top right, rgba(100,115,201,.33), rgba(25,32,72,.7)"
              class="rounded-lg"
            ></v-img>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
    <p></p>
    <v-container style="width: 80%;">
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
          cols="4"
        >
          <v-img
            src="https://cdn.vuetifyjs.com/images/parallax/material2.jpg"
            gradient="to top right, rgba(100,115,201,.33), rgba(25,32,72,.7)"
            class="rounded-lg"
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