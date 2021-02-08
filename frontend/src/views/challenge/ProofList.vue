<template>
  <div>
    <div class="proof-list">
      <span>인증 목록</span>
      <hr>
    </div>
    <v-container
      my-15
    >
      <v-row
      >
        <v-col
          cols="6"
          sm
          v-for="(item, idx) in items"
          :key="idx"
        >
          <v-card
            max-width="12rem"
          >
            <v-img
              :src="item.src"
              height="10rem"
              @click="chooseProof(item)"
            >

            </v-img>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
    <v-overlay
      :z-index="zIndex"
      :value="overlay"
      :chooseItem="this.chooseItem"
    >
      <div style="width: 75%; margin: 0 auto; font-weight: 600;">
        <span>{{chooseItem.nickname}}</span>
        <p>{{chooseItem.date}} {{chooseItem.time}}</p>
      </div>
      <div class="overlay-container">
        <img 
          :src="chooseItem.src" 
          alt=""
          width="80%"
          style="margin: 0 auto; border-radius: 1rem;"
        >
        <v-btn
          class="white--text my-4"
          color="#B71C1C"
          width="10"
          @click="overlay = false"
        >
          반려
        </v-btn>
      </div>
    </v-overlay>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [
        { 
          src: "https://mblogthumb-phinf.pstatic.net/MjAxOTEyMThfMjM3/MDAxNTc2NjYzMjc4MDYx.6XxslFlujKVgyCqdJgXw1paIc3S8YawddyUEAPRS5Rkg.IQ4tn0ZAXyTZWtMJcdHOwjEV0jjNtX52eSW6-pIArjcg.JPEG.snob_dh/1576663275966.jpg?type=w800",
          nickname: "살찐개",
          date: "2021/01/21",
          time: "06:03"
        },
        { 
          src: "http://image.yes24.com/blogimage/blog/2/5/25025/20100501213339779576.jpg",
          nickname: "김미역",
          date: "2021/01/21",
          time: "06:10"
        },
        { 
          src: "https://technicalneers.com/wp-content/uploads/2020/06/%EB%A0%88%EA%B3%A0-%EB%A7%8C%EB%93%A4%EA%B8%B0-3.jpg",
          nickname: "어텀리",
          date: "2021/01/22",
          time: "06:11"
        },
        { 
          src: "https://lh3.googleusercontent.com/proxy/alodTkydfMDboe6HX5id3U6iXLHdbLcFyOGsz59wJYQjLw2khCIVXnF7T2OIeg_jJdAvwsB9E7qDniwtCuj79jZc3EUKdsIBdeuty8B76mk4NwerwVkIDg",
          nickname: "우반동",
          date: "2021/01/22",
          time: "06:20"
        },
        { 
          src: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwAJ048k1bomhBCJ3_5qwZy69hhViiL_7jcw&usqp=CAU",
          nickname: "죠르디",
          date: "2021/01/22",
          time: "06:24"
        },
        { 
          src: "https://lh3.googleusercontent.com/proxy/qmBpFtsm-RoQZxX8InhROX0VHmXvC-bnVVdXCk_deB43OMkhDgriW-xt3xjJFPxc8D_WZszKMhsu3_deacdBPhWMj8hxC6p51sY0WZ5cC4gjL0QHiVFJD2W9rYvBZ1prwkWhclxnCIYV",
          nickname: "김미역",
          date: "2021/01/23",
          time: "06:02"
        },
      ],
      overlay: false,
      zIndex: 1,
      chooseItem: {
        src: "",
        nickname: "",
        date: "",
        time: ""
      },
    }
  },
  methods: {
    chooseProof(item) {
      this.overlay = !this.overlay
      // console.log(item)
      this.chooseItem = item
    }
  }
}
</script>

<style scoped>
.proof-list {
  position: fixed;
  z-index: 1;
  top: 3rem;
  width: 100%;
  background-color: white;
  text-align: center; 
  font-size: 1.7rem; 
  font-weight: bold;
}

.proof-list > hr {
  width: 90%;
  margin: 1rem auto;
  color: gray;
}

.overlay-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>