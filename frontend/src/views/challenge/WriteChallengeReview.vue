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
        <p style="font-size: 1.1rem; font-weight: 600;">리뷰 쓰기</p>
        <v-textarea
          flat
          solo
          name="input-7-4"
          label="리뷰를 작성하세요(최대 1000자)"
          :rules="textRules"
          v-model="texts"
        ></v-textarea>
      </v-card-text>
    </v-card>
    <p></p>
    <v-container style="width: 90%;">
      <v-row>
        <v-col
          cols="8"
        >
          <v-file-input
            outlined
            dense
            multiple
            :rules="fileRules"
            v-model="files"
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
            @click="upload"
          >
            업로드
          </v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col
          cols="4"
          v-for="(item, idx) in items"
          :key="idx"
        >
          <v-img
            :src="item.src"
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
          @click="onSubmit"
        >
          완료
        </v-btn>
      </div>
    </v-container>
  </div>
</template>

<script>
export default {
  data: () => ({
    textRules: [v => v.length <= 1000 || '1000자 이상 입력할 수 없습니다'],
    texts: '',
    fileRules: [v => v.length <= 5 || '최대 5개의 파일을 올릴 수 있습니다'],
    files: '',
    items: [
      {
        src: "https://image.freepik.com/free-photo/a-man-and-a-boy-assemble-from-a-constructor-of-a-robot-male-and-children-s-hands-collect-lego_96336-244.jpg"
      }
    ]
  }),
  methods: {
    upload () {
      console.log(this.files);
    },
    onSubmit () {
      console.log(this.texts)
    }
  }
}
</script>

<style scoped>

</style>