<template>
  <v-row>
    <v-col
      v-for="review in reviews"
      :key="review.review_id"
      class="d-flex child-flex"
      cols="4"
    >
      <router-link :to="{ name: 'ReviewDetail' }">
        <!-- <v-img
        :src="`https://picsum.photos/500/300?image=${n * 5 + 10}`"
        :lazy-src="`https://picsum.photos/10/6?image=${n * 5 + 10}`"
        aspect-ratio="1"
        class="grey lighten-2"
        /> -->
        <img :src="review.src" alt="photo" class="review-photo"/>
      </router-link>
        <template v-slot:placeholder>
          <v-row
            class="fill-height ma-0"
            align="center"
            justify="center"
          >
            <v-progress-circular
              indeterminate
              color="grey lighten-5"
            ></v-progress-circular>
          </v-row>
        </template>
    </v-col>
  </v-row>
</template>

<script>
export default {
  data() {
    return {
      reviews: [
        {
          src: 'https://ifh.cc/g/xWSN8P.jpg',
          review_id: '1',
          review_content: '집밥 해먹기 챌린지를 통해 몸도 마음도 건강해질 수 있었다. :) 앞으로도 다른 챌린지에 꾸준히 참여해봐야겠다 !!! ',
          review_date: '2021.02.08',
          review_cool_count: '100',
          review_comment_count: '28',
          review_user_id: 'dlwlrma',
          challenge_id: '01110228591',
        },
        {
          src: 'https://ifh.cc/g/Ck2aox.jpg',
          review_id: '2',
          review_content: '취미로 시작했던 일주일에 한곡 작곡하기... 힘들기도 했지만 지역내 작은 대회에서 수상할 수 있어서 뿌듯했다.',
          review_date: '2021.01.15',
          review_cool_count: '30',
          review_comment_count: '28',
          review_user_id: 'dlwlrma',
          challenge_id: '01110228591',
        },
        {
          src: 'https://ifh.cc/g/mYsxhr.jpg',
          review_id: '3',
          review_content: '독서는 마음의 양식 :) 독서 챌린지는 꾸준히 진행할 예정이다. ',
          review_date: '2020.12.20',
          review_cool_count: '10',
          review_comment_count: '28',
          review_user_id: 'dlwlrma',
          challenge_id: '01110228591',
        }
      ],
    }
  }
}
</script>

<style>
.review-photo {
  width: 100%;
  height: 100%;
}
</style>