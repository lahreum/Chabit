<template>
  <v-container>
    <div class="hot-review">
      <span class="hot">인기</span><span>리뷰</span>
    </div>
    <v-container>
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
              height="8rem"
            >

            </v-img>
          </v-card>
          <div class="hot-review-info">
            <span>{{item.nickname}}</span>
            <span class="like">{{item.like}}<i class="far fa-thumbs-up"></i></span>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      items: [
        {
          src: "http://www.itworld.co.kr/files/itworld/2020/02/GettyImages-1029186020.jpg",
          nickname: "살찐개",
          like: 231
        },
        {
          src: "https://lh3.googleusercontent.com/proxy/jv-1KBAeFnMDj1VHJf06P5FjvND66v-M4KpkwcPnVwvdiUEukV_xVPv7KnUc8TNNnfrI3CSnPgSO3BWumVnX5HK4MajaWgY-5RzspIkRnXInOIDnCeG85QVuVxlFn1F-0yh5uAx9Fw",
          nickname: "어텀리",
          like: 187
        },
        {
          src: "https://sites.google.com/site/playlegotime/_/rsrc/1468758956698/home/5932-2.jpg",
          nickname: "김미역",
          like: 156
        },
        {
          src: "https://myanimals.com/ko/wp-content/uploads/2019/04/Boy-playing-with-cat.jpg",
          nickname: "우반동",
          like: 133
        },
      ]
    }
  }
}
</script>

<style scoped>
.hot-review {
  padding-left: 0.5rem;
  margin-top: -2.5rem;
  margin-bottom: 1rem;
  font-size: 1.5rem;
  font-weight: 600;
}

.hot-review > .hot {
  color: #B71C1C;
  padding-right: 0.5rem;
}

.hot-review-info {
  margin: 0.5rem 0 -1rem 0;
  display: flex;
  justify-content: space-between;
  font-size: 1rem;
  font-weight: 600;
  max-width: 12rem;
}

.hot-review-info > .like {
  color: #B71C1C;
}

.hot-review-info > span > i {
  margin-left: 0.5rem;
  color: black;
}
</style>